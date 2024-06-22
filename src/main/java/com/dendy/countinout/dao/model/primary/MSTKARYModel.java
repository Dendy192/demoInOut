package com.dendy.countinout.dao.model.primary;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.sql.Date;

@Entity
@Table(name = "MSTKARY")
public class MSTKARYModel {
    @Id
    @Column(name = "MSTID")
    private String id;

    @Column(name = "MSTNAMA2")
    private String nama;

    @Column(name = "MSTJAB")
    private String jabatan;

    @Column(name = "MSTTAG")
    private String noKartu;

    @Column(name = "MSTMANDOR")
    private String perusahaan;

    @Column(name = "MSTPIC")
    private String pic;
    @Column(name = "MSTPASS")
    private String pass;

    @Column(name = "MSTACTIVE")
    private int status;

    @Column(name = "MSTVALID")
    private Date berlaku;

    @Column(name = "MSTUNV")
    private int unv;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getJabatan() {
        return jabatan;
    }

    public void setJabatan(String jabatan) {
        this.jabatan = jabatan;
    }

    public String getNoKartu() {
        return noKartu;
    }

    public void setNoKartu(String noKartu) {
        this.noKartu = noKartu;
    }

    public String getPerusahaan() {
        return perusahaan;
    }

    public void setPerusahaan(String perusahaan) {
        this.perusahaan = perusahaan;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getBerlaku() {
        return berlaku;
    }

    public void setBerlaku(Date berlaku) {
        this.berlaku = berlaku;
    }

    public int getUnv() {
        return unv;
    }

    public void setUnv(int unv) {
        this.unv = unv;
    }
}
