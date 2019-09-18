package com.saint.anthony.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Aircraft")
public class Aircraft {
    @Id
    @Column(name = "aircraft_id", unique = true)
    private Long id;

    @Column(name = "aircraft_creator_id", nullable = false)
    private int airCreatorId;

    @Column(name = "model", nullable = false)
    private String model;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getAirCreatorId() {
        return airCreatorId;
    }

    public void setAirCreatorId(int airCreatorId) {
        this.airCreatorId = airCreatorId;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Override
    public String toString() {
        return "Aircraft{" +
                "id=" + id +
                ", airCreatorId=" + airCreatorId +
                ", model='" + model + '\'' +
                '}';
    }
}
