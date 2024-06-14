package com.dendy.countinout.service.impl;

import com.dendy.countinout.dao.model.TRNKRTLANGModel;
import com.dendy.countinout.dao.service.TRNKRTLANGService;
import com.dendy.countinout.service.GATEService;
import com.dendy.countinout.service.RTLangService;
import com.dendy.countinout.utils.DateUtils;
import com.dendy.countinout.utils.LabelUtils;
import com.dendy.countinout.vo.GateVo;
import com.dendy.countinout.vo.TapInOutDetailVo;
import com.dendy.countinout.vo.TapInOutVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.*;

@Service
public class RTlangServiceImpl implements RTLangService {
    @Autowired
    TRNKRTLANGService trnkrtlangService;

    @Autowired
    GATEService gateService;

    @Override
    public HashMap getTimeInandOutToday() {
        HashMap result = new HashMap<>();
        Timestamp start = DateUtils.toStartOfDay(DateUtils.getTimeSql());
        Timestamp end = DateUtils.toEndOfDay(DateUtils.getTimeSql());
        Optional<List<TRNKRTLANGModel>> oLModel = trnkrtlangService.findTRNKRTLANGModelByTapMasukBetween(start, end);
        Optional<List<TRNKRTLANGModel>> outLmodel = trnkrtlangService.findTRNKRTLANGModelByTapKeluarBetween(start, end);
        List<TRNKRTLANGModel> lModel = oLModel.get();
        List<TRNKRTLANGModel> oModel = outLmodel.get();
        HashMap tap = new HashMap<>();
        if (oLModel.isPresent() && lModel.size() != 0) {
            tap.put(LabelUtils.tapIn, lModel);

        }
        if (outLmodel.isPresent() && oModel.size() != 0) {
            tap.put(LabelUtils.tapOut, oModel);
        }
        if (!tap.isEmpty()) {


            HashMap gate = mappingGate(tap);

            TapInOutVo vo = new TapInOutVo();
            List<TapInOutDetailVo> tapInOutDetailVos = new ArrayList<>();
            Set<String> gateKey = gate.keySet();
            for (String key : gateKey) {

                HashMap tmp = (HashMap) gate.get(key);
                List<String> lIn = (List<String>) tmp.get(LabelUtils.tapIn);
                List<String> lOut = (List<String>) tmp.get(LabelUtils.tapOut);
                int inC = 0;
                int outC = 0;
                if (lIn != null) {
                    inC = lIn.size();
                }
                if (lOut != null) {
                    outC = lOut.size();
                }
                String countIn = String.valueOf(inC);
                String countOut = String.valueOf(outC);


                TapInOutDetailVo tapInOutDetailVo = new TapInOutDetailVo();
                tapInOutDetailVo.setGateName(key);
                tapInOutDetailVo.setTapIn(countIn);
                tapInOutDetailVo.setTapOut(countOut);


                tapInOutDetailVos.add(tapInOutDetailVo);

                vo.setData(tapInOutDetailVos);

                result.put(LabelUtils.data, vo);
            }
        } else {
            TapInOutVo vo2 = new TapInOutVo();
            List<TapInOutDetailVo> tapInOutDetailVos1 = new ArrayList<>();
            List<GateVo> gateVos = gateService.getAllGate();
            for (GateVo vo1 : gateVos) {
                TapInOutDetailVo tapInOutDetailVo = new TapInOutDetailVo();
                tapInOutDetailVo.setTapIn("0");
                tapInOutDetailVo.setTapOut("0");


                tapInOutDetailVo.setGateName(vo1.getName());


                tapInOutDetailVos1.add(tapInOutDetailVo);
            }
            vo2.setData(tapInOutDetailVos1);
            result.put(LabelUtils.data, vo2);
        }

        return result;
    }

