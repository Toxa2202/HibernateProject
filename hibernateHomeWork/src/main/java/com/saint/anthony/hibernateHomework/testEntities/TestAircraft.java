package com.saint.anthony.hibernateHomework.testEntities;

import com.saint.anthony.hibernateHomework.entity.Aircraft;
import javax.persistence.*;
import java.util.List;

public class TestAircraft {
    private static final EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence
            .createEntityManagerFactory("hibernateHomework");

    public static void main(String[] args) {
//        addAircraft(3L, 2, "321");
//        addAircraft(4L, 2, "330");
//        addAircraft(5L, 1, "777 Dreamliner");
//        addAircraft(6L, 1, "737-900");
//        addAircraft(7L, 4, "AN-225");


//        changeModel(3L, "320");
//        getAircraft(3L);
//        deleteAircraft(3L);
        getAircrafts();

        ENTITY_MANAGER_FACTORY.close();
    }

    public static void addAircraft(Long id, Integer airCreatorId, String model) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;

        try {
            // Get transaction and start
            et = em.getTransaction();
            et.begin();

            // Create and set values for new client
            Aircraft aircraft = new Aircraft();
            aircraft.setId(id);
            aircraft.setAirCreatorId(airCreatorId);
            aircraft.setModel(model);

            // Save the client object
            em.persist(aircraft);
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

    public static void getAircraft(Long id) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();

        // the lowercase c refers to the object
        // :custID is a parameterized query thats value is set below
        String query = "SELECT a FROM Aircraft a WHERE a.id = :aircraft_id";

        // Issue the query and get a matching Client
        TypedQuery<Aircraft> tq = em.createQuery(query, Aircraft.class);
        tq.setParameter("aircraft_id", id);

        Aircraft aircraft = null;
        try {
            // Get matching client object and output
            aircraft = tq.getSingleResult();
            System.out.println(aircraft.getAirCreatorId() + " \t| " + aircraft.getModel());
        } catch (NoResultException ex) {
            ex.printStackTrace();
        } finally {
            em.close();
        }
    }

    public static void getAircrafts() {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();

        // the lowercase c refers to the object
        // :custID is a parameterized query thats value is set below
        String strQuery = "SELECT a FROM Aircraft a WHERE a.id IS NOT NULL";

        // Issue the query and get a matching Client
        TypedQuery<Aircraft> tq = em.createQuery(strQuery, Aircraft.class);
        List<Aircraft> aircrafts;
        try {
            // Get matching client object ant output
            aircrafts = tq.getResultList();
            aircrafts.forEach(aircraft -> System.out.println(
                    aircraft.getAirCreatorId() + " \t| " + aircraft.getModel()));
        } catch (NoResultException ex) {
            ex.printStackTrace();
        } finally {
            em.close();
        }
    }

    public static void changeModel(Long id, String model) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;

        Aircraft aircraft = null;
        try {
            // Get transaction and start
            et = em.getTransaction();
            et.begin();

            // Find client and make changes
            aircraft = em.find(Aircraft.class, id);
            aircraft.setModel(model);

            // Save the client object
            em.persist(aircraft);
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

    public static void deleteAircraft(Long id) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;
        Aircraft aircraft = null;

        try {
            et = em.getTransaction();
            et.begin();
            aircraft = em.find(Aircraft.class, id);
            em.remove(aircraft);
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