package com.dendy.countinout.service;

import com.dendy.countinout.vo.KaryawanVo;
import org.springframework.stereotype.Repository;


public interface KaryawanService {

    KaryawanVo getDetailKaryawan(String id);
}
