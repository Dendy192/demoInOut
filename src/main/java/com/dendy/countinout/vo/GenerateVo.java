package com.dendy.countinout.vo;

import java.util.ArrayList;
import java.util.List;

public class GenerateVo {
    private String gate;
    private String company;
    private String tanggal;
    private List<GenerateDetailVo> data;

    public GenerateVo() {

    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }


    public String getGate() {
        return gate;
    }

    public void setGate(String gate) {
        this.gate = gate;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public List<GenerateDetailVo> getData() {
        return data;
    }

    public void setData(List<GenerateDetailVo> data) {
        this.data = data;
    }
    public GenerateVo(GenerateVo original) {
        this.gate = original.gate;
        this.company = original.company;
        this.tanggal = original.tanggal;
        this.data = new ArrayList<>(original.data);
    }

}
