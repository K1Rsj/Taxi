package ua.finalproject.model.dao;

import ua.finalproject.model.entities.full.Car;

import java.util.Optional;

public interface CarDao extends Dao<Car, Integer> {

    /**
     * Gets free car by the car type id
     * @param typeId id of the car type
     * @return car that is free and matches the car type
     */
    Optional<Car> getFreeCarByTypeId(Integer typeId);

    /**
     * Updates car state
     * @param carId id of the car
     * @param carState new car state
     */
    void updateCarState(Integer carId, String carState);
}
