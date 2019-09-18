package com.saint.anthony.testEntities;

import com.saint.anthony.entity.Direction;
import javax.persistence.*;
import java.util.List;

public class TestDirection {
    private static final EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence
            .createEntityManagerFactory("hibernateHomework");

    public static void main(String[] args) {
//        addDirection("LWO", "KBP");
//        changeDirection("LWO", "KRK", "WMI");
//        deleteDirection(39L);
//        getDirection("LWO");
        getDirections();

        ENTITY_MANAGER_FACTORY.close();
    }

    public static void addDirection(String departure, String arrival) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;

        try {
            // Get transaction and start
            et = em.getTransaction();
            et.begin();

            // Create and set values for new client
            Direction direction = new Direction();
            direction.setDepartIataAirportCode(departure);
            direction.setArrivalIataAirprotCode(arrival);

            // Save the client object
            em.persist(direction);
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

    public static void getDirection(String departure) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();

        // the lowercase c refers to the object
        // :custID is a parameterized query thats value is set below
        String query = "SELECT d FROM Direction d WHERE d.departIataAirportCode = :departure_iata_airport_code";

        // Issue the query and get a matching Client
        TypedQuery<Direction> tq = em.createQuery(query, Direction.class);
        tq.setParameter("departure_iata_airport_code", departure);

        Direction direction = null;
        try {
            // Get matching client object and output
            direction = tq.getSingleResult();
            System.out.println(direction.getDepartIataAirportCode() + " " +
                    direction.getArrivalIataAirprotCode());
        } catch (NoResultException ex) {
            ex.printStackTrace();
        } finally {
            em.close();
        }
    }

    public static void getDirections() {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();

        // the lowercase c refers to the object
        // :custID is a parameterized query thats value is set below
        String strQuery = "SELECT d FROM Direction d WHERE d.departIataAirportCode IS NOT NULL";

        // Issue the query and get a matching Client
        TypedQuery<Direction> tq = em.createQuery(strQuery, Direction.class);
        List<Direction> directions;
        try {
            // Get matching client object ant output
            directions = tq.getResultList();
            directions.forEach(direction -> System.out.println(
                    direction.getDepartIataAirportCode() + " " +
                            direction.getArrivalIataAirprotCode()));
        } catch (NoResultException ex) {
            ex.printStackTrace();
        } finally {
            em.close();
        }
    }

    public static void changeDirection(String oldDeparture, String newDeparture, String newArrival) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;

        Direction direction = null;
        try {
            // Get transaction and start
            et = em.getTransaction();
            et.begin();

            // Find client and make changes
            direction = em.find(Direction.class, oldDeparture);
            direction.setDepartIataAirportCode(newDeparture);
            direction.setArrivalIataAirprotCode(newArrival);

            // Save the client object
            em.persist(direction);
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

    public static void deleteDirection(Long id) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;
        Direction direction = null;

        try {
            et = em.getTransaction();
            et.begin();
            direction = em.find(Direction.class, id);
            em.remove(direction);
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