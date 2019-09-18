package com.saint.anthony.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Direction")
public class Direction {
    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "departure_iata_airport_code", nullable = false)
    private String departIataAirportCode;

    @Column(name = "arrival_iata_airport_code", nullable = false)
    private String arrivalIataAirprotCode;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDepartIataAirportCode() {
        return departIataAirportCode;
    }

    public void setDepartIataAirportCode(String departIataAirportCode) {
        this.departIataAirportCode = departIataAirportCode;
    }

    public String getArrivalIataAirprotCode() {
        return arrivalIataAirprotCode;
    }

    public void setArrivalIataAirprotCode(String arrivalIataAirprotCode) {
        this.arrivalIataAirprotCode = arrivalIataAirprotCode;
    }

    @Override
    public String toString() {
        return "Direction{" +
                "departIataAirportCode='" + departIataAirportCode + '\'' +
                ", arrivalIataAirprotCode='" + arrivalIataAirprotCode + '\'' +
                ", id=" + id +
                '}';
    }
}
