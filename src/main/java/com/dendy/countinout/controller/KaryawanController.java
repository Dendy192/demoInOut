package com.dendy.countinout.controller;

import com.dendy.countinout.dao.model.primary.MSTPERUSHModel;
import com.dendy.countinout.dao.model.secondary.PIC001Model;
import com.dendy.countinout.dao.model.secondary.PIC006Model;
import com.dendy.countinout.dao.service.primary.MSTPERUSHService;
import com.dendy.countinout.dao.service.secondary.PIC001Service;
import com.dendy.countinout.dao.service.secondary.PIC006Service;
import com.dendy.countinout.form.KaryawanForm;
import com.dendy.countinout.service.KaryawanService;
import com.dendy.countinout.vo.KaryawanVo;
import com.dendy.countinout.vo.PerusahaanVo;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
        List<MSTPERUSHModel> mstperushModels = mstperushService.findMSTPERUSHModelByStatus(1);
        List<PerusahaanVo> perusahaanVos = new ArrayList<>();
        for(MSTPERUSHModel model: mstperushModels){
            PerusahaanVo perusahaanVo = new PerusahaanVo();
            perusahaanVo.setName(model.getName());
            perusahaanVos.add(perusahaanVo);
        }
        KaryawanVo vo = karyawanService.getDetailKaryawan(id);
        request.getSession().setAttribute("perusahaanList", perusahaanVos);
        request.getSession().setAttribute("karyawan", vo);
        return "karyawanDetail";
    }

    @GetMapping("/ktp/{id}")
    public void displayktp(@PathVariable String id, HttpServletResponse response) throws IOException {
        PIC006Model model = pic006Service.findPIC006ModelByPid(id);
        response.setContentType("image/jpeg"); // or "image/png" or other image formats
        response.getOutputStream().write(model.getData());
        response.getOutputStream().close();
    }
}
