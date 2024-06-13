package com.dendy.countinout.service.impl;

import com.dendy.countinout.dao.model.MSTGATEModel;
import com.dendy.countinout.dao.service.MSTGATEService;
import com.dendy.countinout.service.GATEService;
import com.dendy.countinout.vo.GateVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class GateServiceImpl implements GATEService {
    @Autowired
    MSTGATEService gateService;
    @Transactional
    public GateVo getGateName(String id) {
        GateVo vo = new GateVo();
        MSTGATEModel gate = gateService.findMSTGATEModelById(id);
        vo.setId(gate.getId());
        vo.setName(gate.getName());
        return vo;
    }

    @Transactional
    public List<GateVo> getAllGate() {
        List<GateVo> result = new ArrayList<>();
        List<MSTGATEModel> modelList = gateService.findAll();
        for(MSTGATEModel gate:modelList){
            GateVo vo = new GateVo();
            vo.setName(gate.getName());
            vo.setId(gate.getId());
            result.add(vo);
        }
        return result;
    }
}
