package com.dendy.countinout.vo;

public class GenerateDetailVo {
    private String waktu;
    private String noKartu;
    private String nama;
    private String perusahaan;
    private String jabatan;
    private String zona;
    private String akess;

    public String getWaktu() {
        return waktu;
    }

    public void setWaktu(String waktu) {
        this.waktu = waktu;
    }

    public String getNoKartu() {
        return noKartu;
    }

    public void setNoKartu(String noKartu) {
        this.noKartu = noKartu;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getPerusahaan() {
        return perusahaan;
    }

    public void setPerusahaan(String perusahaan) {
        this.perusahaan = perusahaan;
    }

    public String getJabatan() {
        return jabatan;
    }

    public void setJabatan(String jabatan) {
        this.jabatan = jabatan;
    }

    public String getZona() {
        return zona;
    }

    public void setZona(String zona) {
        this.zona = zona;
    }

    public String getAkess() {
        return akess;
    }

    public void setAkess(String akess) {
        this.akess = akess;
    }

    public boolean matchesSearchCriteria(String searchValue) {
        return this.getNama().equalsIgnoreCase(searchValue);
    }

}
