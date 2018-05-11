package ua.finalproject.model.services;

import ua.finalproject.dao.CarDao;
import ua.finalproject.dao.connectionPool.ConnectionPoolHolder;
import ua.finalproject.dao.factory.DaoFactory;
import ua.finalproject.model.entities.impl.Car;
import ua.finalproject.model.entities.impl.Order;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public class CarService {

    public Optional<List<Car>> showAllCars() {
        Connection connection = ConnectionPoolHolder.getConnection();
        try (CarDao carDao = DaoFactory.getInstance().createCarDao(connection)) {
            return carDao.findAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public void changeCarStateFromBusyToFree(Order order) {
        Connection connection = ConnectionPoolHolder.getConnection();
        try {
            try (CarDao carDao = DaoFactory.getInstance().createCarDao(connection)) {
                carDao.updateCarState(order.getCar().getId(), Car.State.FREE.toString().toLowerCase());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}