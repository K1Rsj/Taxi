package ua.finalproject.model.services;

import ua.finalproject.model.entities.full.Car;
import ua.finalproject.model.entities.full.Order;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.Optional;

public interface CarService extends Service {

    Optional<List<Car>> showAllCars();
    void changeCarStateFromBusyToFree(Order order);
    void addCar(Car carFromRequest, String type) throws
            SQLIntegrityConstraintViolationException;
}
