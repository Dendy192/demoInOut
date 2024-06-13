package com.dendy.countinout.controller;

import com.dendy.countinout.service.RTLangService;
import com.dendy.countinout.utils.LabelUtils;
import com.dendy.countinout.vo.DateVo;
import com.dendy.countinout.vo.TapInOutVo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.UnsupportedEncodingException;
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
    public TapInOutVo getDataByDate(@RequestParam String start, @RequestParam String end) throws ParseException, UnsupportedEncodingException, JsonProcessingException {
//        String decodedData = java.net.URLDecoder.decode(data, "UTF-8");
//        DateVo dateVo = objectMapper.readValue(decodedData, DateVo.class);
        HashMap result = rtLangService.getTimeInandOut(start,end);
        TapInOutVo vo = (TapInOutVo) result.get(LabelUtils.data);
        return vo;
    }
}
