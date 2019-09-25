package com.saint.anthony.classes;

import com.saint.anthony.interfaces.ElectricityConsumer;

/**
 * Created by anton.sviatov on 14.08.2019.
 */

// Конкрретний споживач імплементуєтсья від інтерфейсу
public class TV implements ElectricityConsumer {

    // Переписує метод інтерфейсу з врахуванням своїх особливостей
    public void start() {
        System.out.println("Film started");
    }
}
