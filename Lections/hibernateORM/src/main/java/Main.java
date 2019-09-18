import entity.Car;
import entity.Marka;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        EntityManagerFactory factory =
                Persistence.createEntityManagerFactory("blablabla");
        EntityManager manager = factory.createEntityManager();
        manager.getTransaction().begin();





//        Marka marka = new Marka();
//        marka.setName("BMW");
//        manager.persist(marka);

//        Car newCar1 = new Car();
//        newCar1.setModel("M4");
//        newCar1.setPower(450);
//
//        Car newCar2 = new Car();
//        newCar2.setModel("540i");
//        newCar2.setPower(340);
//
//        manager.persist(newCar1);
//        manager.persist(newCar2);
//
//        newCar1.setMarka(marka);
//        newCar2.setMarka(marka);
//        manager.persist(newCar1);
//        manager.persist(newCar2);
//        marka.getCars().add(newCar1);
//        marka.getCars().add(newCar2);
//        manager.merge(marka);


        Marka marka =  manager.createQuery("select m from Marka m where m.id=:id",Marka.class)
                .setParameter("id",1L)
                .getSingleResult();
        marka.getCars().forEach(manager::remove);
        manager.remove(marka);




        manager.getTransaction().commit();
        manager.close();
        factory.close();


//        Car newCar = new Car();
//        newCar.setModel("Audi S4");
//        newCar.setPower(400);
//        manager.persist(newCar);

//        manager.createQuery("select c from Car c", Car.class)
//                .getResultList()
//                .forEach(System.out::println);

//        Scanner sc = new Scanner(System.in);
//        System.out.println("ВВедіть ІД автомобіля який хочете дістати з бази: ");
//        long id = sc.nextLong();
//
//        Car carById = manager.createQuery("select c from Car c where c.id =:id", Car.class)
//                .setParameter("id", id)
//                .getSingleResult();
//
//        System.out.println(carById);
//        manager.remove(carById);
//        carById.setPower(500);


//        Car newCar = new Car();
//        newCar.setName("AUDI s4");
//        newCar.setPower(370);
//        manager.persist(newCar);

//        List<Car> cars = manager.createQuery("select c from Car c", Car.class).getResultList();
//        for (Car car : cars) {
//            System.out.println(car);
//        }

//        manager.createQuery("select c from Car c where c.name like :name", Car.class)
//                .setParameter("name", "%m4%")
//                .getResultList().forEach(manager::remove);
//
//        manager.createQuery("select c from Car c", Car.class)
//                .getResultList()
//                .forEach(System.out::println);


//        Marka markaBMW = new Marka();
//        markaBMW.setName("BMW");
//        Marka markaAudi = new Marka();
//        markaAudi.setName("AUDI");
//        manager.persist(markaAudi);
//        manager.persist(markaBMW);
//
//
//        Car newCar = new Car();
//        newCar.setMarka(markaBMW);
//        newCar.setModel("M4");
//        newCar.setPower(370);
//        manager.persist(newCar);


    }

}