    @Override
    public HashMap getTimeInandOut(String start, String end) throws ParseException {
        HashMap result = new HashMap<>();
        Timestamp startT = DateUtils.toStartOfDay(DateUtils.convertStringToTimeSql(start));
        Timestamp endT = DateUtils.toEndOfDay(DateUtils.convertStringToTimeSql(end));
        Optional<List<TRNKRTLANGModel>> oLModel = trnkrtlangService.findTRNKRTLANGModelByTapMasukBetween(startT, endT);
        Optional<List<TRNKRTLANGModel>> outLmodel = trnkrtlangService.findTRNKRTLANGModelByTapKeluarBetween(startT, endT);
        List<TRNKRTLANGModel> lModel = oLModel.get();
        List<TRNKRTLANGModel> oModel = outLmodel.get();
        HashMap tap = new HashMap<>();
        if (oLModel.isPresent() && lModel.size() != 0) {
            tap.put(LabelUtils.tapIn, lModel);

        }
        if (outLmodel.isPresent() && oModel.size() != 0) {
            tap.put(LabelUtils.tapOut, oModel);
        }
        if (!tap.isEmpty()) {


            HashMap gate = mappingGate(tap);

            TapInOutVo vo = new TapInOutVo();
            List<TapInOutDetailVo> tapInOutDetailVos = new ArrayList<>();
            Set<String> gateKey = gate.keySet();
            for (String key : gateKey) {

                HashMap tmp = (HashMap) gate.get(key);
                List<String> lIn = (List<String>) tmp.get(LabelUtils.tapIn);
                List<String> lOut = (List<String>) tmp.get(LabelUtils.tapOut);
                int inC = 0;
                int outC = 0;
                if (lIn != null) {
                    inC = lIn.size();
                }
                if (lOut != null) {
                    outC = lOut.size();
                }
                String countIn = String.valueOf(inC);
                String countOut = String.valueOf(outC);


                TapInOutDetailVo tapInOutDetailVo = new TapInOutDetailVo();
                tapInOutDetailVo.setGateName(key);
                tapInOutDetailVo.setTapIn(countIn);
                tapInOutDetailVo.setTapOut(countOut);


                tapInOutDetailVos.add(tapInOutDetailVo);

                vo.setData(tapInOutDetailVos);

                result.put(LabelUtils.data, vo);
            }
        } else {
            TapInOutVo vo2 = new TapInOutVo();
            List<TapInOutDetailVo> tapInOutDetailVos1 = new ArrayList<>();
            List<GateVo> gateVos = gateService.getAllGate();
            for (GateVo vo1 : gateVos) {
                TapInOutDetailVo tapInOutDetailVo = new TapInOutDetailVo();
                tapInOutDetailVo.setTapIn("0");
                tapInOutDetailVo.setTapOut("0");


                tapInOutDetailVo.setGateName(vo1.getName());


                tapInOutDetailVos1.add(tapInOutDetailVo);
            }
            vo2.setData(tapInOutDetailVos1);
            result.put(LabelUtils.data, vo2);
        }

        return result;
    }

    public HashMap mappingGate(HashMap map) {
        HashMap result = new HashMap<>();
        List<TRNKRTLANGModel> in = (List<TRNKRTLANGModel>) map.get(LabelUtils.tapIn);
        List<TRNKRTLANGModel> out = (List<TRNKRTLANGModel>) map.get(LabelUtils.tapOut);
        List<GateVo> getGate = gateService.getAllGate();
        for (GateVo gV : getGate) {
            result.put(gV.getName(), new HashMap<>());
        }
        if (in != null) {
            for (TRNKRTLANGModel i : in) {
                GateVo gateVo = gateService.getGateName(i.getGateMasuk());
                HashMap data = (HashMap) result.get(gateVo.getName());
                if (data.containsKey(LabelUtils.tapIn)) {
                    List<String> gate = (List<String>) data.get(LabelUtils.tapIn);
                    gate.add(i.getId());
                    data.put(LabelUtils.tapIn, gate);
                } else {
                    List<String> gate = new ArrayList<>();
                    gate.add(i.getId());
                    data.put(LabelUtils.tapIn, gate);
                }
                result.put(gateVo.getName(), data);

            }
        }
        if (out != null) {


            for (TRNKRTLANGModel o : out) {
                GateVo gateVo = gateService.getGateName(o.getGateMasuk());
                if (result.containsKey(gateVo.getName())) {
                    HashMap data = (HashMap) result.get(gateVo.getName());
                    if (data.containsKey(LabelUtils.tapOut)) {
                        List<String> gate = (List<String>) data.get(LabelUtils.tapOut);
                        gate.add(o.getId());
                        data.put(LabelUtils.tapOut, gate);
                    } else {
                        List<String> gate = new ArrayList<>();
                        gate.add(o.getId());
                        data.put(LabelUtils.tapOut, gate);
                    }
                    result.put(gateVo.getName(), data);
                } else {
                    HashMap data = new HashMap<>();
                    List<String> gate = new ArrayList<>();
                    gate.add(o.getId());
                    data.put(LabelUtils.tapOut, gate);
                    result.put(gateVo.getName(), data);
                }
            }
        }

        return result;
    }
}
