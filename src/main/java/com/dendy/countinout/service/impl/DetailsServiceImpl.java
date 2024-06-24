package com.dendy.countinout.service.impl;

import com.dendy.countinout.dao.model.primary.MSTKARYModel;
import com.dendy.countinout.dao.model.primary.TRNKRTLANGModel;
import com.dendy.countinout.dao.model.secondary.PIC001Model;
import com.dendy.countinout.dao.service.primary.MSTKARYService;
import com.dendy.countinout.dao.service.primary.TRNKRTLANGService;
import com.dendy.countinout.dao.service.secondary.PIC001Service;
import com.dendy.countinout.service.DetailsService;
import com.dendy.countinout.service.GATEService;
import com.dendy.countinout.utils.DateUtils;
import com.dendy.countinout.utils.LabelUtils;
import com.dendy.countinout.vo.GateVo;
import com.dendy.countinout.vo.GenerateDetailVo;
import com.dendy.countinout.vo.GenerateVo;
import com.dendy.countinout.vo.TableVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.sql.Blob;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.*;

@Service
public class DetailsServiceImpl implements DetailsService {

    @Autowired
    GATEService gateService;

    @Autowired
    MSTKARYService mstkaryService;

    @Autowired
    TRNKRTLANGService trnkrtlangService;


    @Transactional
    public HashMap getDetail(String gateName, String start, String end) throws ParseException, SQLException {
        GateVo gateVo = gateService.getGate(gateName);
        GenerateVo generateVo = new GenerateVo();
        generateVo.setGate(gateVo.getId() + " - " + gateVo.getName().toUpperCase());
        generateVo.setTanggal(DateUtils.convertDateTimeToTimeStringHalf(start) + " s/d " + DateUtils.convertDateTimeToTimeStringHalf(end));
        Timestamp startTime = DateUtils.convertStringToTimeSql(start);
        Timestamp endTime = DateUtils.convertStringToTimeSql(end + " 23:59:58");
        Optional<List<TRNKRTLANGModel>> oi = trnkrtlangService.findTRNKRTLANGModelByGateMasukAndTapMasukBetweenOrderByTapMasukDesc(gateVo.getId(), DateUtils.toStartOfDay(startTime), DateUtils.toEndOfDay(endTime));
        Optional<List<TRNKRTLANGModel>> oo = trnkrtlangService.findTRNKRTLANGModelByGateKeluarAndTapKeluarBetweenOrderByTapKeluarDesc(gateVo.getId(), DateUtils.toStartOfDay(startTime), DateUtils.toEndOfDay(endTime));
        List<TableVo> tableVoList = new ArrayList<>();
        List<GenerateDetailVo> generateDetailVoList = new ArrayList<>();
        HashMap data = new HashMap<>();
        if (oi.isPresent()) {
            List<TRNKRTLANGModel> tapInList = oi.get();
            for (TRNKRTLANGModel modelIn : tapInList) {
                GenerateDetailVo generateDetailVo = new GenerateDetailVo();
                TableVo vo = new TableVo();
                vo.setWaktu(DateUtils.convertDateTimeToTimeString(modelIn.getTapMasuk()));
                vo.setStatus("In");
                generateDetailVo.setWaktu(DateUtils.convertDateTimeToTimeStringFull(modelIn.getTapMasuk()));
                generateDetailVo.setAkess("MASUK");
                Optional<MSTKARYModel> optional = mstkaryService.findMSTKARYModelById(modelIn.getId());
                if (optional.isPresent()) {
                    MSTKARYModel mstkaryModel = optional.get();


                    generateDetailVo.setNama(mstkaryModel.getNama());
                    generateDetailVo.setNoKartu(mstkaryModel.getId());

                    generateDetailVo.setPerusahaan(mstkaryModel.getPerusahaan());
                    generateDetailVo.setJabatan(mstkaryModel.getJabatan());
                    generateDetailVo.setZona(mstkaryModel.getPass());
                    generateDetailVoList.add(generateDetailVo);

                    vo.setJabatan(mstkaryModel.getJabatan());
                    vo.setZona(mstkaryModel.getPass());
                    vo.setKartu("1");
                    vo.setFoto(mstkaryModel.getId());
                    vo.setNama(mstkaryModel.getNama());
                    vo.setPerushaan(mstkaryModel.getPerusahaan());
                    vo.setNoKartu(mstkaryModel.getId());
                    tableVoList.add(vo);

                } else {
                }

            }
        }
        if (oo.isPresent()) {
            List<TRNKRTLANGModel> tapOutList = oo.get();
            for (TRNKRTLANGModel modelOut : tapOutList) {
                GenerateDetailVo generateDetailVo = new GenerateDetailVo();
                TableVo vo = new TableVo();
                vo.setWaktu(DateUtils.convertDateTimeToTimeString(modelOut.getTapKeluar()));
                vo.setStatus("Out");
                generateDetailVo.setWaktu(DateUtils.convertDateTimeToTimeStringFull(modelOut.getTapKeluar()));
                generateDetailVo.setAkess("KELUAR");
                Optional<MSTKARYModel> optional = mstkaryService.findMSTKARYModelById(modelOut.getId());
                if (optional.isPresent()) {
                    MSTKARYModel mstkaryModel = optional.get();
                    vo.setNama(mstkaryModel.getNama());
                    vo.setPerushaan(mstkaryModel.getPerusahaan());
                    vo.setFoto(mstkaryModel.getId());
                    vo.setNoKartu(mstkaryModel.getId());
                    vo.setJabatan(mstkaryModel.getJabatan());
                    vo.setZona(mstkaryModel.getPass());
                    vo.setKartu("1");
                    generateDetailVo.setPerusahaan(mstkaryModel.getPerusahaan());
                    generateDetailVo.setJabatan(mstkaryModel.getJabatan());
                    generateDetailVo.setZona(mstkaryModel.getPass());
                    generateDetailVo.setNama(mstkaryModel.getNama());
                    generateDetailVo.setNoKartu(mstkaryModel.getId());
                    generateDetailVoList.add(generateDetailVo);
                    tableVoList.add(vo);

                } else {
                }
            }
        }
        Collections.sort(tableVoList, new Comparator<TableVo>() {
            @Override
            public int compare(TableVo o1, TableVo o2) {
                return o2.getWaktu().compareTo(o1.getWaktu());
            }
        });
        Collections.sort(generateDetailVoList, new Comparator<GenerateDetailVo>() {
            @Override
            public int compare(GenerateDetailVo o1, GenerateDetailVo o2) {
                return o2.getWaktu().compareTo(o1.getWaktu());
            }
        });
        generateVo.setData(generateDetailVoList);
        data.put("generate", generateVo);
        data.put(LabelUtils.data, tableVoList);
        return data;
    }


}
