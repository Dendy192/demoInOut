package com.dendy.countinout.dao.model.primary;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "MSTGATE")
public class MSTGATEModel {
    @Id
    @Column(name = "MSTID")
    private String id;

    @Column(name = "MSTLOKASI")
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
