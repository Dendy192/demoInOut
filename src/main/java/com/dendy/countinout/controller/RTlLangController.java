package com.dendy.countinout.controller;

import com.dendy.countinout.service.RTLangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Objects;

@Controller
public class RTlLangController {

    @Autowired
    RTLangService rtLangService;

    @RequestMapping(value = "/dashboard")
    public String getTapinandOutToday(){
        return "index";
    }

    @RequestMapping(value = "/getData")
    @ResponseBody
    public HashMap getData(){

        return rtLangService.getTimeInandOutToday();
    }
}
