package yaremko.classes;

import yaremko.interfaces.Provider;

public class Courier implements Provider {
    public void deliver() {
        System.out.println("I am courier.\nI delivered goods really slow.");
    }
}

// Class connects to interface and overrides his method