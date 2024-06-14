package com.dendy.countinout.dao.service;

import com.dendy.countinout.dao.model.MSTKARYModel;
import jakarta.persistence.Entity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MSTKARYService extends JpaRepository<MSTKARYModel, String> {
    Optional<MSTKARYModel> findMSTKARYModelById(String id);
}
