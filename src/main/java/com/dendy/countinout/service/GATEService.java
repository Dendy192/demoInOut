package com.dendy.countinout.service;

import com.dendy.countinout.vo.GateVo;

import java.util.List;

public interface GATEService {
    GateVo getGateName(String id);
    List<GateVo> getAllGate();
}
