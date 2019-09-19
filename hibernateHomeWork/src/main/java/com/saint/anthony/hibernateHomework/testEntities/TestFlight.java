package com.saint.anthony.hibernateHomework.testEntities;

import com.saint.anthony.hibernateHomework.entity.Flight;
import javax.persistence.*;
import java.util.List;

public class TestFlight {
    private static final EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence
            .createEntityManagerFactory("hibernateHomework");

    public static void main(String[] args) {
//        addFlight(6L, 1643L, 2L);
//        changeFlight(6L, 1643L, 3L);
//        deleteFlight(6L);
//        getFlight(1L);
        getFlights();

        ENTITY_MANAGER_FACTORY.close();
    }

    public static void addFlight(Long flightCall, Long scheduleID, Long flightStatusID) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;

        try {
            // Get transaction and start
            et = em.getTransaction();
            et.begin();

            // Create and set values for new client
            Flight flight = new Flight();
            flight.setFlightCall(flightCall);
            flight.setScheduleId(scheduleID);
            flight.setFlightStatusId(flightStatusID);

            // Save the client object
            em.persist(flight);
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

    public static void getFlight(Long flightCall) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();

        // the lowercase c refers to the object
        // :custID is a parameterized query thats value is set below
        String query = "SELECT f FROM Flight f WHERE f.flightCall = :flight_call";

        // Issue the query and get a matching Client
        TypedQuery<Flight> tq = em.createQuery(query, Flight.class);
        tq.setParameter("flight_call", flightCall);

        Flight flight = null;
        try {
            // Get matching client object and output
            flight = tq.getSingleResult();
            System.out.println(flight.getFlightCall() + " \t| " +
                    flight.getScheduleId() + " \t| " + flight.getFlightStatusId());
        } catch (NoResultException ex) {
            ex.printStackTrace();
        } finally {
            em.close();
        }
    }

    public static void getFlights() {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();

        // the lowercase c refers to the object
        // :custID is a parameterized query thats value is set below
        String strQuery = "SELECT f FROM Flight f WHERE f.flightCall IS NOT NULL";

        // Issue the query and get a matching Client
        TypedQuery<Flight> tq = em.createQuery(strQuery, Flight.class);
        List<Flight> flights;
        try {
            // Get matching client object ant output
            flights = tq.getResultList();
            flights.forEach(flight -> System.out.println(flight.getFlightCall() + " \t| " +
                            flight.getScheduleId() + " \t| " + flight.getFlightStatusId()));
        } catch (NoResultException ex) {
            ex.printStackTrace();
        } finally {
            em.close();
        }
    }

    public static void changeFlight(Long flightCall, Long scheduleID, Long flightStatusID) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;

        Flight flight = null;
        try {
            // Get transaction and start
            et = em.getTransaction();
            et.begin();

            // Find client and make changes
            flight = em.find(Flight.class, flightCall);
            flight.setScheduleId(scheduleID);
            flight.setFlightStatusId(flightStatusID);

            // Save the client object
            em.merge(flight);
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

    public static void deleteFlight(Long flightCall) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;
        Flight flight = null;

        try {
            et = em.getTransaction();
            et.begin();
            flight = em.find(Flight.class, flightCall);
            em.remove(flight);
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