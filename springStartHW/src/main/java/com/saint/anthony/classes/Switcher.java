package com.saint.anthony.classes;

import com.saint.anthony.interfaces.ElectricityConsumer;
import java.util.UUID;

/**
 * Created by anton.sviatov on 14.08.2019.
 */

// Створюємо певний вимикач, який постачатиме напругу споживачам
    // for ElectricityConsumers...

public class Switcher {
    private ElectricityConsumer electricityConsumer;
    private String name;
    private String uuid;

    public void turnOnDevices() {
        electricityConsumer.start();
    }

    private void createSwitcher() {
        this.uuid = UUID.randomUUID().toString();
        System.out.println("Start device with name: " + this.name);
    }

    private void deathSwitcher() {
        System.out.println("Turn off device with name: " + this.name);
    }

    public ElectricityConsumer getElectricityConsumer() {
        return electricityConsumer;
    }

    public void setElectricityConsumer(ElectricityConsumer electricityConsumer) {
        this.electricityConsumer = electricityConsumer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}
