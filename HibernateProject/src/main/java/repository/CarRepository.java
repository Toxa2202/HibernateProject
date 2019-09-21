package repository;


import entity.Car;
import javax.persistence.EntityManager;
import java.util.List;

public class CarRepository {
    private final static String FOR_LIKE = "%";
    private final EntityManager manager;


    public CarRepository(EntityManager manager) {
        this.manager = manager;
    }

    public List<Car> findAll() {
        return manager.createQuery("select c from Car c", Car.class)
                .getResultList();
    }

    // JOIN Zverny Uvagu
    public List<Car> findAllByMarkaAndModel(Long markaId, String modelValue) {
        return manager.createQuery("select c from Car c join c.marka m where m.id =:markaId " +
                "and c.model like :model", Car.class)
                .setParameter("markaId", markaId)
                .setParameter("model", FOR_LIKE + modelValue + FOR_LIKE)
                .getResultList();
    }

    public Car findById(Long id) {
        return manager.createQuery("select c from Car c where c.id =:id", Car.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    public void save(Car car) {
        manager.getTransaction().begin();
        manager.merge(car);
        manager.getTransaction().commit();
    }

    public void delete(Long id) {
        manager.getTransaction().begin();
        manager.remove(findById(id));
        manager.getTransaction().commit();
    }
}