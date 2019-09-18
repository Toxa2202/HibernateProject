package com.saint.anthony.hibernateHomework.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "FlightAircraftInstance")
public class FlightAircraftInstance {
    @Id
    @Column(name = "flight_call", unique = true)
    private Long flightCall;

    @Column(name = "aircraft_instance_id", nullable = false)
    private Long aircraftInstanceId;

    public Long getFlightCall() {
        return flightCall;
    }

    public void setFlightCall(Long flightCall) {
        this.flightCall = flightCall;
    }

    public Long getAircraftInstanceId() {
        return aircraftInstanceId;
    }

    public void setAircraftInstanceId(Long aircraftInstanceId) {
        this.aircraftInstanceId = aircraftInstanceId;
    }

    @Override
    public String toString() {
        return "FlightAircraftInstance{" +
                "flightCall=" + flightCall +
                ", aircraftInstanceId=" + aircraftInstanceId +
                '}';
    }
}
