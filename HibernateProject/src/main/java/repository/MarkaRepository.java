package repository;


import entity.Marka;

import javax.persistence.EntityManager;
import java.util.List;

public class MarkaRepository {
    private final static String FOR_LIKE = "%";
    private final EntityManager manager;

    public MarkaRepository(EntityManager manager) {
        this.manager = manager;
    }

    public List<Marka> findByNameLike(String name) {
        return manager.createQuery("select m from Marka m where m.name like :name", Marka.class)
                .setParameter("name", FOR_LIKE + name + FOR_LIKE)
                .getResultList();
    }

    public Marka findById(Long id) {
        return manager.createQuery("select m from Marka m where m.id =:id", Marka.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    public List<Marka> findAll() {
        return manager.createQuery("select m from Marka m", Marka.class)
                .getResultList();
    }

    public void save(Marka marka) {
        manager.getTransaction().begin();
        manager.merge(marka);
        manager.getTransaction().commit();
    }

    // Method in Method
    public void delete(Long id) {
        manager.getTransaction().begin();
        manager.remove(findById(id));
        manager.getTransaction().commit();
    }
}