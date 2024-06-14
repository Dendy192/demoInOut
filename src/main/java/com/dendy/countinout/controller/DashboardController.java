package com.dendy.countinout.controller;

import com.dendy.countinout.service.DetailsService;
import com.dendy.countinout.service.RTLangService;
import com.dendy.countinout.utils.LabelUtils;
import com.dendy.countinout.vo.DateVo;
import com.dendy.countinout.vo.TapInOutVo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.http.HttpResponse;
import java.text.ParseException;
import java.util.HashMap;

@Controller
public class DashboardController {

    @Autowired
    RTLangService rtLangService;
    private final ObjectMapper objectMapper;


    public DashboardController(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Autowired
    DetailsService detailsService;

    @RequestMapping(value = "/dashboard")
    public String getTapinandOutToday(){
        return "index";
    }

    @RequestMapping(value = "/getData")
    @ResponseBody
    public TapInOutVo getData(){
        HashMap result = rtLangService.getTimeInandOutToday();
        TapInOutVo vo = (TapInOutVo) result.get(LabelUtils.data);
        return vo;
    }
    @RequestMapping(value = "/getDataByDate")
    @ResponseBody
    public TapInOutVo getDataByDate(@RequestParam String start, @RequestParam String end) throws ParseException{
        HashMap result = rtLangService.getTimeInandOut(start,end);
        TapInOutVo vo = (TapInOutVo) result.get(LabelUtils.data);
        return vo;
    }

    @RequestMapping(value = "/details")
    public String detail(HttpServletRequest request, @RequestParam String gateName, @RequestParam String startDate, @RequestParam String endDate) throws ParseException {
        System.out.println(gateName);
        HashMap result = detailsService.getDetail(gateName,startDate,endDate);
        request.setAttribute("gate", gateName);
        request.setAttribute("data", result.get(LabelUtils.data));
        return "detail";
    }
    @Value("${directory.image}")
    private String FILE_DIRECTORY;
    @GetMapping("/images/{filename}")
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {
        File file = new File(FILE_DIRECTORY + filename);
        FileSystemResource resource = new FileSystemResource(file);

        // Check if the file exists
        if (!resource.exists()) {
            return ResponseEntity.notFound().build();
        }

        // Determine the content type dynamically
        String contentType;
        try {
            contentType = URLDecoder.decode(file.toURI().toURL().openConnection().getContentType(), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            contentType = MediaType.APPLICATION_OCTET_STREAM_VALUE;
        } catch (Exception e) {
            contentType = MediaType.APPLICATION_OCTET_STREAM_VALUE;
        }

        // Set content disposition as inline; filename="<filename>"
        HttpHeaders headers = new HttpHeaders();
        headers.setContentDispositionFormData("inline", filename);
        headers.setContentType(MediaType.parseMediaType(contentType));

        return ResponseEntity.ok()
                .headers(headers)
                .body(resource);
    }


//    @RequestMapping(value = "/download", method = RequestMethod.POST)
//    public ResponseEntity<byte[]> downloadReport(HttpRequest request, HttpResponse response){
//
////        byte[] reportBytes = reportService.generateReport(reportName, reportData);
//
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=report.pdf");
//        headers.setContentType(MediaType.APPLICATION_PDF);
//        return ResponseEntity.ok().headers(headers).body(reportBytes);
//    }
}
