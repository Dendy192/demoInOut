package com.dendy.countinout.dao.service.secondary;

import com.dendy.countinout.dao.model.secondary.PIC006Model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.Optional;

@Repository
public interface PIC006Service extends JpaRepository<PIC006Model, Timestamp> {
    Optional<PIC006Model> findPIC006ModelByPid(String pid);
}
