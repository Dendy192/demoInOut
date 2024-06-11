package com.dendy.countinout.dao.service;

import com.dendy.countinout.dao.model.TRNKRTLANGModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Repository
public interface TRNKRTLANGService extends JpaRepository<TRNKRTLANGModel, String> {
    Optional<List<TRNKRTLANGModel>> findTRNKRTLANGModelByTapInBetween(Timestamp startDateTime, Timestamp endDateTime);
}
