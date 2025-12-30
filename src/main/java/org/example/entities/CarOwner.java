package org.example.entities;

import jakarta.persistence.*;

@Entity
public class CarOwner {

    @Id
    @GeneratedValue
    @Column(name = "car_id")
    private long carId;

    @Column(name = "owner_name")
    private String name;

    //for bidirectional mapping of one-one relation
    @OneToOne
    private Car car;


    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public long getCarId() {
        return carId;
    }

    public void setCarId(long carId) {
        this.carId = carId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
