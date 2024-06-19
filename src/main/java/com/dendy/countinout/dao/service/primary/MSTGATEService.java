package com.dendy.countinout.dao.service.primary;

import com.dendy.countinout.dao.model.primary.MSTGATEModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MSTGATEService extends JpaRepository<MSTGATEModel, String> {
    MSTGATEModel findMSTGATEModelById(String id);
    MSTGATEModel findMSTGATEModelByName(String name);
//    List<MSTGATEModel> findAll();
}
