package com.saint.anthony.testEntities;

import com.saint.anthony.entity.Country;
import javax.persistence.*;
import java.util.List;

public class TestCountry {
    private static final EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence
            .createEntityManagerFactory("hibernateHomework");

    public static void main(String[] args) {
//        addCountry("KZ", "Kazahstan");
//        changeCountry("KZ", "Nursultan");
//        deleteCountry("KZ");
//        getCountry("KZ");
        getCountries();

        ENTITY_MANAGER_FACTORY.close();
    }

    public static void addCountry(String countryCode, String name) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;

        try {
            // Get transaction and start
            et = em.getTransaction();
            et.begin();

            // Create and set values for new client
            Country country = new Country();
            country.setIataCountryCode(countryCode);
            country.setCountryName(name);

            // Save the client object
            em.persist(country);
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

    public static void getCountry(String countryCode) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();

        // the lowercase c refers to the object
        // :custID is a parameterized query thats value is set below
        String query = "SELECT c FROM Country c WHERE c.iataCountryCode = :iata_country_code";

        // Issue the query and get a matching Client
        TypedQuery<Country> tq = em.createQuery(query, Country.class);
        tq.setParameter("iata_country_code", countryCode);

        Country country = null;
        try {
            // Get matching client object and output
            country = tq.getSingleResult();
            System.out.println(country.getIataCountryCode() + " " +
                    country.getCountryName());
        } catch (NoResultException ex) {
            ex.printStackTrace();
        } finally {
            em.close();
        }
    }

    public static void getCountries() {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();

        // the lowercase c refers to the object
        // :custID is a parameterized query thats value is set below
        String strQuery = "SELECT c FROM Country c WHERE c.iataCountryCode IS NOT NULL";

        // Issue the query and get a matching Client
        TypedQuery<Country> tq = em.createQuery(strQuery, Country.class);
        List<Country> countries;
        try {
            // Get matching client object ant output
            countries = tq.getResultList();
            countries.forEach(country -> System.out.println(
                    country.getIataCountryCode() + " " +
                            country.getCountryName()));
        } catch (NoResultException ex) {
            ex.printStackTrace();
        } finally {
            em.close();
        }
    }

    public static void changeCountry(String countryCode, String name) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;

        Country country = null;
        try {
            // Get transaction and start
            et = em.getTransaction();
            et.begin();

            // Find client and make changes
            country = em.find(Country.class, countryCode);
            country.setCountryName(name);

            // Save the client object
            em.persist(country);
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

    public static void deleteCountry(String countryCode) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;
        Country country = null;

        try {
            et = em.getTransaction();
            et.begin();
            country = em.find(Country.class, countryCode);
            em.remove(country);
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