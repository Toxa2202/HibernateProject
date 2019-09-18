package com.saint.anthony.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Booking")
public class Booking {
    @Id
    @Column(name = "client_id", nullable = false)
    private Long clientId;

    @Column(name = "flight_call", nullable = false)
    private Long flightCall;

    @Column(name = "aircraft_id", nullable = false)
    private Long aircraftId;

    @Column(name = "seat_id")
    private Long seatId;

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public Long getFlightCall() {
        return flightCall;
    }

    public void setFlightCall(Long flightCall) {
        this.flightCall = flightCall;
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

    @Override
    public String toString() {
        return "Booking{" +
                "clientId=" + clientId +
                ", flightCall=" + flightCall +
                ", aircraftId=" + aircraftId +
                ", seatId=" + seatId +
                '}';
    }
}
