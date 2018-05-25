package ua.finalproject.model.dao.impl;

import ua.finalproject.constants.db.DbQueries;
import ua.finalproject.constants.db.TableNames;
import ua.finalproject.constants.messages.LogMessages;
import ua.finalproject.model.dao.CarDao;
import ua.finalproject.model.dao.mapper.CarMapper;
import ua.finalproject.model.entities.impl.Car;
import ua.finalproject.util.LogMessageBuilder;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Implementation for car dao
 */
public class CarDaoImpl implements CarDao {

    /**
     * @see Connection
     */
    private Connection connection;

    public CarDaoImpl(Connection connection) {
        this.connection = connection;
    }

    /**
     * Adds car to DB
     * @param car car that will be added to DB
     * @throws SQLIntegrityConstraintViolationException if car number already exists in DB
     */
    @Override
    public void create(Car car) throws SQLIntegrityConstraintViolationException {
        CarMapper carMapper = new CarMapper();
        try (PreparedStatement preparedStatement = connection
                .prepareStatement(DbQueries.INSERT_INTO_CARS)) {
            carMapper.setValuesForQuery(car, preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLIntegrityConstraintViolationException e) {
            throw new SQLIntegrityConstraintViolationException(e);
        } catch (SQLException e) {
            logger.error(LogMessageBuilder.INSTANCE.createEntryError(TableNames.CARS), e.getMessage());
            throw new RuntimeException(e);
        }
    }

    /**
     * Finds car by id
     * @param id id of car
     * @return car that matches id
     */
    @Override
    public Optional<Car> findById(Integer id) {
        CarMapper carMapper = new CarMapper();
        try (PreparedStatement preparedStatement = connection.prepareStatement(DbQueries.SELECT_FROM_CARS_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return Optional.of(carMapper.extractFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            logger.error(LogMessageBuilder.INSTANCE.findByIdError(TableNames.CARS), e.getMessage());
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }

    /**
     * Finds all cars
     * @return list of all cars
     */
    @Override
    public Optional<List<Car>> findAll() {
        List<Car> allCars = new ArrayList<>();
        CarMapper carMapper = new CarMapper();
        try (PreparedStatement preparedStatement = connection.prepareStatement(DbQueries.SELECT_FROM_CARS_LEFT_JOIN_CAR_TYPE);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                allCars.add(carMapper.extractFromResultSet(resultSet));
            }
            return Optional.of(allCars);
        } catch (SQLException e) {
            logger.error(LogMessageBuilder.INSTANCE.findAllError(TableNames.CARS), e.getMessage());
            throw new RuntimeException(e);
        }
    }

    /**
     * Deletes car by id
     * @param id id of car
     */
    @Override
    public void delete(Integer id) {
        try (PreparedStatement preparedStatement = connection
                .prepareStatement(DbQueries.DELETE_FROM_CARS_BY_ID)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error(LogMessageBuilder.INSTANCE.deleteEntryError(TableNames.CARS, id), e.getMessage());
            throw new RuntimeException(e);
        }
    }

    /**
     * Gets free car by the car type id
     * @param typeId id of the car type
     * @return car that is free and matches the car type
     */
    @Override
    public Optional<Car> getFreeCarByTypeId(Integer typeId) {
        CarMapper carMapper = new CarMapper();
        try (PreparedStatement preparedStatement = connection.prepareStatement(DbQueries.SELECT_FROM_CARS_LEFT_JOIN_CAR_TYPE_BY_ID)) {
            preparedStatement.setInt(1, typeId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return Optional.of(carMapper.extractFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            logger.error(LogMessages.GET_FREE_CAR_BY_TYPE_ERROR, e.getMessage());
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }

    /**
     * Updates car state
     * @param carId id of the car
     * @param carState new car state
     */
    @Override
    public void updateCarState(Integer carId, String carState) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(DbQueries.UPDATE_CAR_STATE_BY_ID)) {
            preparedStatement.setString(1, carState);
            preparedStatement.setInt(2, carId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error(LogMessages.UPDATE_CAR_STATE_ERROR, e.getMessage());
            throw new RuntimeException(e);
        }
    }

    /**
     * Auto-closing the connection
     */
    @Override
    public void close(){
        try {
            connection.close();
        } catch (SQLException e) {
            logger.error(LogMessages.CONNECTION_CLOSE_ERROR, e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
