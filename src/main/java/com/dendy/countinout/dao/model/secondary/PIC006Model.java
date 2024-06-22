package com.dendy.countinout.dao.model.secondary;

import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "PIC006")
public class PIC006Model {
    @Id
    @Column(name = "PLOG")
    private Timestamp plog;

    @Column(name = "PID")
    private String pid;

    @Column(name = "PTYPE")
    private String type;

    @Column(name = "PDATA")
    @Lob
    private byte[] data;

    public Timestamp getPlog() {
        return plog;
    }

    public void setPlog(Timestamp plog) {
        this.plog = plog;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }
}