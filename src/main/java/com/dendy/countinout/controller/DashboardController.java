package com.dendy.countinout.controller;

import com.dendy.countinout.dao.model.secondary.PIC001Model;
import com.dendy.countinout.dao.service.secondary.PIC001Service;
import com.dendy.countinout.service.DetailsService;
import com.dendy.countinout.service.RTLangService;
import com.dendy.countinout.service.ReportService;
import com.dendy.countinout.utils.LabelUtils;
import com.dendy.countinout.utils.MessageHelperUtils;
import com.dendy.countinout.vo.GenerateDetailVo;
import com.dendy.countinout.vo.GenerateVo;
import com.dendy.countinout.vo.MessageVo;
import com.dendy.countinout.vo.TapInOutVo;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
public class DashboardController {

    @Autowired
    RTLangService rtLangService;

    @Autowired
    ReportService reportService;

    @Autowired
    PIC001Service pic001Service;
    private final ObjectMapper objectMapper;



    public DashboardController(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Autowired
    DetailsService detailsService;

    @RequestMapping(value = "/dashboard")
    public String getTapinandOutToday(HttpServletRequest request) {
        if(request.getSession().getAttribute("usernameLogin") != null){
            return "dashboard";
        }
        MessageVo vo = MessageHelperUtils.mustLoginFirst();
        request.getSession().setAttribute("msgLogin", vo);
        return "redirect:/login";
    }

    @RequestMapping(value = "/getData")
    @ResponseBody
    public TapInOutVo getData() {
        HashMap result = rtLangService.getTimeInandOutToday();
        TapInOutVo vo = (TapInOutVo) result.get(LabelUtils.data);
        return vo;
    }

    @RequestMapping(value = "/getDataByDate")
    @ResponseBody
    public TapInOutVo getDataByDate(@RequestParam String start, @RequestParam String end) throws ParseException {
        HashMap result = rtLangService.getTimeInandOut(start, end);
        TapInOutVo vo = (TapInOutVo) result.get(LabelUtils.data);
        return vo;
    }

    @RequestMapping(value = "/details")
    public String detail(HttpServletRequest request, @RequestParam String gateName, @RequestParam String startDate, @RequestParam String endDate) throws ParseException, SQLException {
        if(request.getSession().getAttribute("usernameLogin") != null) {

            HashMap result = detailsService.getDetail(gateName, startDate, endDate);
            request.getSession().setAttribute("gate", gateName);
            request.getSession().setAttribute("data", result.get(LabelUtils.data));
            request.getSession().setAttribute("generate", result.get("generate"));
            return "detail";
        }

        MessageVo vo = MessageHelperUtils.mustLoginFirst();
        request.getSession().setAttribute("msgLogin", vo);
        return "redirect:/login";
    }

    @GetMapping("/images/{id}")
    public void serveFile(@PathVariable String id, HttpServletResponse response) throws IOException {
        Optional<PIC001Model> omodel = pic001Service.findPIC001ModelByPid(id);
        PIC001Model model = omodel.get();
        response.setContentType("image/jpeg"); // or "image/png" or other image formats
        response.getOutputStream().write(model.getData());
        response.getOutputStream().close();
    }

    @RequestMapping(value = "/download", method = RequestMethod.POST)
    public ResponseEntity<byte[]> downloadReport(@RequestParam("fileType") String fileType,@RequestParam("searchValue") String searchValue, HttpServletRequest request, HttpServletResponse response ) throws JRException, IOException {
        GenerateVo vo = (GenerateVo) request.getSession().getAttribute("generate");
        GenerateVo tmp = new GenerateVo(vo);
        searchValue = searchValue.replace(",","");
        List<GenerateDetailVo> generateDetailVos = filterData(tmp.getData(), searchValue);
        byte[] reportBytes;
        String contentType;
        String fileExtension;
        tmp.setData(generateDetailVos);

        if ("pdf".equalsIgnoreCase(fileType)) {
            reportBytes = reportService.generateReportPdf(tmp);
            contentType = MediaType.APPLICATION_PDF_VALUE;
            fileExtension = "pdf";
        } else if ("xlsx".equalsIgnoreCase(fileType)) {
            // Implement XLSX report generation
            reportBytes = reportService.generateXLSXReport(tmp);
            contentType = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
            fileExtension = "xlsx";
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }


        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=report." + fileExtension);
        headers.setContentType(MediaType.parseMediaType(contentType));

        return ResponseEntity.ok().headers(headers).body(reportBytes);
    }

    private List<GenerateDetailVo> filterData(List<GenerateDetailVo> data, String searchValue) {
        if (searchValue == null || searchValue.isEmpty() || searchValue.equals("")) {
            return data;
        }

        return data.stream()
                .filter(vo -> vo.matchesSearchCriteria(searchValue))
                .collect(Collectors.toList());
    }

}
