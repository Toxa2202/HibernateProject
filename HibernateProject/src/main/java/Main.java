import entity.Car;
import entity.Marka;
import entity.Person;
import repository.PersonRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Scanner;

public class Main {
    private static boolean status = true;
    private static Scanner sc = new Scanner(System.in);

    private static void authenticatedPerson(EntityManager manager, Person person) {
        System.out.println("In progress");
    }

    private static void login(EntityManager manager) {
        System.out.println("Login: ");
        String login = sc.next();
        System.out.println("Password: ");
        String password = sc.next();
        PersonRepository personRepository = new PersonRepository(manager);
        Person person = personRepository.findPersonBLoginAndPassword(login, password);
        if (person != null) {
            authenticatedPerson(manager, person);
        } else {
            System.out.println("Try again!");
        }
    }


    private static void register(EntityManager manager) {
        System.out.println("Реєстрація");
        System.out.println("Введіть \nім'я\nПрізвище\nЛогін\nПароль\n");
        String firstName = sc.next();
        String lastName = sc.next();
        String login = sc.next();
        String password = sc.next();
        Person person = new Person();
        person.setFirstName(firstName);
        person.setLastName(lastName);
        person.setLogin(login);
        person.setPassword(password);
        PersonRepository personRepository = new PersonRepository(manager);
        personRepository.save(person);
        System.out.println("Ви зареєстровані");
    }

    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("blablabla");
        EntityManager manager = factory.createEntityManager();
//        manager.getTransaction().begin();

        while (status) {
            System.out.println("Оберіть пункт меню\n1.Логін\n2.Реєстрація\n3.Вихід");
            int menuPicker = sc.nextInt();
            switch (menuPicker) {
                case 1:{
                    login(manager);
                    break;
                }
                case 2:{
                    register(manager);
                    break;
                }
                case 3:{
                    status = false;
                    System.out.println("Pa-Pa");
                    break;
                }
                default:{
                    System.out.println("Цього пункту не існує");
                }
            }
        }


//        manager.getTransaction().commit();
        manager.close();
        factory.close();


        //        Car newCar = new Car();
//        newCar.setModel("BMW X3");
//        newCar.setPower(350);
//
//        // Persist always create new unit
//        manager.persist(newCar);

        // Merge can update old unit
//        manager.merge(newCar);

        // hql and jpql - work with database in hibernate, work as with objects
//        manager.createQuery("select c from Car c", Car.class)
//                .getResultList()
//                .forEach(System.out::println);

//        Scanner sc = new Scanner(System.in);
//        System.out.println("Enter ID of the car which you want to get from DB: ");
//        Long id = sc.nextLong();
//
//        Car carById = manager.createQuery("select c from Car c where c.id =:id", Car.class)
//                .setParameter("id", id)
//                .getSingleResult();
//        System.out.println(carById);

        // Can update this ID, works in two threads at the same time
//        carById.setPower(500);

        // remove object from db
//        manager.remove(carById);


        // видалити всі записи при умові що під персоном є підвязані інші кари.
        // Вказуємо умову видалення вручну, інакше не видалиться
//        Marka marka = manager.createQuery("select m from Marka m where m.id=:id", Marka.class)
//                .setParameter("id", 1L)
//                .getSingleResult();
//        marka.getCars().forEach(manager::remove);
//        manager.remove(marka);

    }

}

/**
// домашка
// Описати в гібернейті всю базу. Для всіх crud операції, findAll, findById.
// Робимо пакет окремий - репозиторій, до кожного 5 методів.
 */


// дописати цю програму, додати аутентікейтед, переглядати машини, додавати, видаляти машини