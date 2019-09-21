package entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity()
public class Car extends IdHolder {

    private String model;
    private Integer power;

    @ManyToMany(mappedBy = "cars", cascade = CascadeType.ALL)
    private List<Person> persons = new ArrayList<>();

    @ManyToOne
    private Marka marka;

    public List<Person> getPersons() {
        return persons;
    }

    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }

    public Marka getMarka() {
        return marka;
    }

    public void setMarka(Marka marka) {
        this.marka = marka;
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
                "id=" + super.getId() +
                ", model='" + model + '\'' +
                ", power=" + power +
                '}';
    }
}
