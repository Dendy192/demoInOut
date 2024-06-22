package com.dendy.countinout.form;

import org.springframework.web.multipart.MultipartFile;

public class KaryawanForm {
    private String id;
    private boolean status;
    private String tag;
    private String ktp;
    private String perusahaan;
    private String nama;
    private String ruang;
    private String noHp;
    private String jab;
    private boolean unlimitid;

    private String berlaku;

    private MultipartFile foto;
    private MultipartFile  fotoKtp;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getKtp() {
        return ktp;
    }

    public void setKtp(String ktp) {
        this.ktp = ktp;
    }

    public String getPerusahaan() {
        return perusahaan;
    }

    public void setPerusahaan(String perusahaan) {
        this.perusahaan = perusahaan;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getRuang() {
        return ruang;
    }

    public void setRuang(String ruang) {
        this.ruang = ruang;
    }

    public String getNoHp() {
        return noHp;
    }

    public void setNoHp(String noHp) {
        this.noHp = noHp;
    }

    public String getJab() {
        return jab;
    }

    public void setJab(String jab) {
        this.jab = jab;
    }

    public String getBerlaku() {
        return berlaku;
    }

    public void setBerlaku(String berlaku) {
        this.berlaku = berlaku;
    }

    public MultipartFile getFoto() {
        return foto;
    }

    public void setFoto(MultipartFile foto) {
        this.foto = foto;
    }

    public MultipartFile getFotoKtp() {
        return fotoKtp;
    }

    public void setFotoKtp(MultipartFile fotoKtp) {
        this.fotoKtp = fotoKtp;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public boolean isUnlimitid() {
        return unlimitid;
    }

    public void setUnlimitid(boolean unlimitid) {
        this.unlimitid = unlimitid;
    }
}
