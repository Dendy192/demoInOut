package com.dendy.countinout.service.impl;

import com.dendy.countinout.dao.model.primary.MSTKARYModel;
import com.dendy.countinout.dao.model.secondary.PIC001Model;
import com.dendy.countinout.dao.model.secondary.PIC006Model;
import com.dendy.countinout.dao.service.primary.MSTKARYService;
import com.dendy.countinout.dao.service.secondary.PIC001Service;
import com.dendy.countinout.dao.service.secondary.PIC006Service;
import com.dendy.countinout.form.KaryawanForm;
import com.dendy.countinout.service.KaryawanService;
import com.dendy.countinout.utils.DateUtils;
import com.dendy.countinout.vo.KaryawanVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Optional;

@Service
public class KaryawanServiceImpl implements KaryawanService {

    @Autowired
    MSTKARYService mstkaryService;

    @Autowired
    PIC001Service pic001Service;
    @Autowired
    PIC006Service pic006Service;

    @Override
    public KaryawanVo getDetailKaryawan(String id) {
        KaryawanVo vo = new KaryawanVo();
        Optional<MSTKARYModel> mstkaryModel = mstkaryService.findMSTKARYModelById(id);
        MSTKARYModel model = mstkaryModel.get();
        vo.setId(model.getId());
        vo.setTag(model.getNoKartu());
        vo.setPerusahaan(model.getPerusahaan());
        vo.setKtp("");
        vo.setNama(model.getNama());
        vo.setRuang(model.getPass());
        vo.setNoHp("");
        vo.setJab(model.getJabatan());
        vo.setBerlaku(DateUtils.dateSqlToString(model.getBerlaku()));
        if (model.getUnv() == 1) {
            vo.setUnlimitid("checked");
        }
        if (model.getStatus() == 1) {
            vo.setStatus("checked");
        }
        vo.setFotoKtp(model.getId());
        vo.setFoto(model.getId());

        return vo;
    }

    @Override
    public void saveKaryawan(KaryawanForm form) throws ParseException, IOException {
        MSTKARYModel mstkaryModel = new MSTKARYModel();
        mstkaryModel.setUnv(0);
        mstkaryModel.setUnv(0);
        if (form.isStatus()) {
            mstkaryModel.setStatus(1);
        }
        if (form.isUnlimitid()) {
            mstkaryModel.setUnv(1);
        }
        mstkaryModel.setId(form.getId());
        mstkaryModel.setJabatan(form.getJab());
        mstkaryModel.setPass(form.getRuang());
        mstkaryModel.setBerlaku(DateUtils.stringToDateSQL(form.getBerlaku()));
        mstkaryModel.setNoKartu(form.getTag());
        mstkaryService.save(mstkaryModel);

        if (!form.getFotoStatus().isEmpty()) {
            PIC001Model pic001Model = pic001Service.findPIC001ModelByPid(form.getId());
            pic001Service.delete(pic001Model);
            pic001Model.setPlog(DateUtils.getTimeSql());
            if (form.getFotoStatus().equalsIgnoreCase("change")) {
                pic001Model.setData(form.getFoto().getBytes());
            }else if(form.getFotoStatus().equalsIgnoreCase("delete")){
                pic001Model.setData(null);
            }
            pic001Service.save(pic001Model);
        }
        if (!form.getFotoKtpStatus().isEmpty()) {
            PIC006Model pic006Model = pic006Service.findPIC006ModelByPid(form.getId());
            pic006Service.delete(pic006Model);
            pic006Model.setPlog(DateUtils.getTimeSql());
            if (form.getFotoKtpStatus().equalsIgnoreCase("change")) {
                pic006Model.setData(form.getFotoKtp().getBytes());

            }
            else if (form.getFotoKtpStatus().equalsIgnoreCase("delete")) {
                pic006Model.setData(null);

            }
            pic006Service.save(pic006Model);
        }


    }
}
