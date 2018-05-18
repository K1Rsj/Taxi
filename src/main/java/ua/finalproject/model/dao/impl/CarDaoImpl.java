package ua.finalproject.model.dao.impl;

import ua.finalproject.constants.DbQueries;
import ua.finalproject.model.dao.CarDao;
import ua.finalproject.model.dao.mapper.CarMapper;
import ua.finalproject.model.entities.impl.Car;
import ua.finalproject.util.LogMessageBuilder;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CarDaoImpl implements CarDao {

    private Connection connection;

    public CarDaoImpl(Connection connection) {
        this.connection = connection;
    }

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
            logger.error(LogMessageBuilder.INSTANCE.createEntryError("cars"), e.getMessage());
        }
    }

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
            logger.error(LogMessageBuilder.INSTANCE.findByIdError("cars"), e.getMessage());
        }
        return Optional.empty();
    }

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
            logger.error(LogMessageBuilder.INSTANCE.findAllError("cars"), e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public void delete(Integer id) {
        try (PreparedStatement preparedStatement = connection
                .prepareStatement(DbQueries.DELETE_FROM_CARS_BY_ID)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error(LogMessageBuilder.INSTANCE.deleteEntryError("cars", id), e.getMessage());
        }
    }

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
            logger.error("Get free car by type error", e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public void updateCarState(Integer carId, String carState) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(DbQueries.UPDATE_CAR_STATE_BY_ID)) {
            preparedStatement.setString(1, carState);
            preparedStatement.setInt(2, carId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Update car state error", e.getMessage());
        }
    }

    @Override
    public void close(){
        try {
            connection.close();
        } catch (SQLException e) {
            logger.error("Connection close error", e.getMessage());
        }
    }
}
