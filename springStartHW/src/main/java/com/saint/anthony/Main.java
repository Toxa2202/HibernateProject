package com.saint.anthony;

import com.saint.anthony.classes.Switcher;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = new ClassPathXmlApplicationContext(
                "/META-INF/applicationContext.xml");

        Switcher switcher1 = context.getBean(Switcher.class);
        switcher1.turnOnDevices();
        System.out.println("Current device is: " + switcher1.getUuid());

        Switcher switcher2 = context.getBean(Switcher.class);
        switcher2.turnOnDevices();
        System.out.println("Current device is: " + switcher2.getUuid());
        context.close();
    }
}
