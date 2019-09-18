package entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Car extends IdHolder {

    private String model;
    private Integer power;

    @ManyToMany(mappedBy = "cars")
    private List<Person> persons = new ArrayList<>();

    @ManyToOne
    private Marka marka;

    public Car() {
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Integer getPower() {
        return power;
    }

    public void setPower(Integer power) {
        this.power = power;
    }

    @Override
    public String toString() {
        return "Car{" +
                ", model='" + model + '\'' +
                ", power=" + power +
                '}';
    }
}
