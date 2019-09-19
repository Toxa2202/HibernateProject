package com.saint.anthony.hibernateHomework.testEntities;

import com.saint.anthony.hibernateHomework.entity.FlightAircraftInstance;
import javax.persistence.*;
import java.util.List;

public class TestFlightAircraftInstance {
    private static final EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence
            .createEntityManagerFactory("hibernateHomework");

    public static void main(String[] args) {
//        addFlightAircraftInstance(5L, 115L);
//        changeFlightAircraftInstance(5L, 116L);
//        deleteFlightAircraftInstance(5L);
//        getFlightAircraftInstance(1L);
        getFlightAircraftInstances();

        ENTITY_MANAGER_FACTORY.close();
    }

    public static void addFlightAircraftInstance(Long flightCall, Long aircraftInstanceID) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;

        try {
            // Get transaction and start
            et = em.getTransaction();
            et.begin();

            // Create and set values for new client
            FlightAircraftInstance flightAircraftInstance = new FlightAircraftInstance();
            flightAircraftInstance.setFlightCall(flightCall);
            flightAircraftInstance.setAircraftInstanceId(aircraftInstanceID);

            // Save the client object
            em.persist(flightAircraftInstance);
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

    public static void getFlightAircraftInstance(Long flightCall) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();

        // the lowercase c refers to the object
        // :custID is a parameterized query thats value is set below
        String query = "SELECT f FROM FlightAircraftInstance f WHERE f.flightCall = :flight_call";

        // Issue the query and get a matching Client
        TypedQuery<FlightAircraftInstance> tq = em.createQuery(query, FlightAircraftInstance.class);
        tq.setParameter("flight_call", flightCall);

        FlightAircraftInstance flightAircraftInstance = null;
        try {
            // Get matching client object and output
            flightAircraftInstance = tq.getSingleResult();
            System.out.println(flightAircraftInstance.getFlightCall() + " \t| " +
                    flightAircraftInstance.getAircraftInstanceId());
        } catch (NoResultException ex) {
            ex.printStackTrace();
        } finally {
            em.close();
        }
    }

    public static void getFlightAircraftInstances() {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();

        // the lowercase c refers to the object
        // :custID is a parameterized query thats value is set below
        String strQuery = "SELECT f FROM FlightAircraftInstance f WHERE f.flightCall IS NOT NULL";

        // Issue the query and get a matching Client
        TypedQuery<FlightAircraftInstance> tq = em.createQuery(strQuery, FlightAircraftInstance.class);
        List<FlightAircraftInstance> flightAircraftInstances;
        try {
            // Get matching client object ant output
            flightAircraftInstances = tq.getResultList();
            flightAircraftInstances.forEach(flightAircraftInstance -> System.out.println(
                    flightAircraftInstance.getFlightCall() + " \t| " +
                    flightAircraftInstance.getAircraftInstanceId()));
        } catch (NoResultException ex) {
            ex.printStackTrace();
        } finally {
            em.close();
        }
    }

    public static void changeFlightAircraftInstance(Long flightCall, Long aircraftInstanceID) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;

        FlightAircraftInstance flightAircraftInstance = null;
        try {
            // Get transaction and start
            et = em.getTransaction();
            et.begin();

            // Find client and make changes
            flightAircraftInstance = em.find(FlightAircraftInstance.class, flightCall);
            flightAircraftInstance.setAircraftInstanceId(aircraftInstanceID);

            // Save the client object
            em.merge(flightAircraftInstance);
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

    public static void deleteFlightAircraftInstance(Long flightCall) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;
        FlightAircraftInstance flightAircraftInstance = null;

        try {
            et = em.getTransaction();
            et.begin();
            flightAircraftInstance = em.find(FlightAircraftInstance.class, flightCall);
            em.remove(flightAircraftInstance);
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