package ua.finalproject.model.services.impl;

import ua.finalproject.constants.messages.LogMessages;
import ua.finalproject.model.dao.CarDao;
import ua.finalproject.model.dao.CarTypeDao;
import ua.finalproject.model.dao.connectionPool.ConnectionPoolHolder;
import ua.finalproject.model.dao.util.UtilDao;
import ua.finalproject.model.entities.full.Car;
import ua.finalproject.model.entities.full.CarType;
import ua.finalproject.model.entities.full.Order;
import ua.finalproject.model.services.CarService;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.Optional;

public class CarServiceImpl implements CarService {

    private CarServiceImpl() {
    }

    private static class CarServiceImplHolder {
        static final CarServiceImpl instance = new CarServiceImpl();
    }

    public static CarServiceImpl getInstance() {
        return CarServiceImplHolder.instance;
    }

    /**
     * Shows all cars
     *
     * @return list of all cars
     */
    @Override
    public Optional<List<Car>> showAllCars() {
        Connection connection = ConnectionPoolHolder.getConnection();
        try (CarDao carDao = daoFactory.createCarDao(connection)) {
            return carDao.findAll();
        } catch (Exception e) {
            logger.error(LogMessages.AUTO_CLOSABLE_RESOURCE_ERROR_IN_SHOW_ALL_CARS, e
                    .getMessage());
        }
        return Optional.empty();
    }

    /**
     * Changes car state from busy to free
     *
     * @param order user's order
     */
    @Override
    public void changeCarStateFromBusyToFree(Order order) {
        Connection connection = ConnectionPoolHolder.getConnection();
        try (CarDao carDao = daoFactory.createCarDao(connection)) {
            carDao.updateCarState(order.getCar().getId(), Car.State.FREE.toString()
                    .toLowerCase());
        } catch (Exception e) {
            logger.error(LogMessages.AUTO_CLOSABLE_RESOURCE_ERROR_IN_CHANGE_CAR_STATE, e
                    .getMessage());
        }
    }

    /**
     * Adds car to DB
     *
     * @param carFromRequest car from request
     * @param type           car type
     * @throws SQLIntegrityConstraintViolationException if number already exists in DB
     */
    @Override
    public void addCar(Car carFromRequest, String type) throws
            SQLIntegrityConstraintViolationException {
        Connection connection = ConnectionPoolHolder.getConnection();
        try (CarDao carDao = daoFactory.createCarDao(connection);
             CarTypeDao carTypeDao = daoFactory.createCarTypeDao(connection)) {
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
        } catch (SQLException e) {
            logger.error(LogMessages.ADD_CAR_ERROR, e.getMessage());
            SQLExceptionRollbackErrorHandle(connection);
            throw new RuntimeException(e);
        } catch (Exception e) {
            logger.error(LogMessages.AUTO_CLOSABLE_RESOURCE_ERROR_IN_ADD_CAR, e.getMessage());
        }
    }

    /**
     * Handles rollback error
     *
     * @param connection connection
     */
    private void SQLExceptionRollbackErrorHandle(Connection connection) {
        try {
            connection.rollback();
        } catch (SQLException e1) {
            logger.error(LogMessages.ADD_CAR_CONNECTION_ROLLBACK_ERROR, e1.getMessage());
            throw new RuntimeException(e1);
        }
    }
}