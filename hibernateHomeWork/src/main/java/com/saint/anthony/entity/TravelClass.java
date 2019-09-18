package com.saint.anthony.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TravelClass")
public class TravelClass {
    @Id
    @Column(name = "travel_class_id", unique = true)
    private Long travelClassId;

    @Column(name = "name", unique = true)
    private String travellClassName;

    @Column(name = "description", nullable = false)
    private String travelClassDescription;

    public Long getTravelClassId() {
        return travelClassId;
    }

    public void setTravelClassId(Long travelClassId) {
        this.travelClassId = travelClassId;
    }

    public String getTravellClassName() {
        return travellClassName;
    }

    public void setTravellClassName(String travellClassName) {
        this.travellClassName = travellClassName;
    }

    public String getTravelClassDescription() {
        return travelClassDescription;
    }

    public void setTravelClassDescription(String travelClassDescription) {
        this.travelClassDescription = travelClassDescription;
    }

    @Override
    public String toString() {
        return "TravelClass{" +
                "travelClassId=" + travelClassId +
                ", travellClassName='" + travellClassName + '\'' +
                ", travelClassDescription='" + travelClassDescription + '\'' +
                '}';
    }
}
