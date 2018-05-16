package ua.finalproject.model.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

    private static final Logger logger = LogManager.getLogger(CarService.class);


    public Optional<List<Car>> showAllCars() {
        Connection connection = ConnectionPoolHolder.getConnection();
        try (CarDao carDao = DaoFactory.getInstance().createCarDao(connection)) {
            return carDao.findAll();
        } catch (Exception e) {
            logger.error("Show all cars error", e.getMessage());
        }
        return Optional.empty();
    }

    public void changeCarStateFromBusyToFree(Order order) {
        Connection connection = ConnectionPoolHolder.getConnection();
        try (CarDao carDao = DaoFactory.getInstance().createCarDao(connection)) {
            carDao.updateCarState(order.getCar().getId(), Car.State.FREE.toString().toLowerCase());
        } catch (Exception e) {
            logger.error("Change car state from busy to free error", e.getMessage());
        }
    }

    public void addCar(Car carFromRequest, String type) throws SQLIntegrityConstraintViolationException {
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
            throw new SQLIntegrityConstraintViolationException(e);
        } catch (Exception e) {
            logger.error("Add car error", e.getMessage());
            SQLExceptionRollbackErrorHandle(connection);
        }
    }

    private void SQLExceptionRollbackErrorHandle(Connection connection) {
        try {
            connection.rollback();
        } catch (SQLException e1) {
            logger.error("Add car connection rollback error", e1.getMessage());
        }
    }
}