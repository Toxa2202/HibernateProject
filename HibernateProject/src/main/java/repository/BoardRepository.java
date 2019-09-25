package repository;

import entity.Board;
import javax.persistence.EntityManager;
import java.util.List;

public class BoardRepository {
    private final static String FOR_LIKE = "%";
    private final EntityManager manager;

    public BoardRepository(EntityManager manager) {
        this.manager = manager;
    }

    public List<Board> findAll() {
        return manager.createQuery("select b from Board b", Board.class)
                .getResultList();
    }

    public Board findByCarId(Long carID) {
        return manager.createQuery("select b from Board b where b.car =: carID", Board.class)
                .setParameter("carID", carID)
                .getSingleResult();
    }

    public List<Board> findAllByPrice(Long lowPrice, Long highPrice) {
        return manager.createQuery("select b from Board b where b.price <=:lowPrice " +
                "and b.price >=:highPrice", Board.class)
                .setParameter("lowPrice", lowPrice)
                .setParameter("highPrice", highPrice)
                .getResultList();
    }

    public Board findById(Long id) {
        return manager.createQuery("select b from Board b where b.id =:id", Board.class)
                .setParameter("id", id)
                .getSingleResult();
    }

//    public Board findPersonByBoardId (Long id) {
//        return manager.createQuery("select b from Board b where b.person.id =:id", Board.class)
//                .setParameter("id", id)
//                .getSingleResult();
//    }

    public void save(Board board) {
        manager.getTransaction().begin();
        manager.merge(board);
        manager.getTransaction().commit();
    }

    public void delete(Long id) {
        manager.getTransaction().begin();
        manager.remove(findById(id));
        manager.getTransaction().commit();
    }
}
