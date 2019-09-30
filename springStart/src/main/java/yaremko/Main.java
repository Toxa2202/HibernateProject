package yaremko;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import yaremko.classes.DeliveryService;

public class Main {
    public static void main(String[] args) {

        // Connect to Spring XML config file
        ConfigurableApplicationContext context = new ClassPathXmlApplicationContext(
                "/META-INF/applicationContext.xml");

        // Create object of DeliveryService class and sending him to the Spring context
        DeliveryService deliveryService1 = context.getBean(DeliveryService.class);
        // Start first delivery (we dont know which - drone or courier).
        // We must write class in xml config, which to use
        deliveryService1.deliverGoods();
        System.out.println("current delivery service uuid " + deliveryService1.getUuid());

        // Same action with second object. When Singleton - it will be one memory address
        // When scope = prototype, different stacks
        DeliveryService deliveryService2 = context.getBean(DeliveryService.class);
        deliveryService1.deliverGoods();
        System.out.println("current delivery service uuid " + deliveryService2.getUuid());
        // In the end
        context.close();
    }
}

// First scope of bin is singleton!!!
// UUID - random String generator method
// це старий підхід. новий підхід - конфігурація з джава
/** Spring in Action - книга по спрігну */

// Домашка
// був світчер і електрісіті консюмер. Переписати все на спрінг за даним прикладом.
