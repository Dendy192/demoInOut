package com.dendy.countinout.service;

import com.dendy.countinout.form.KaryawanForm;
import com.dendy.countinout.vo.KaryawanVo;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;


public interface KaryawanService {

    KaryawanVo getDetailKaryawan(String id);

    void saveKaryawan(KaryawanForm form) throws ParseException, IOException;
}
