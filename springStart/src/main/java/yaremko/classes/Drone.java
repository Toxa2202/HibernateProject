package yaremko.classes;

import yaremko.interfaces.Provider;

public class Drone implements Provider {
    public void deliver() {
        System.out.println("I am drone.\nI delivered goods very fast.");
    }
}
