package com.saint.anthony.hibernateHomework.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "FlightStatus")
public class FlightStatus {
    @Id
    @Column(name = "flight_status_id", unique = true)
    private Long flightStatusId;

    @Column(name = "name", unique = true)
    private String flightStatusName;

    @Column(name = "details", nullable = false)
    private String flightStatusDetails;

    public Long getFlightStatusId() {
        return flightStatusId;
    }

    public void setFlightStatusId(Long flightStatusId) {
        this.flightStatusId = flightStatusId;
    }

    public String getFlightStatusName() {
        return flightStatusName;
    }

    public void setFlightStatusName(String flightStatusName) {
        this.flightStatusName = flightStatusName;
    }

    public String getFlightStatusDetails() {
        return flightStatusDetails;
    }

    public void setFlightStatusDetails(String flightStatusDetails) {
        this.flightStatusDetails = flightStatusDetails;
    }

    @Override
    public String toString() {
        return "FlightStatus{" +
                "flightStatusId=" + flightStatusId +
                ", flightStatusName='" + flightStatusName + '\'' +
                ", flightStatusDetails='" + flightStatusDetails + '\'' +
                '}';
    }
}
