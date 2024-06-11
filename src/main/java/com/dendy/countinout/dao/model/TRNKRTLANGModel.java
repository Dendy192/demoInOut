package com.dendy.countinout.dao.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.sql.Timestamp;

@Entity
@Table(name = "TRNKRTLANG")
public class TRNKRTLANGModel {

    @Id
    @Column(name = "TRNID")
    private String id;

    @Column(name = "TRNCARDNO")
    private String cardNo;

    @Column(name = "TRNTIMEIN")
    private Timestamp tapIn;

    @Column(name = "TRNGATEIN")
    private String gateIn;

    @Column(name = "TRNCEKOUT")
    private int cekOut;

    @Column(name = "TRNTIMEOUT")
    private Timestamp tapOut;

    @Column(name = "TRNGATEOUT")
    private String gateOut;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public Timestamp getTapIn() {
        return tapIn;
    }

    public void setTapIn(Timestamp tapIn) {
        this.tapIn = tapIn;
    }

    public String getGateIn() {
        return gateIn;
    }

    public void setGateIn(String gateIn) {
        this.gateIn = gateIn;
    }

    public int getCekOut() {
        return cekOut;
    }

    public void setCekOut(int cekOut) {
        this.cekOut = cekOut;
    }

    public Timestamp getTapOut() {
        return tapOut;
    }

    public void setTapOut(Timestamp tapOut) {
        this.tapOut = tapOut;
    }

    public String getGateOut() {
        return gateOut;
    }

    public void setGateOut(String gateOut) {
        this.gateOut = gateOut;
    }
}
