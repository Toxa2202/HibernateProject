package entity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Board extends IdHolder{
    private String description;
    private Integer price;

    @ManyToOne
    private Car car;

    @ManyToOne
    private Person person;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
