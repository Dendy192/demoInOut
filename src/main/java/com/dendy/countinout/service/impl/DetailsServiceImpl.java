package com.dendy.countinout.service.impl;

import com.dendy.countinout.dao.model.MSTKARYModel;
import com.dendy.countinout.dao.model.TRNKRTLANGModel;
import com.dendy.countinout.dao.service.MSTKARYService;
import com.dendy.countinout.dao.service.TRNKRTLANGService;
import com.dendy.countinout.service.DetailsService;
import com.dendy.countinout.service.GATEService;
import com.dendy.countinout.utils.DateUtils;
import com.dendy.countinout.utils.LabelUtils;
import com.dendy.countinout.vo.GateVo;
import com.dendy.countinout.vo.TableVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class DetailsServiceImpl implements DetailsService {

    @Autowired
    GATEService gateService;

    @Autowired
    MSTKARYService mstkaryService;

    @Autowired
    TRNKRTLANGService trnkrtlangService;

    @Override
    public HashMap getDetail(String gateName, String start, String end) throws ParseException {
        GateVo gateVo = gateService.getGate(gateName);
        Timestamp startTime = DateUtils.convertStringToTimeSql(start);
        Timestamp endTime = DateUtils.convertStringToTimeSql(end+"23:59:59");
        Optional<List<TRNKRTLANGModel>> oi = trnkrtlangService.findTRNKRTLANGModelByGateMasukAndTapMasukBetween(gateVo.getId(), DateUtils.toStartOfDay(startTime), DateUtils.toEndOfDay(endTime));
        Optional<List<TRNKRTLANGModel>> oo = trnkrtlangService.findTRNKRTLANGModelByGateKeluarAndTapKeluarBetween(gateVo.getId(), DateUtils.toStartOfDay(startTime), DateUtils.toEndOfDay(endTime));
        List<TableVo> tableVoList = new ArrayList<>();
        HashMap data = new HashMap<>();
        if (oi.isPresent()) {
            List<TRNKRTLANGModel> tapInList = oi.get();
            for (TRNKRTLANGModel modelIn : tapInList) {
                TableVo vo = new TableVo();
                vo.setWaktu(DateUtils.convertDateTimeToTimeString(modelIn.getTapMasuk()));
                vo.setStatus("In");
                Optional<MSTKARYModel> optional = mstkaryService.findMSTKARYModelById(modelIn.getId());
                if (optional.isPresent()) {
                    MSTKARYModel mstkaryModel = optional.get();
                    vo.setNama(mstkaryModel.getNama());
                    vo.setDepartement(mstkaryModel.getJabatan());
                    vo.setPerushaan(mstkaryModel.getPerusahaan());
                    vo.setFoto("arashmil.jpg");
                    vo.setNoKartu(modelIn.getId());
                    tableVoList.add(vo);
                } else {
                }

            }
        }
        if (oo.isPresent()) {
            List<TRNKRTLANGModel> tapOutList = oo.get();
            for (TRNKRTLANGModel modelOut : tapOutList) {
                TableVo vo = new TableVo();
                vo.setWaktu(DateUtils.convertDateTimeToTimeString(modelOut.getTapKeluar()));
                vo.setStatus("Out");
                Optional<MSTKARYModel> optional = mstkaryService.findMSTKARYModelById(modelOut.getId());
                if (optional.isPresent()) {
                    MSTKARYModel mstkaryModel = optional.get();
                    vo.setNama(mstkaryModel.getNama());
                    vo.setDepartement(mstkaryModel.getJabatan());
                    vo.setPerushaan(mstkaryModel.getPerusahaan());
                    vo.setFoto("arashmil.jpg");
                    vo.setNoKartu(modelOut.getId());
                    tableVoList.add(vo);

                } else {
                }
            }
        }
        data.put(LabelUtils.data, tableVoList);
        return data;
    }
}
