package com.dendy.countinout.dao.service.secondary;

import com.dendy.countinout.dao.model.secondary.PIC001Model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.Optional;

@Repository
public interface PIC001Service extends JpaRepository<PIC001Model, Timestamp> {
    Optional<PIC001Model> findPIC001ModelByPid(String pid);

}
