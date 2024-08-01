package com.dendy.countinout.controller;

import com.dendy.countinout.dao.model.primary.MSTPERUSHModel;
import com.dendy.countinout.dao.model.secondary.PIC001Model;
import com.dendy.countinout.dao.model.secondary.PIC006Model;
import com.dendy.countinout.dao.service.primary.MSTPERUSHService;
import com.dendy.countinout.dao.service.secondary.PIC001Service;
import com.dendy.countinout.dao.service.secondary.PIC006Service;
import com.dendy.countinout.form.KaryawanForm;
import com.dendy.countinout.service.KaryawanService;
import com.dendy.countinout.utils.MessageHelperUtils;
import com.dendy.countinout.vo.KaryawanTableVo;
import com.dendy.countinout.vo.KaryawanVo;
import com.dendy.countinout.vo.MessageVo;
import com.dendy.countinout.vo.PerusahaanVo;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class KaryawanController {

    @Autowired
    PIC006Service pic006Service;

    @Autowired
    MSTPERUSHService mstperushService;

    @Autowired
    KaryawanService karyawanService;

    @RequestMapping(value = "/karyawanDetail")
    public String kayryawanDetail(@RequestParam String id, HttpServletRequest request, @ModelAttribute KaryawanForm form) {
        if (request.getSession().getAttribute("usernameLogin") != null) {
            List<MSTPERUSHModel> mstperushModels = mstperushService.findMSTPERUSHModelByStatus(1);
            List<PerusahaanVo> perusahaanVos = new ArrayList<>();
            for (MSTPERUSHModel model : mstperushModels) {
                PerusahaanVo perusahaanVo = new PerusahaanVo();
                perusahaanVo.setName(model.getName());
                perusahaanVos.add(perusahaanVo);
            }
            KaryawanVo vo = karyawanService.getDetailKaryawan(id);
            request.getSession().setAttribute("perusahaanList", perusahaanVos);
            request.getSession().setAttribute("karyawan", vo);
            return "karyawanDetail";
        }
        MessageVo vo = MessageHelperUtils.mustLoginFirst();
        request.getSession().setAttribute("msgLogin", vo);
        return "redirect:/login";
    }

    @RequestMapping(value = "/saveKaryawan", method = RequestMethod.POST)
    public String saveKaryawan(@RequestParam(name = "status", required = false) boolean status,
                               @RequestParam(name = "unlimitid", required = false) boolean unlimitid,
                               @RequestParam("inputFoto") MultipartFile foto,
                               @RequestParam("ktpFotoInput") MultipartFile ktpFoto,
                               @ModelAttribute KaryawanForm form, HttpServletRequest request, @RequestHeader(value = "Referer", required = false) String referer) throws ParseException, IOException {
        form.setStatus(status);
        form.setUnlimitid(unlimitid);
        form.setFoto(foto);
        form.setFotoKtp(ktpFoto);
        karyawanService.saveKaryawan(form);
        MessageVo vo = MessageHelperUtils.successUpdateData();
        request.getSession().setAttribute("msgKaryawan", vo);
        return "redirect:karyawanData";
    }

    @RequestMapping("/ktp/{id}")
    public void displayktp(@PathVariable String id, HttpServletResponse response) throws IOException {
        Optional<PIC006Model> omodel = pic006Service.findPIC006ModelByPid(id);
        PIC006Model model = omodel.get();
        response.setContentType("image/jpeg"); // or "image/png" or other image formats
        response.getOutputStream().write(model.getData());
        response.getOutputStream().close();
    }

    @RequestMapping(value = "/karyawanData")
    public String karyawanDataPage(HttpServletRequest request, HttpServletResponse response) {
        if (request.getSession().getAttribute("usernameLogin") != null) {

            List<KaryawanVo> vo = karyawanService.getKaryawanData();
            request.getSession().setAttribute("karyawans", vo);
            return "karyawan";
        }
        MessageVo vo = MessageHelperUtils.mustLoginFirst();
        request.getSession().setAttribute("msgLogin", vo);
        return "redirect:/login";
    }
}
