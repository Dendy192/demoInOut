package com.dendy.countinout.dao.model.primary;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "SYSACCESS")
public class SYSACCESModel {
    @Id
    @Column(name = "USID")
    private String id;

    @Column(name = "USNAME")
    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
