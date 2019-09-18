package com.saint.anthony.hibernateHomework.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Country")
public class Country {
    @Id
    @Column(name = "iata_country_code", unique = true, nullable = false)
    private String iataCountryCode;

    @Column(name = "name", unique = true, nullable = false)
    private String countryName;

    public String getIataCountryCode() {
        return iataCountryCode;
    }

    public void setIataCountryCode(String iataCountryCode) {
        this.iataCountryCode = iataCountryCode;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    @Override
    public String toString() {
        return "Country{" +
                "iataCountryCode='" + iataCountryCode + '\'' +
                ", countryName='" + countryName + '\'' +
                '}';
    }
}
