package com.saint.anthony.hibernateHomework.testEntities;

import com.saint.anthony.hibernateHomework.entity.AircraftInstance;
import javax.persistence.*;
import java.util.List;

public class TestAircraftInstance {
    private static final EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence
            .createEntityManagerFactory("hibernateHomework");

    public static void main(String[] args) {
//        addAircraftInstance(22L, 2L);
//        addAircraftInstance(23L, 2L);
//        addAircraftInstance(24L, 2L);
//        addAircraftInstance(25L, 2L);


//        changeAircraftId(22L, 1L);
//        getAircraftInstance(21L);
//        deleteAircraftInstance(22L);
//        deleteAircraftInstance(23L);
//        deleteAircraftInstance(24L);
//        deleteAircraftInstance(25L);
        getAircraftInstances();

        ENTITY_MANAGER_FACTORY.close();
    }

    public static void addAircraftInstance(Long id, Long aircraftId) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;

        try {
            // Get transaction and start
            et = em.getTransaction();
            et.begin();

            // Create and set values for new client
            AircraftInstance aircraftInstance = new AircraftInstance();
            aircraftInstance.setAircraftInstanceId(id);
            aircraftInstance.setAircraftId(aircraftId);

            // Save the client object
            em.persist(aircraftInstance);
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

    public static void getAircraftInstance(Long id) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();

        // the lowercase c refers to the object
        // :custID is a parameterized query thats value is set below
        String query = "SELECT ai FROM AircraftInstance ai WHERE ai.aircraftInstanceId = :aircraft_instance_id";

        // Issue the query and get a matching Client
        TypedQuery<AircraftInstance> tq = em.createQuery(query, AircraftInstance.class);
        tq.setParameter("aircraft_instance_id", id);

        AircraftInstance aircraftInstance = null;
        try {
            // Get matching client object and output
            aircraftInstance = tq.getSingleResult();
            System.out.println(
                    aircraftInstance.getAircraftInstanceId() + " \t| " +
                    aircraftInstance.getAircraftId());
        } catch (NoResultException ex) {
            ex.printStackTrace();
        } finally {
            em.close();
        }
    }

    public static void getAircraftInstances() {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();

        // the lowercase c refers to the object
        // :custID is a parameterized query thats value is set below
        String strQuery = "SELECT ai FROM AircraftInstance ai WHERE ai.aircraftInstanceId IS NOT NULL";

        // Issue the query and get a matching Client
        TypedQuery<AircraftInstance> tq = em.createQuery(strQuery, AircraftInstance.class);
        List<AircraftInstance> aircraftInstances;
        try {
            // Get matching client object ant output
            aircraftInstances = tq.getResultList();
            aircraftInstances.forEach(aircraftInstance -> System.out.println(
                    aircraftInstance.getAircraftInstanceId() + " \t| " +
                            aircraftInstance.getAircraftId()));
        } catch (NoResultException ex) {
            ex.printStackTrace();
        } finally {
            em.close();
        }
    }

    public static void changeAircraftId(Long id, Long aircraftId) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;

        AircraftInstance aircraftInstance = null;
        try {
            // Get transaction and start
            et = em.getTransaction();
            et.begin();

            // Find client and make changes
            aircraftInstance = em.find(AircraftInstance.class, id);
            aircraftInstance.setAircraftId(aircraftId);

            // Save the client object
            em.persist(aircraftInstance);
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

    public static void deleteAircraftInstance(Long id) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;
        AircraftInstance aircraftInstance = null;

        try {
            et = em.getTransaction();
            et.begin();
            aircraftInstance = em.find(AircraftInstance.class, id);
            em.remove(aircraftInstance);
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