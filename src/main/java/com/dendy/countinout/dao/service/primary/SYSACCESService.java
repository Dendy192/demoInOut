package com.dendy.countinout.dao.service.primary;

import com.dendy.countinout.dao.model.primary.SYSACCESModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Repository
public interface SYSACCESService extends JpaRepository<SYSACCESModel,String > {
    Optional<SYSACCESModel> findSYSACCESModelById(String id);
}
