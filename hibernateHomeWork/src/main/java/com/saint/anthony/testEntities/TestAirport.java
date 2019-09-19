package com.saint.anthony.testEntities;

import com.saint.anthony.entity.Airport;
import javax.persistence.*;
import java.util.List;

public class TestAirport {
    private static final EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence
            .createEntityManagerFactory("hibernateHomework");

    public static void main(String[] args) {
//        addAirport("BLZ", "Belz Airport", "Belz", "UA");
//        changeAirport("BLZ", "Volyn National Airport");
//        deleteAirport("BLZ");
//        getAirport("BLZ");
        getAirports();

        ENTITY_MANAGER_FACTORY.close();
    }

    public static void addAirport(String airportCode, String name, String city, String countryCode) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;

        try {
            // Get transaction and start
            et = em.getTransaction();
            et.begin();

            // Create and set values for new client
            Airport airport = new Airport();
            airport.setIataAirportCode(airportCode);
            airport.setAirportName(name);
            airport.setCityName(city);
            airport.setIataCountryCode(countryCode);

            // Save the client object
            em.persist(airport);
            et.commit();
        } catch (Exception ex) {
            // If there is an exception - rollback changes
            if (et != null) {
                et.rollback();
            }
            ex.printStackTrace();
        } finally {
            // Close EntityManager
            em.close();
        }
    }

    public static void getAirport(String airportCode) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();

        // the lowercase c refers to the object
        // :custID is a parameterized query thats value is set below
        String query = "SELECT a FROM Airport a WHERE a.iataAirportCode = :airportCode";

        // Issue the query and get a matching Client
        TypedQuery<Airport> tq = em.createQuery(query, Airport.class);
        tq.setParameter("airportCode", airportCode);

        Airport airport = null;
        try {
            // Get matching client object and output
            airport = tq.getSingleResult();
            System.out.println(airport.getIataAirportCode() + " \t| " +
                    airport.getAirportName() + " \t| " +
                    airport.getCityName() + " \t| " +
                    airport.getIataCountryCode());
        } catch (NoResultException ex) {
            ex.printStackTrace();
        } finally {
            em.close();
        }
    }

    public static void getAirports() {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();

        // the lowercase c refers to the object
        // :custID is a parameterized query thats value is set below
        String strQuery = "SELECT a FROM Airport a WHERE a.iataAirportCode IS NOT NULL";

        // Issue the query and get a matching Client
        TypedQuery<Airport> tq = em.createQuery(strQuery, Airport.class);
        List<Airport> airports;
        try {
            // Get matching client object ant output
            airports = tq.getResultList();
            airports.forEach(airport -> System.out.println(
                    airport.getIataAirportCode() + " \t| " +
                            airport.getAirportName() + " \t| " +
                            airport.getCityName() + " \t| " +
                            airport.getIataCountryCode()));
        } catch (NoResultException ex) {
            ex.printStackTrace();
        } finally {
            em.close();
        }
    }

    public static void changeAirport(String airportCode, String name) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;

        Airport airport = null;
        try {
            // Get transaction and start
            et = em.getTransaction();
            et.begin();

            // Find client and make changes
            airport = em.find(Airport.class, airportCode);
            airport.setAirportName(name);

            // Save the client object
            em.persist(airport);
            et.commit();
        } catch (Exception ex) {
            // If there is an exception - rollback changes
            if (et != null) {
                et.rollback();
            }
            ex.printStackTrace();
        } finally {
            // Close EntityManager
            em.close();
        }
    }

    public static void deleteAirport(String airportCode) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;
        Airport airport = null;

        try {
            et = em.getTransaction();
            et.begin();
            airport = em.find(Airport.class, airportCode);
            em.remove(airport);
            et.commit();
        } catch (Exception ex) {
            // If there is an exception - rollback changes
            if (et != null) {
                et.rollback();
            }
            ex.printStackTrace();
        } finally {
            // Close EntityManager
            em.close();
        }
    }
}