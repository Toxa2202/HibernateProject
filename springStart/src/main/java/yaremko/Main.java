package yaremko;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import yaremko.classes.DeliveryService;

public class Main {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = new ClassPathXmlApplicationContext(
                "/META-INF/applicationContext.xml");


        DeliveryService deliveryService1 = context.getBean(DeliveryService.class);
        deliveryService1.deliverGoods();
        System.out.println("current delivery service uuid " + deliveryService1.getUuid());

        DeliveryService deliveryService2 = context.getBean(DeliveryService.class);
        deliveryService1.deliverGoods();
        System.out.println("current delivery service uuid " + deliveryService2.getUuid());
        context.close();
    }
}


// First scope of bin is singleton!!!
// UUID - random String generator method
// це старий підхід. новий підхід - конфігурація з джава
/** Spring in Action - книга по спрігну */

// Домашка
// був світчер і електрісіті консюмер. Переписати все на спрінг за даним прикладом.
