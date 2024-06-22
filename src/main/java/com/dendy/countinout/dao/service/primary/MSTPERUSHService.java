package com.dendy.countinout.dao.service.primary;

import com.dendy.countinout.dao.model.primary.MSTPERUSHModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MSTPERUSHService extends JpaRepository<MSTPERUSHModel, String> {
    List<MSTPERUSHModel> findMSTPERUSHModelByStatus(int status);
}
