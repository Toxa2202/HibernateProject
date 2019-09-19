package com.saint.anthony.hibernateHomework.testEntities;

import com.saint.anthony.hibernateHomework.entity.AircraftSeat;
import javax.persistence.*;
import java.util.List;

public class TestAircraftSeat {
    private static final EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence
            .createEntityManagerFactory("hibernateHomework");

    public static void main(String[] args) {
//        addAircraftSeat(65L, 4L, 27L, 2L);
//        addAircraftSeat(66L, 4L, 26L, 2L);
//        addAircraftSeat(67L, 4L, 25L, 2L);
//        addAircraftSeat(68L, 4L, 24L, 2L);


//        changeSeatId(66L, 22L);
//        deleteAircraftSeat(65L);
//        deleteAircraftSeat(66L);
//        deleteAircraftSeat(67L);
//        deleteAircraftSeat(68L);
        getAircraftSeats();
//        getAircraftSeat(67L);

        ENTITY_MANAGER_FACTORY.close();
    }

    public static void addAircraftSeat(Long id, Long aircraftId, Long seatId, Long travelClassId) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;

        try {
            // Get transaction and start
            et = em.getTransaction();
            et.begin();

            // Create and set values for new client
            AircraftSeat aircraftSeat = new AircraftSeat();
            aircraftSeat.setId(id);
            aircraftSeat.setAircraftId(aircraftId);
            aircraftSeat.setSeatId(seatId);
            aircraftSeat.setTravelClassId(travelClassId);

            // Save the client object
            em.persist(aircraftSeat);
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

    public static void getAircraftSeat(Long id) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();

        // the lowercase c refers to the object
        // :custID is a parameterized query thats value is set below
        String query = "SELECT a FROM AircraftSeat a WHERE a.id = :id";

        // Issue the query and get a matching Client
        TypedQuery<AircraftSeat> tq = em.createQuery(query, AircraftSeat.class);
        tq.setParameter("id", id);

        AircraftSeat aircraftSeat = null;
        try {
            // Get matching client object and output
            aircraftSeat = tq.getSingleResult();
            System.out.println(aircraftSeat.getId() + " \t| " +
                    aircraftSeat.getAircraftId() + " \t| " +
                    aircraftSeat.getSeatId() + " \t| " +
                    aircraftSeat.getTravelClassId());
        } catch (NoResultException ex) {
            ex.printStackTrace();
        } finally {
            em.close();
        }
    }

    public static void getAircraftSeats() {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();

        // the lowercase c refers to the object
        // :custID is a parameterized query thats value is set below
        String strQuery = "SELECT a FROM AircraftSeat a WHERE a.id IS NOT NULL";

        // Issue the query and get a matching Client
        TypedQuery<AircraftSeat> tq = em.createQuery(strQuery, AircraftSeat.class);
        List<AircraftSeat> aircraftSeats;
        try {
            // Get matching client object ant output
            aircraftSeats = tq.getResultList();
            aircraftSeats.forEach(aircraftSeat -> System.out.println(
                    aircraftSeat.getId() + " \t| " +
                    aircraftSeat.getAircraftId() + " \t| " +
                    aircraftSeat.getSeatId() + " \t| " +
                    aircraftSeat.getTravelClassId()));
        } catch (NoResultException ex) {
            ex.printStackTrace();
        } finally {
            em.close();
        }
    }

    public static void changeSeatId(Long id, Long seatId) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;

        AircraftSeat aircraftSeat = null;
        try {
            // Get transaction and start
            et = em.getTransaction();
            et.begin();

            // Find client and make changes
            aircraftSeat = em.find(AircraftSeat.class, id);
            aircraftSeat.setSeatId(seatId);

            // Save the client object
            em.merge(aircraftSeat);
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

    public static void deleteAircraftSeat(Long id) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;
        AircraftSeat aircraftSeat = null;

        try {
            et = em.getTransaction();
            et.begin();
            aircraftSeat = em.find(AircraftSeat.class, id);
            em.remove(aircraftSeat);
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