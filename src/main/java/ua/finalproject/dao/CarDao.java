package ua.finalproject.dao;

import ua.finalproject.model.entities.impl.Car;

import java.util.List;

public interface CarDao extends Dao<Car, Integer> {
    List<Car> getFreeCarByType(Car.Type type);
    void updateCarState(Integer carId, String carState);
}
