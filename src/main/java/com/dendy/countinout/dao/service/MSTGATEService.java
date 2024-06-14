package com.dendy.countinout.dao.service;

import com.dendy.countinout.dao.model.MSTGATEModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MSTGATEService extends JpaRepository<MSTGATEModel, String> {
    MSTGATEModel findMSTGATEModelById(String id);
    MSTGATEModel findMSTGATEModelByName(String name);
//    List<MSTGATEModel> findAll();
}
