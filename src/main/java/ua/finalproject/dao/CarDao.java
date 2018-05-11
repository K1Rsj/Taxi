package ua.finalproject.dao;

import ua.finalproject.model.entities.impl.Car;

import java.util.Optional;

public interface CarDao extends Dao<Car, Integer> {
    Optional<Car> getFreeCarByTypeId(Integer typeId);
    void updateCarState(Integer carId, String carState);
}
