package com.saint.anthony.hibernateHomework.testEntities;

import com.saint.anthony.hibernateHomework.entity.Schedule;

import javax.persistence.*;
import java.util.List;

public class TestSchedule {
    private static final EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence
            .createEntityManagerFactory("hibernateHomework");

    public static void main(String[] args) {
//        addSchedule(7270L, "LWO", "KRK",
//                "2019-11-02 15:35:00", "2019-11-02 15:35:00");
//        changeSchedule(7270L, "2019-10-02 15:35:00");
//        deleteSchedule(7270L);
//        getSchedule(7269L);
        getSchedules();

        ENTITY_MANAGER_FACTORY.close();
    }

    public static void addSchedule(Long scheduleID, String departureIATA,
                                   String arrivalIATA, String departTimeGMT, String arrivalTimeGMT) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;

        try {
            // Get transaction and start
            et = em.getTransaction();
            et.begin();

            // Create and set values for new client
            Schedule schedule = new Schedule();
            schedule.setScheduleId(scheduleID);
            schedule.setDepartureIataAirportCode(departureIATA);
            schedule.setArrivalIataAirportCode(arrivalIATA);
            schedule.setDepartureTimeGMT(departTimeGMT);
            schedule.setArrivalTimeGMT(arrivalTimeGMT);

            // Save the client object
            em.persist(schedule);
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

    public static void getSchedule(Long scheduleID) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();

        // the lowercase c refers to the object
        // :custID is a parameterized query thats value is set below
        String query = "SELECT s FROM Schedule s WHERE s.scheduleId = :schedule_id";

        // Issue the query and get a matching Client
        TypedQuery<Schedule> tq = em.createQuery(query, Schedule.class);
        tq.setParameter("schedule_id", scheduleID);

        Schedule schedule = null;
        try {
            // Get matching client object and output
            schedule = tq.getSingleResult();
            System.out.println(schedule.getScheduleId() + " \t| " +
                    schedule.getDepartureIataAirportCode() + " \t| " +
                    schedule.getArrivalIataAirportCode() + " \t| " +
                    schedule.getDepartureTimeGMT() + " \t| " +
                    schedule.getArrivalTimeGMT());
        } catch (NoResultException ex) {
            ex.printStackTrace();
        } finally {
            em.close();
        }
    }

    public static void getSchedules() {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();

        // the lowercase c refers to the object
        // :custID is a parameterized query thats value is set below
        String strQuery = "SELECT s FROM Schedule s WHERE s.scheduleId IS NOT NULL";

        // Issue the query and get a matching Client
        TypedQuery<Schedule> tq = em.createQuery(strQuery, Schedule.class);
        List<Schedule> schedules;
        try {
            // Get matching client object ant output
            schedules = tq.getResultList();
            schedules.forEach(schedule -> System.out.println(
                            schedule.getScheduleId() + " \t| " +
                            schedule.getDepartureIataAirportCode() + " \t| " +
                            schedule.getArrivalIataAirportCode() + " \t| " +
                            schedule.getDepartureTimeGMT() + " \t| " +
                            schedule.getArrivalTimeGMT()));
        } catch (NoResultException ex) {
            ex.printStackTrace();
        } finally {
            em.close();
        }
    }

    public static void changeSchedule(Long scheduleID, String departureTimeGMT) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;

        Schedule schedule = null;
        try {
            // Get transaction and start
            et = em.getTransaction();
            et.begin();

            // Find client and make changes
            schedule = em.find(Schedule.class, scheduleID);
            schedule.setDepartureTimeGMT(departureTimeGMT);

            // Save the client object
            em.persist(schedule);
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

    public static void deleteSchedule(Long scheduleID) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;
        Schedule schedule = null;

        try {
            et = em.getTransaction();
            et.begin();
            schedule = em.find(Schedule.class, scheduleID);
            em.remove(schedule);
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