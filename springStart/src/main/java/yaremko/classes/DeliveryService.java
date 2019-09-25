package yaremko.classes;

import yaremko.interfaces.Provider;
import java.util.UUID;

public class DeliveryService {
    private String name;
    private String uuid;
    private Provider provider;

    public void deliverGoods() {
        provider.deliver();
    }

    private void createDeliveryService() {
        this.uuid = UUID.randomUUID().toString();
        System.out.println("Created Delivery service with name: " + this.name);
    }

    private void deathDeliveryService() {
        System.out.println("Dropped Delivery service with name: " + this.name);
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

    public Provider getProvider() {
        return provider;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
    }
}
