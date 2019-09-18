package repository;

import entity.Car;
import entity.Person;

import javax.persistence.EntityManager;
import java.util.List;

public class PersonRepository {
    private final static String FOR_LIKE = "%";
    private final EntityManager manager;

    public PersonRepository(EntityManager manager) {
        this.manager = manager;
    }

    public List<Person> findAll() {
        return manager.createQuery("select p from Person p", Person.class)
                .getResultList();
    }

    public Person findPersonBLoginAndPassword(String loginValue, String passwordValue) {
        return manager.createQuery("select p from Person p where p.login =:loginValue and p.password =:passwordValue", Person.class)
                .setParameter("loginValue", loginValue)
                .setParameter("passwordValue", passwordValue)
                .getSingleResult();
    }

    public Person findById(Long id) {
        return manager.createQuery("select p from Person p where p.id=:id", Person.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    public void save(Person person) {
        manager.getTransaction().begin();
        manager.merge(person);
        manager.getTransaction().commit();
    }

    public void addCarToPerson(Long personId, Car car) {
        Person person = findById(personId);
        person.getCars().add(car);
        save(person);
    }

}
