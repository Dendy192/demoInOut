package com.dendy.countinout.dao.service.primary;

import com.dendy.countinout.dao.model.primary.MSTKARYModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MSTKARYService extends JpaRepository<MSTKARYModel, String> {
    Optional<MSTKARYModel> findMSTKARYModelById(String id);
}
