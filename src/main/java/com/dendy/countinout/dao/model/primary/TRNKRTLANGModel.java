package com.dendy.countinout.dao.model.primary;

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
    private Timestamp tapMasuk;

    @Column(name = "TRNGATEIN")
    private String gateMasuk;

    @Column(name = "TRNCEKOUT")
    private int cekOut;

    @Column(name = "TRNTIMEOUT")
    private Timestamp tapKeluar;

    @Column(name = "TRNGATEOUT")
    private String gateKeluar;

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


    public String getGateMasuk() {
        return gateMasuk;
    }

    public void setGateMasuk(String gateMasuk) {
        this.gateMasuk = gateMasuk;
    }

    public int getCekOut() {
        return cekOut;
    }

    public void setCekOut(int cekOut) {
        this.cekOut = cekOut;
    }

    public String getGateKeluar() {
        return gateKeluar;
    }

    public void setGateKeluar(String gateKeluar) {
        this.gateKeluar = gateKeluar;
    }

    public Timestamp getTapMasuk() {
        return tapMasuk;
    }

    public void setTapMasuk(Timestamp tapMasuk) {
        this.tapMasuk = tapMasuk;
    }

    public Timestamp getTapKeluar() {
        return tapKeluar;
    }

    public void setTapKeluar(Timestamp tapKeluar) {
        this.tapKeluar = tapKeluar;
    }
}
