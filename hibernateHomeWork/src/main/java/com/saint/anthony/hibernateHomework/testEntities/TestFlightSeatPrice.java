package com.saint.anthony.hibernateHomework.testEntities;

import com.saint.anthony.hibernateHomework.entity.FlightSeatPrice;

import javax.persistence.*;
import java.util.List;

public class TestFlightSeatPrice {
    private static final EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence
            .createEntityManagerFactory("hibernateHomework");

    public static void main(String[] args) {
//        addFlightSeatPrice(5L, 1L, 17L, 123.50);
//        changeFlightSeatPrice(5L, 83.99);
//        deleteFlightSeatPrice(5L);
//        getFlightSeatPrice(1L);
        getFlightSeatPrices();

        ENTITY_MANAGER_FACTORY.close();
    }

    public static void addFlightSeatPrice(Long flightCall, Long aircraftID, Long seatID, Double priceUSD) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;

        try {
            // Get transaction and start
            et = em.getTransaction();
            et.begin();

            // Create and set values for new client
            FlightSeatPrice flightSeatPrice = new FlightSeatPrice();
            flightSeatPrice.setFlightCall(flightCall);
            flightSeatPrice.setAircraftId(aircraftID);
            flightSeatPrice.setSeatId(seatID);
            flightSeatPrice.setPriceUSD(priceUSD);

            // Save the client object
            em.persist(flightSeatPrice);
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

    public static void getFlightSeatPrice(Long flightCall) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();

        // the lowercase c refers to the object
        // :custID is a parameterized query thats value is set below
        String query = "SELECT f FROM FlightSeatPrice f WHERE f.flightCall = :flight_call";

        // Issue the query and get a matching Client
        TypedQuery<FlightSeatPrice> tq = em.createQuery(query, FlightSeatPrice.class);
        tq.setParameter("flight_call", flightCall);

        FlightSeatPrice flightSeatPrice = null;
        try {
            // Get matching client object and output
            flightSeatPrice = tq.getSingleResult();
            System.out.println(flightSeatPrice.getFlightCall() + " \t| " +
                    flightSeatPrice.getAircraftId() + " \t| " +
                    flightSeatPrice.getSeatId() + " \t| " +
                    flightSeatPrice.getPriceUSD());
        } catch (NoResultException ex) {
            ex.printStackTrace();
        } finally {
            em.close();
        }
    }

    public static void getFlightSeatPrices() {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();

        // the lowercase c refers to the object
        // :custID is a parameterized query thats value is set below
        String strQuery = "SELECT f FROM FlightSeatPrice f WHERE f.flightCall IS NOT NULL";

        // Issue the query and get a matching Client
        TypedQuery<FlightSeatPrice> tq = em.createQuery(strQuery, FlightSeatPrice.class);
        List<FlightSeatPrice> flightSeatPrices;
        try {
            // Get matching client object ant output
            flightSeatPrices = tq.getResultList();
            flightSeatPrices.forEach(flightSeatPrice -> System.out.println(
                            flightSeatPrice.getFlightCall() + " \t| " +
                            flightSeatPrice.getAircraftId() + " \t| " +
                            flightSeatPrice.getSeatId() + " \t| " +
                            flightSeatPrice.getPriceUSD()));
        } catch (NoResultException ex) {
            ex.printStackTrace();
        } finally {
            em.close();
        }
    }

    public static void changeFlightSeatPrice(Long flightCall, Double priceUSD) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;

        FlightSeatPrice flightSeatPrice = null;
        try {
            // Get transaction and start
            et = em.getTransaction();
            et.begin();

            // Find client and make changes
            flightSeatPrice = em.find(FlightSeatPrice.class, flightCall);
            flightSeatPrice.setPriceUSD(priceUSD);

            // Save the client object
            em.merge(flightSeatPrice);
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

    public static void deleteFlightSeatPrice(Long flightCall) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;
        FlightSeatPrice flightSeatPrice = null;

        try {
            et = em.getTransaction();
            et.begin();
            flightSeatPrice = em.find(FlightSeatPrice.class, flightCall);
            em.remove(flightSeatPrice);
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