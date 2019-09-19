package com.saint.anthony.hibernateHomework.testEntities;

import com.saint.anthony.hibernateHomework.entity.AircraftCreator;
import javax.persistence.*;
import java.util.List;

public class TestAircraftCreator {
    private static final EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence
            .createEntityManagerFactory("hibernateHomework");

    public static void main(String[] args) {
//        addAircraftCreator(5L, "Thunderstorm");
//        addAircraftCreator(6L, "F-18");
//        addAircraftCreator(7L, "De Halivan");
//        addAircraftCreator(8L, "Tupolev");
//        addAircraftCreator(9L, "Concorde");


//        changeModel(5L, "EuroFighter");
//        getAircraftCreator(3L);
//        deleteAircraftCreator(5L);
//        deleteAircraftCreator(6L);
//        deleteAircraftCreator(7L);
//        deleteAircraftCreator(8L);
//        deleteAircraftCreator(9L);
        getAircraftCreators();

        ENTITY_MANAGER_FACTORY.close();
    }

    public static void addAircraftCreator(Long id, String name) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;

        try {
            // Get transaction and start
            et = em.getTransaction();
            et.begin();

            // Create and set values for new client
            AircraftCreator aircraftCreator = new AircraftCreator();
            aircraftCreator.setId(id);
            aircraftCreator.setName(name);

            // Save the client object
            em.persist(aircraftCreator);
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

    public static void getAircraftCreator(Long id) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();

        // the lowercase c refers to the object
        // :custID is a parameterized query thats value is set below
        String query = "SELECT ac FROM AircraftCreator ac WHERE ac.id = :aircraft_creator_id";

        // Issue the query and get a matching Client
        TypedQuery<AircraftCreator> tq = em.createQuery(query, AircraftCreator.class);
        tq.setParameter("aircraft_creator_id", id);

        AircraftCreator aircraftCreator = null;
        try {
            // Get matching client object and output
            aircraftCreator = tq.getSingleResult();
            System.out.println(aircraftCreator.getId() + " \t| " + aircraftCreator.getName());
        } catch (NoResultException ex) {
            ex.printStackTrace();
        } finally {
            em.close();
        }
    }

    public static void getAircraftCreators() {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();

        // the lowercase c refers to the object
        // :custID is a parameterized query thats value is set below
        String strQuery = "SELECT ac FROM AircraftCreator ac WHERE ac.id IS NOT NULL";

        // Issue the query and get a matching Client
        TypedQuery<AircraftCreator> tq = em.createQuery(strQuery, AircraftCreator.class);
        List<AircraftCreator> aircraftCreators;
        try {
            // Get matching client object ant output
            aircraftCreators = tq.getResultList();
            aircraftCreators.forEach(aircraftCreator -> System.out.println(
                    aircraftCreator.getId() + " \t| " + aircraftCreator.getName()));
        } catch (NoResultException ex) {
            ex.printStackTrace();
        } finally {
            em.close();
        }
    }

    public static void changeModel(Long id, String name) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;

        AircraftCreator aircraftCreator = null;
        try {
            // Get transaction and start
            et = em.getTransaction();
            et.begin();

            // Find client and make changes
            aircraftCreator = em.find(AircraftCreator.class, id);
            aircraftCreator.setName(name);

            // Save the client object
            em.persist(aircraftCreator);
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

    public static void deleteAircraftCreator(Long id) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;
        AircraftCreator aircraftCreator = null;

        try {
            et = em.getTransaction();
            et.begin();
            aircraftCreator = em.find(AircraftCreator.class, id);
            em.remove(aircraftCreator);
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