package com.saint.anthony.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Schedule")
public class Schedule {
    @Id
    @Column(name = "schedule_id", unique = true)
    private Long scheduleId;

    @Column(name = "departure_iata_airport_code", nullable = false)
    private String departureIataAirportCode;

    @Column(name = "arrival_iata_airport_code", nullable = false)
    private String arrivalIataAirportCode;

    @Column(name = "departure_time_gmt", nullable = false)
    private String departureTimeGMT;

    @Column(name = "arrival_time_gmt", nullable = false)
    private String arrivalTimeGMT;

    public Long getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(Long scheduleId) {
        this.scheduleId = scheduleId;
    }

    public String getDepartureIataAirportCode() {
        return departureIataAirportCode;
    }

    public void setDepartureIataAirportCode(String departureIataAirportCode) {
        this.departureIataAirportCode = departureIataAirportCode;
    }

    public String getArrivalIataAirportCode() {
        return arrivalIataAirportCode;
    }

    public void setArrivalIataAirportCode(String arrivalIataAirportCode) {
        this.arrivalIataAirportCode = arrivalIataAirportCode;
    }

    public String getDepartureTimeGMT() {
        return departureTimeGMT;
    }

    public void setDepartureTimeGMT(String departureTimeGMT) {
        this.departureTimeGMT = departureTimeGMT;
    }

    public String getArrivalTimeGMT() {
        return arrivalTimeGMT;
    }

    public void setArrivalTimeGMT(String arrivalTimeGMT) {
        this.arrivalTimeGMT = arrivalTimeGMT;
    }

    @Override
    public String toString() {
        return "Schedule{" +
                "scheduleId=" + scheduleId +
                ", departureIataAirportCode='" + departureIataAirportCode + '\'' +
                ", arrivalIataAirportCode='" + arrivalIataAirportCode + '\'' +
                ", departureTimeGMT='" + departureTimeGMT + '\'' +
                ", arrivalTimeGMT='" + arrivalTimeGMT + '\'' +
                '}';
    }
}
