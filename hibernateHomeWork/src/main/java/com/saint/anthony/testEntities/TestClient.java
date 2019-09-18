package com.saint.anthony.testEntities;

import com.saint.anthony.entity.Client;

import javax.persistence.*;
import java.util.List;

public class TestClient {
    private static final EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence
            .createEntityManagerFactory("hibernateHomework");

    public static void main(String[] args) {
//        addClient(11, "Robert", "n/a", "Levandovsky", 48488488111L,
//                "robert@gmail.com", "PL-023412", "PL");
//        addClient(12,"Marco", "n/a", "Vovchok", 380984881112L,
//                "marko@gmail.com", "UA-234412", "UA");
//        addClient(13,"Maria", "n/a", "Lopez", 41488488433L,
//                "maria@gmail.com", "ES-893412", "ES");
//        addClient(14,"Christopher", "Anatolijovych", "Prytula", 380964884325L,
//                "ukrainec@gmail.com", "UA-112232", "UA");
//        addClient(15,"Marius", "n/a", "Bogdan", 1488488111L,
//                "marius@gmail.com", "US-045412", "US");

//        getClient(5);

//        getClients();

//        changeFirstName(11, "Oleg");
//        getClient(11);

        deleteClient(15);
        getClients();

        ENTITY_MANAGER_FACTORY.close();
    }

    public static void addClient(Integer id, String firstName, String middleName, String lastName,
                                 Long phone, String email, String passport, String iataCountryCode) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;

        try {
            // Get transaction and start
            et = em.getTransaction();
            et.begin();

            // Create and set values for new client
            Client client1 = new Client();
            client1.setId(id);
            client1.setFirstName(firstName);
            client1.setMiddleName(middleName);
            client1.setLastName(lastName);
            client1.setPhone(phone);
            client1.setEmail(email);
            client1.setPassport(passport);
            client1.setIataCountryCode(iataCountryCode);

            // Save the client object
            em.persist(client1);
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

    public static void getClient(Integer id) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();

        // the lowercase c refers to the object
        // :custID is a parameterized query thats value is set below
        String query = "SELECT c FROM Client c WHERE c.id = :client_id";

        // Issue the query and get a matching Client
        TypedQuery<Client> tq = em.createQuery(query, Client.class);
        tq.setParameter("client_id", id);

        Client client = null;
        try {
            // Get matching client object and output
            client = tq.getSingleResult();
            System.out.println(client.getFirstName() + " " + client.getMiddleName() + " " +
                    client.getLastName() + " " + client.getPhone() + " " + client.getEmail() + " " +
                    client.getPassport() + " " + client.getIataCountryCode());
        } catch (NoResultException ex) {
            ex.printStackTrace();
        } finally {
            em.close();
        }
    }

    public static void getClients() {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();

        // the lowercase c refers to the object
        // :custID is a parameterized query thats value is set below
        String strQuery = "SELECT c FROM Client c WHERE c.id IS NOT NULL";

        // Issue the query and get a matching Client
        TypedQuery<Client> tq = em.createQuery(strQuery, Client.class);
        List<Client> clients;
        try {
            // Get matching client object ant output
            clients = tq.getResultList();
            clients.forEach(client -> System.out.println(
                    client.getFirstName() + " " +
                    client.getMiddleName() + " " +
                    client.getLastName() + " " +
                    client.getPhone() + " " +
                    client.getEmail() + " " +
                    client.getPassport() + " " +
                    client.getIataCountryCode()));
        } catch (NoResultException ex) {
            ex.printStackTrace();
        } finally {
            em.close();
        }
    }

    public static void changeFirstName(Integer id, String firstName) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;

        Client client = null;
        try {
            // Get transaction and start
            et = em.getTransaction();
            et.begin();

            // Find client and make changes
            client = em.find(Client.class, id);
            client.setFirstName(firstName);

            // Save the client object
            em.persist(client);
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

    public static void deleteClient(Integer id) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;
        Client client = null;

        try {
            et = em.getTransaction();
            et.begin();
            client = em.find(Client.class, id);
            em.remove(client);
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
