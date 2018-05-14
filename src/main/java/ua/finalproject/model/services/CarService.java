package ua.finalproject.model.services;

import ua.finalproject.dao.CarDao;
import ua.finalproject.dao.CarTypeDao;
import ua.finalproject.dao.connectionPool.ConnectionPoolHolder;
import ua.finalproject.dao.factory.DaoFactory;
import ua.finalproject.dao.util.UtilDao;
import ua.finalproject.model.entities.impl.Car;
import ua.finalproject.model.entities.impl.CarType;
import ua.finalproject.model.entities.impl.Order;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
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
        try (CarDao carDao = DaoFactory.getInstance().createCarDao(connection)) {
            carDao.updateCarState(order.getCar().getId(), Car.State.FREE.toString().toLowerCase());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addCar(Car carFromRequest, String type) throws SQLException {
        Connection connection = ConnectionPoolHolder.getConnection();
        try (CarDao carDao = DaoFactory.getInstance().createCarDao(connection);
             CarTypeDao carTypeDao = DaoFactory.getInstance().createCarTypeDao(connection)) {
            connection.setAutoCommit(false);
            Optional<CarType> carType = carTypeDao.findById(UtilDao.parseTypeString(type));
            Car car = new Car.CarBuilder()
                    .setModel(carFromRequest.getModel())
                    .setNumber(carFromRequest.getNumber())
                    .setDriver(carFromRequest.getDriver())
                    .setCarType(carType.get())
                    .build();
            carDao.create(car);
            connection.commit();
        } catch (SQLIntegrityConstraintViolationException e) {
            e.printStackTrace();
            throw new SQLIntegrityConstraintViolationException(e);
        } catch (Exception e) {
            e.printStackTrace();
            connection.rollback();
        }
    }
}