package com.saint.anthony.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "AircraftInstance")
public class AircraftInstance {
    @Id
    @Column(name = "aircraft_instance_id", unique = true)
    private Long aircraftInstanceId;

    @Column(name = "aircraft_id", nullable = false)
    private Long aircraftId;

    public Long getAircraftInstanceId() {
        return aircraftInstanceId;
    }

    public void setAircraftInstanceId(Long aircraftInstanceId) {
        this.aircraftInstanceId = aircraftInstanceId;
    }

    public Long getAircraftId() {
        return aircraftId;
    }

    public void setAircraftId(Long aircraftId) {
        this.aircraftId = aircraftId;
    }

    @Override
    public String toString() {
        return "AircraftInstance{" +
                "aircraftInstanceId=" + aircraftInstanceId +
                ", aircraftId=" + aircraftId +
                '}';
    }
}
