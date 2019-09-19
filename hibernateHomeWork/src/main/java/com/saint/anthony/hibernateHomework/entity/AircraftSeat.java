package com.saint.anthony.hibernateHomework.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "AircraftSeat")
public class AircraftSeat {
    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "aircraft_id", nullable = false)
    private Long aircraftId;

    @Column(name = "seat_id", nullable = false)
    private Long seatId;

    @Column(name = "travel_class_id", nullable = false)
    private Long travelClassId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAircraftId() {
        return aircraftId;
    }

    public void setAircraftId(Long aircraftId) {
        this.aircraftId = aircraftId;
    }

    public Long getSeatId() {
        return seatId;
    }

    public void setSeatId(Long seatId) {
        this.seatId = seatId;
    }

    public Long getTravelClassId() {
        return travelClassId;
    }

    public void setTravelClassId(Long travelClassId) {
        this.travelClassId = travelClassId;
    }

    @Override
    public String toString() {
        return "AircraftSeat{" +
                "id=" + id +
                ", aircraftId=" + aircraftId +
                ", seatId=" + seatId +
                ", travelClassId=" + travelClassId +
                '}';
    }
}
