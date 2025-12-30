package org.example.dao;

import org.example.entities.Car;

import java.util.List;

public interface CarDAO {
    void saveCar(Car c);

    Car getCar(long id);

    List<Car> getAllCars();

    void updateCar(Car c);

    void deleteCar(long id);
}
