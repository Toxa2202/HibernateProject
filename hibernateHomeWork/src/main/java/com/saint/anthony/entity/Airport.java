package com.saint.anthony.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Airport")
public class Airport {
    @Id
    @Column(name = "iata_airport_code", unique = true)
    private String iataAirportCode;

    @Column(name = "name", nullable = false)
    private String airportName;

    @Column(name = "city", nullable = false)
    private String cityName;

    @Column(name = "iata_country_code", nullable = false)
    private String iataCountryCode;

    public String getIataAirportCode() {
        return iataAirportCode;
    }

    public void setIataAirportCode(String iataAirportCode) {
        this.iataAirportCode = iataAirportCode;
    }

    public String getAirportName() {
        return airportName;
    }

    public void setAirportName(String airportName) {
        this.airportName = airportName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getIataCountryCode() {
        return iataCountryCode;
    }

    public void setIataCountryCode(String iataCountryCode) {
        this.iataCountryCode = iataCountryCode;
    }

    @Override
    public String toString() {
        return "Airport{" +
                "iataAirportCode='" + iataAirportCode + '\'' +
                ", airportName='" + airportName + '\'' +
                ", cityName='" + cityName + '\'' +
                ", iataCountryCode='" + iataCountryCode + '\'' +
                '}';
    }
}
