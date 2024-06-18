package com.dendy.countinout.dao.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.sql.Timestamp;

@Entity
@Table(name = "TRNGENLOG")
public class TRNGENLOGModel {
    @Id
    @Column(name = "TRNLOG")
    private Timestamp log;

    @Column(name = "TRNID")
    private String id;

    @Column(name = "TRNTYPE")
    private int type;

    public Timestamp getLog() {
        return log;
    }

    public void setLog(Timestamp log) {
        this.log = log;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
