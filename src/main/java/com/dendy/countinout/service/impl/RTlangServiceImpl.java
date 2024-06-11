package com.dendy.countinout.service.impl;

import com.dendy.countinout.dao.model.TRNKRTLANGModel;
import com.dendy.countinout.dao.service.TRNKRTLANGService;
import com.dendy.countinout.service.RTLangService;
import com.dendy.countinout.utils.DateUtils;
import com.dendy.countinout.utils.LabelUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.*;

@Service
public class RTlangServiceImpl implements RTLangService {
    @Autowired
    TRNKRTLANGService trnkrtlangService;
    @Override
    public HashMap getTimeInandOutToday() {
        HashMap result = new HashMap<>();
        Timestamp start = DateUtils.toStartOfDay(DateUtils.getTimeSql());
        Timestamp end = DateUtils.toEndOfDay(DateUtils.getTimeSql());
        Optional<List<TRNKRTLANGModel>> oLModel = trnkrtlangService.findTRNKRTLANGModelByTapInBetween(start,end);
        if(oLModel.isPresent()){
            List<TRNKRTLANGModel> lModel = oLModel.get();
            List<String> tapOut = new ArrayList<>();
            for (TRNKRTLANGModel model : lModel){
                if(model.getTapOut() !=null){
                    tapOut.add(model.getId());
                }
            }
            result.put(LabelUtils.tapIn, lModel.size());
            result.put(LabelUtils.tapOut, tapOut.size());
        }else{
            result.put(LabelUtils.tapIn, 0);
            result.put(LabelUtils.tapOut, 0);
        }
        return result;
    }
}
