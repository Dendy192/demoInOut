package com.dendy.countinout.dao.service.primary;

import com.dendy.countinout.dao.model.primary.TRNKRTLANGModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Repository
public interface TRNKRTLANGService extends JpaRepository<TRNKRTLANGModel, String> {
    Optional<List<TRNKRTLANGModel>> findTRNKRTLANGModelByTapMasukBetween(Timestamp startDateTime, Timestamp endDateTime);
    Optional<List<TRNKRTLANGModel>> findTRNKRTLANGModelByTapKeluarBetween(Timestamp startDateTime, Timestamp endDateTime);

    Optional<List<TRNKRTLANGModel>> findTRNKRTLANGModelByGateMasukAndTapMasukBetween(String gateIn, Timestamp startDateTime, Timestamp endDateTime);

    Optional<List<TRNKRTLANGModel>> findTRNKRTLANGModelByGateKeluarAndTapKeluarBetween(String gateIn, Timestamp startDateTime, Timestamp endDateTime);
}
