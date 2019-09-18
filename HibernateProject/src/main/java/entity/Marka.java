package entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Marka extends IdHolder {

    private String name;

    @OneToMany(mappedBy = "marka")
    private List<Car> cars = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }

    @Override
    public String toString() {
        return "Marka{" +
                ", name='" + name + '\'' +
                ", cars=" + cars +
                '}';
    }
}
