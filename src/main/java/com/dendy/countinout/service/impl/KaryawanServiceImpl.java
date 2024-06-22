package com.dendy.countinout.service.impl;

import com.dendy.countinout.dao.model.primary.MSTKARYModel;
import com.dendy.countinout.dao.service.primary.MSTKARYService;
import com.dendy.countinout.dao.service.secondary.PIC001Service;
import com.dendy.countinout.dao.service.secondary.PIC006Service;
import com.dendy.countinout.service.KaryawanService;
import com.dendy.countinout.utils.DateUtils;
import com.dendy.countinout.vo.KaryawanVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class KaryawanServiceImpl implements KaryawanService {

    @Autowired
    MSTKARYService mstkaryService;
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
        if(model.getUnv() == 1){
            vo.setUnlimitid("checked");
        }
        if(model.getStatus() ==1){
            vo.setStatus("checked");
        }
        vo.setFotoKtp(model.getId());
        vo.setFoto(model.getId());

        return vo;
    }
}
