package com.saint.anthony.hibernateHomework.testEntities;

import com.saint.anthony.hibernateHomework.entity.Booking;

import javax.persistence.*;
import java.util.List;

public class TestBooking {
    private static final EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence
            .createEntityManagerFactory("hibernateHomework");

    public static void main(String[] args) {
//        addBooking(3L, 5L, 1L, 23L);
//        changeBooking(3L, 4L);
//        deleteBooking(3L);
//        getBooking(4L);
        getBookings();

        ENTITY_MANAGER_FACTORY.close();
    }

    public static void addBooking(Long clientId, Long flightCall, Long aircraftId, Long seatId) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;

        try {
            // Get transaction and start
            et = em.getTransaction();
            et.begin();

            // Create and set values for new client
            Booking booking = new Booking();
            booking.setClientId(clientId);
            booking.setFlightCall(flightCall);
            booking.setAircraftId(aircraftId);
            booking.setSeatId(seatId);

            // Save the client object
            em.persist(booking);
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

    public static void getBooking(Long clientId) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();

        // the lowercase c refers to the object
        // :custID is a parameterized query thats value is set below
        String query = "SELECT b FROM Booking b WHERE b.clientId = :client_id";

        // Issue the query and get a matching Client
        TypedQuery<Booking> tq = em.createQuery(query, Booking.class);
        tq.setParameter("client_id", clientId);

        Booking booking = null;
        try {
            // Get matching client object and output
            booking = tq.getSingleResult();
            System.out.println(booking.getClientId() + " \t| " +
                    booking.getFlightCall() + " \t| " +
                    booking.getAircraftId() + " \t| " +
                    booking.getSeatId());
        } catch (NoResultException ex) {
            ex.printStackTrace();
        } finally {
            em.close();
        }
    }

    public static void getBookings() {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();

        // the lowercase c refers to the object
        // :custID is a parameterized query thats value is set below
        String strQuery = "SELECT b FROM Booking b WHERE b.clientId IS NOT NULL";

        // Issue the query and get a matching Client
        TypedQuery<Booking> tq = em.createQuery(strQuery, Booking.class);
        List<Booking> bookings;
        try {
            // Get matching client object ant output
            bookings = tq.getResultList();
            bookings.forEach(booking -> System.out.println(
                    booking.getClientId() + " \t| " +
                            booking.getFlightCall() + " \t| " +
                            booking.getAircraftId() + " \t| " +
                            booking.getSeatId()));
        } catch (NoResultException ex) {
            ex.printStackTrace();
        } finally {
            em.close();
        }
    }

    public static void changeBooking(Long clientId, Long flightCall) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;

        Booking booking = null;
        try {
            // Get transaction and start
            et = em.getTransaction();
            et.begin();

            // Find client and make changes
            booking = em.find(Booking.class, clientId);
            booking.setFlightCall(flightCall);

            // Save the client object
            em.persist(booking);
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

    public static void deleteBooking(Long clientId) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;
        Booking booking = null;

        try {
            et = em.getTransaction();
            et.begin();
            booking = em.find(Booking.class, clientId);
            em.remove(booking);
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