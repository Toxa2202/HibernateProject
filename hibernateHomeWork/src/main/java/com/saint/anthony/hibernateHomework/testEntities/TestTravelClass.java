package com.saint.anthony.hibernateHomework.testEntities;

import com.saint.anthony.hibernateHomework.entity.TravelClass;

import javax.persistence.*;
import java.util.List;

public class TestTravelClass {
    private static final EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence
            .createEntityManagerFactory("hibernateHomework");

    public static void main(String[] args) {
//        addTravelClass(3L, "First Class", "The best we can offer!");
//        changeTravelClass(3L, "Super Class");
        deleteTravelClass(3L);
//        getTravelClass(3L);
        getTravelClasses();

        ENTITY_MANAGER_FACTORY.close();
    }

    public static void addTravelClass(Long travelClassID, String name, String description) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;

        try {
            // Get transaction and start
            et = em.getTransaction();
            et.begin();

            // Create and set values for new client
            TravelClass travelClass = new TravelClass();
            travelClass.setTravelClassId(travelClassID);
            travelClass.setTravellClassName(name);
            travelClass.setTravelClassDescription(description);

            // Save the client object
            em.persist(travelClass);
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

    public static void getTravelClass(Long travelClassID) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();

        // the lowercase c refers to the object
        // :custID is a parameterized query thats value is set below
        String query = "SELECT t FROM TravelClass t WHERE t.travelClassId = :travel_class_id";

        // Issue the query and get a matching Client
        TypedQuery<TravelClass> tq = em.createQuery(query, TravelClass.class);
        tq.setParameter("travel_class_id", travelClassID);

        TravelClass travelClass = null;
        try {
            // Get matching client object and output
            travelClass = tq.getSingleResult();
            System.out.println(travelClass.getTravelClassId() + " \t| " +
                    travelClass.getTravellClassName() + " \t| " +
                    travelClass.getTravelClassDescription());
        } catch (NoResultException ex) {
            ex.printStackTrace();
        } finally {
            em.close();
        }
    }

    public static void getTravelClasses() {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();

        // the lowercase c refers to the object
        // :custID is a parameterized query thats value is set below
        String strQuery = "SELECT t FROM TravelClass t WHERE t.travelClassId IS NOT NULL";

        // Issue the query and get a matching Client
        TypedQuery<TravelClass> tq = em.createQuery(strQuery, TravelClass.class);
        List<TravelClass> travelClasses;
        try {
            // Get matching client object ant output
            travelClasses = tq.getResultList();
            travelClasses.forEach(travelClass -> System.out.println(
                            travelClass.getTravelClassId() + " \t| " +
                            travelClass.getTravellClassName() + " \t| " +
                            travelClass.getTravelClassDescription()));
        } catch (NoResultException ex) {
            ex.printStackTrace();
        } finally {
            em.close();
        }
    }

    public static void changeTravelClass(Long travelClassID, String name) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;

        TravelClass travelClass = null;
        try {
            // Get transaction and start
            et = em.getTransaction();
            et.begin();

            // Find client and make changes
            travelClass = em.find(TravelClass.class, travelClassID);
            travelClass.setTravellClassName(name);

            // Save the client object
            em.persist(travelClass);
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

    public static void deleteTravelClass(Long travelClassID) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;
        TravelClass travelClass = null;

        try {
            et = em.getTransaction();
            et.begin();
            travelClass = em.find(TravelClass.class, travelClassID);
            em.remove(travelClass);
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