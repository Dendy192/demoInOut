package com.dendy.countinout.dao.service;

import com.dendy.countinout.dao.model.TRNGENLOGModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;

@Repository
public interface TRNGENLOGService extends JpaRepository<TRNGENLOGModel, Timestamp> {
    TRNGENLOGModel findTRNGENLOGModelByLogAndId(Timestamp log, String id);
}
