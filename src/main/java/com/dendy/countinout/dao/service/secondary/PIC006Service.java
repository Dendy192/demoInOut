package com.dendy.countinout.dao.service.secondary;

import com.dendy.countinout.dao.model.secondary.PIC006Model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
@Repository
public interface PIC006Service extends JpaRepository<PIC006Model, Timestamp> {
    PIC006Model findPIC006ModelByPid(String pid);
}
