package com.saint.anthony.hibernateHomework.testEntities;

import com.saint.anthony.hibernateHomework.entity.FlightStatus;
import javax.persistence.*;
import java.util.List;

public class TestFlightStatus {
    private static final EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence
            .createEntityManagerFactory("hibernateHomework");

    public static void main(String[] args) {
//        addFlightStatus(13L, "Unknown", "Test status");
//        changeFlightStatus(13L, "new Test");
//        deleteFlightStatus(13L);
//        getFlightStatus(1L);
        getFlightStatuses();

        ENTITY_MANAGER_FACTORY.close();
    }

    public static void addFlightStatus(Long flightStatusID, String name, String details) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;

        try {
            // Get transaction and start
            et = em.getTransaction();
            et.begin();

            // Create and set values for new client
            FlightStatus flightStatus = new FlightStatus();
            flightStatus.setFlightStatusId(flightStatusID);
            flightStatus.setFlightStatusName(name);
            flightStatus.setFlightStatusDetails(details);

            // Save the client object
            em.persist(flightStatus);
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

    public static void getFlightStatus(Long flightStatusID) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();

        // the lowercase c refers to the object
        // :custID is a parameterized query thats value is set below
        String query = "SELECT f FROM FlightStatus f WHERE f.flightStatusId = :flight_status_id";

        // Issue the query and get a matching Client
        TypedQuery<FlightStatus> tq = em.createQuery(query, FlightStatus.class);
        tq.setParameter("flight_status_id", flightStatusID);

        FlightStatus flightStatus = null;
        try {
            // Get matching client object and output
            flightStatus = tq.getSingleResult();
            System.out.println(flightStatus.getFlightStatusId() + " \t| " +
                    flightStatus.getFlightStatusName() + " \t| " +
                    flightStatus.getFlightStatusDetails());
        } catch (NoResultException ex) {
            ex.printStackTrace();
        } finally {
            em.close();
        }
    }

    public static void getFlightStatuses() {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();

        // the lowercase c refers to the object
        // :custID is a parameterized query thats value is set below
        String strQuery = "SELECT f FROM FlightStatus f WHERE f.flightStatusId IS NOT NULL";

        // Issue the query and get a matching Client
        TypedQuery<FlightStatus> tq = em.createQuery(strQuery, FlightStatus.class);
        List<FlightStatus> flightStatuses;
        try {
            // Get matching client object ant output
            flightStatuses = tq.getResultList();
            flightStatuses.forEach(flightStatus -> System.out.println(
                    flightStatus.getFlightStatusId() + " \t| " + flightStatus.getFlightStatusName() + " \t| " +
                            flightStatus.getFlightStatusDetails()));
        } catch (NoResultException ex) {
            ex.printStackTrace();
        } finally {
            em.close();
        }
    }

    public static void changeFlightStatus(Long flightStatusID, String newName) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;

        FlightStatus flightStatus = null;
        try {
            // Get transaction and start
            et = em.getTransaction();
            et.begin();

            // Find client and make changes
            flightStatus = em.find(FlightStatus.class, flightStatusID);
            flightStatus.setFlightStatusName(newName);

            // Save the client object
            em.merge(flightStatus);
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

    public static void deleteFlightStatus(Long flightStatusID) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;
        FlightStatus flightStatus = null;

        try {
            et = em.getTransaction();
            et.begin();
            flightStatus = em.find(FlightStatus.class, flightStatusID);
            em.remove(flightStatus);
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