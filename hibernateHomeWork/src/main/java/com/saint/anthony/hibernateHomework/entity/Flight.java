package com.saint.anthony.hibernateHomework.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Flight")
public class Flight {
    @Id
    @Column(name = "flight_call", unique = true)
    private Long flightCall;

    @Column(name = "schedule_id", unique = true, nullable = false)
    private Long scheduleId;

    @Column(name = "flight_status_id", nullable = false)
    private Long flightStatusId;

    public Long getFlightCall() {
        return flightCall;
    }

    public void setFlightCall(Long flightCall) {
        this.flightCall = flightCall;
    }

    public Long getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(Long scheduleId) {
        this.scheduleId = scheduleId;
    }

    public Long getFlightStatusId() {
        return flightStatusId;
    }

    public void setFlightStatusId(Long flightStatusId) {
        this.flightStatusId = flightStatusId;
    }

    @Override
    public String toString() {
        return "Flight{" +
                "flightCall=" + flightCall +
                ", scheduleId=" + scheduleId +
                ", flightStatusId=" + flightStatusId +
                '}';
    }
}
