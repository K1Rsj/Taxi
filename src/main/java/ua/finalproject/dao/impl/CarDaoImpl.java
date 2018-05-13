package ua.finalproject.dao.impl;

import ua.finalproject.dao.CarDao;
import ua.finalproject.dao.mapper.CarMapper;
import ua.finalproject.model.entities.impl.Car;

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
                .prepareStatement("INSERT INTO CARS(model, number, driver, car_type_id) " +
                        "VALUES (?,?,?,?) ")) {
            carMapper.setValuesForQuery(car, preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLIntegrityConstraintViolationException e) {
            e.printStackTrace();
            throw new SQLIntegrityConstraintViolationException(e);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<Car> findById(Integer id) {
        CarMapper carMapper = new CarMapper();
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM CARS WHERE id_car = ?")) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return Optional.of(carMapper.extractFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Optional<List<Car>> findAll() {
        List<Car> allCars = new ArrayList<>();
        CarMapper carMapper = new CarMapper();
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT *" +
                "  FROM CARS LEFT JOIN car_type on car_type_id = id_car_type");
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                allCars.add(carMapper.extractFromResultSet(resultSet));
            }
            return Optional.of(allCars);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public void delete(Integer id) {
        try (PreparedStatement preparedStatement = connection
                .prepareStatement("DELETE FROM CARS WHERE id_car = ?")) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<Car> getFreeCarByTypeId(Integer typeId) {
        CarMapper carMapper = new CarMapper();
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM CARS LEFT JOIN " +
                "car_type on car_type_id = id_car_type WHERE car_type_id = ? AND state = 'free'")) {
            preparedStatement.setInt(1, typeId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return Optional.of(carMapper.extractFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public void updateCarState(Integer carId, String carState) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("UPDATE cars set state = ? where id_car = ?")) {
            preparedStatement.setString(1, carState);
            preparedStatement.setInt(2, carId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void close() throws Exception {
        connection.close();
    }
}
