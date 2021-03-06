package com.saint.anthony.hibernateHomework.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "FlightSeatPrice")
public class FlightSeatPrice {
    @Id
    @Column(name = "flight_call", unique = true)
    private Long flightCall;

    @Column(name = "aircraft_id", nullable = false)
    private Long aircraftId;

    @Column(name = "seat_id", nullable = false)
    private Long seatId;

    @Column(name = "price_usd", nullable = false)
    private Double priceUSD;

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

    public Double getPriceUSD() {
        return priceUSD;
    }

    public void setPriceUSD(Double priceUSD) {
        this.priceUSD = priceUSD;
    }

    @Override
    public String toString() {
        return "FlightSeatPrice{" +
                "flightId=" + flightCall +
                ", aircraftId=" + aircraftId +
                ", seatId=" + seatId +
                ", priceUSD=" + priceUSD +
                '}';
    }
}
