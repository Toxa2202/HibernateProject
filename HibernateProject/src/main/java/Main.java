import entity.Board;
import entity.Car;
import entity.Marka;
import entity.Person;
import repository.BoardRepository;
import repository.CarRepository;
import repository.MarkaRepository;
import repository.PersonRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Scanner;

public class Main {
    private static boolean mainStatus = true;
    private static boolean authStatus = true;
    private static Scanner sc = new Scanner(System.in);
    private static Scanner scLine = new Scanner(System.in);

    private static void authenticatedPerson(EntityManager manager, Person person) {
        System.out.println();
        while (authStatus) {
            System.out.println("Choose MENU item:" +
                    "\n1. Get list of CARS" +
                    "\n2. Add new CAR" +
                    "\n3. Delete CAR" +
                    "\n4. Put the CAR for SALE" +
                    "\n5. See my CARs for SALE" +
                    "\n6. See all CARs for SALE" +
                    "\n7. Buy some CAR" +
                    "\n8. Exit from your account");
            int menuPicker = sc.nextInt();
            switch (menuPicker) {
                case 1: {
                    seeCars(manager, person);
                    break;
                }
                case 2: {
                    addCar(manager, person);
                    break;
                }
                case 3: {
                    removeCar(manager, person);
                    break;
                }
                case 4: {
                    putCarForSale(manager, person);
                    break;
                }
                case 5: {
                    seeMyCarsForSale(manager, person);
                    break;
                }
                case 6: {
                    seeCarsForSale(manager, person);
                    break;
                }
                case 7: {
                    buyCar(manager, person);
                    break;
                }
                case 8: {
                    authStatus = false;
                    System.out.println("Choose another option!");
                    break;
                }
                default: {
                    System.out.println("This item does not exist...");
                }
            }
        }
    }

    private static void seeCarsForSale(EntityManager manager, Person person) {
        BoardRepository boardRepository = new BoardRepository(manager);
        if (boardRepository.findAll().isEmpty()) {
            System.out.println("list is empty!");
        } else {
            System.out.println(boardRepository.findAll());
        }
    }

    private static void seeMyCarsForSale(EntityManager manager, Person person) {
        if (person.getBoards().isEmpty()) {
            System.out.println("List is empty. Add some car!");
        } else {
            System.out.println(person.getBoards());
        }
    }

    private static void buyCar(EntityManager manager, Person person) {
        if (person.getBoards().isEmpty()) {
            System.out.println("List is empty. Try again later...");
        } else {
            seeCarsForSale(manager, person);
            System.out.println("Enter ID of Car to buy: ");
            Long boardIdToBuy = sc.nextLong();
            BoardRepository boardRepository = new BoardRepository(manager);
            PersonRepository personRepository = new PersonRepository(manager);
            Board board = new Board();
            Car car = boardRepository.findById(boardIdToBuy).getCar();
            Long idOfPerson = boardRepository.findById(boardIdToBuy).getPerson().getId();
            // add car to new owner
            personRepository.addCarToPerson(person.getId(), car);
            personRepository.deleteCarFromPerson(idOfPerson, car);
            // delete from Person_Board
            personRepository.deleteBoardFromPerson(idOfPerson, boardRepository.findById(boardIdToBuy));
            //delete board value from desk
            boardRepository.delete(boardIdToBuy);
            System.out.println("Car was successfully bought!");
        }
    }

    private static void putCarForSale(EntityManager manager, Person person) {
        if (person.getCars().isEmpty()) {
            System.out.println("List is empty. First add some car to your list.");
        } else {
            System.out.println("Description: ");
            String description = scLine.nextLine();
            System.out.println("Price: ");
            Integer price = sc.nextInt();
            System.out.println("ID of your Car: ");
            Long carID = sc.nextLong();
            Board board = new Board();
            board.setDescription(description);
            board.setPrice(price);
            CarRepository carRepository = new CarRepository(manager);
            board.setCar(carRepository.findById(carID));
            board.setPerson(person);
            PersonRepository personRepository = new PersonRepository(manager);
            personRepository.addBoardToPerson(person.getId(), board);
            System.out.println("Car is ready for SALE!");
        }
    }

    private static void removeCar(EntityManager manager, Person person) {
        if (person.getCars().isEmpty()) {
            System.out.println("List is empty. Add some Car!");
        } else {
            seeCars(manager, person);
            System.out.println("Enter ID of Car to delete: ");
            Long carIdToDelete = sc.nextLong();
            CarRepository carRepository = new CarRepository(manager);
            PersonRepository personRepository = new PersonRepository(manager);
            personRepository.deleteCarFromPerson(person.getId(), carRepository.findById(carIdToDelete));
            System.out.println("Car was deleted!");
        }
    }

    private static void addCar(EntityManager manager, Person person) {
        System.out.println("Model: ");
        String model = sc.next();
        System.out.println("Power: ");
        Integer power = sc.nextInt();
        System.out.println("Marka: ");
        String newMarka = sc.next();
        Car car = new Car();
        Marka marka = new Marka();
        car.setModel(model);
        car.setPower(power);
        marka.setName(newMarka);
        car.setMarka(marka);
        MarkaRepository markaRepository = new MarkaRepository(manager);
        markaRepository.save(marka);
        CarRepository carRepository = new CarRepository(manager);
        carRepository.save(car);
        PersonRepository personRepository = new PersonRepository(manager);
        personRepository.addCarToPerson(person.getId(), car);
        System.out.println("Car saved!");
    }

    private static void seeCars(EntityManager manager, Person person) {
        if (person.getCars().isEmpty()) {
            System.out.println("List is empty. Add some car!");
        } else {
            System.out.println(person.getCars());
        }
    }

    private static void login(EntityManager manager) {
        System.out.println("Login: ");
        String login = sc.next();
        System.out.println("Password: ");
        String password = sc.next();
        PersonRepository personRepository = new PersonRepository(manager);
        Person person = personRepository.findPersonByLoginAndPassword(login, password);
        if (person != null) {
            authenticatedPerson(manager, person);
        } else {
            System.out.println("Try again!");
        }
    }


    private static void register(EntityManager manager) {
        System.out.println("___Registration___");
        System.out.println("Enter\nName-->\nSurname-->\nLogin-->\nPassword-->\n");
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
        System.out.println("Registration complete!");
    }

    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("blablabla");
        EntityManager manager = factory.createEntityManager();

        while (mainStatus) {
            System.out.println("Choose MENU item:\n1. Login\n2. Registration\n3. Exit");
            int menuPicker = sc.nextInt();
            switch (menuPicker) {
                case 1: {
                    login(manager);
                    break;
                }
                case 2: {
                    register(manager);
                    break;
                }
                case 3: {
                    mainStatus = false;
                    System.out.println("Have a nice day!");
                    break;
                }
                default: {
                    System.out.println("This item does not exist...");
                }
            }
        }

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
 * // домашка
 * // Описати в гібернейті всю базу. Для всіх crud операції, findAll, findById.
 * // Робимо пакет окремий - репозиторій, до кожного 5 методів.
 */

// домашка 18.09.2019
// дописати цю програму, додати аутентікейтед, переглядати машини, додавати, видаляти машини