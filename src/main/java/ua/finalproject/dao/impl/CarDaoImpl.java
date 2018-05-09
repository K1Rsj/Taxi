package ua.finalproject.dao.impl;

import ua.finalproject.dao.CarDao;
import ua.finalproject.dao.mapper.CarMapper;
import ua.finalproject.dao.util.UtilDao;
import ua.finalproject.model.entities.impl.Car;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CarDaoImpl implements CarDao {

    private Connection connection;

    public CarDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(Car car) {

    }

    @Override
    public Optional<Car> findById(Integer integer) {
        return Optional.empty();
    }

    @Override
    public Optional<List<Car>> findAll() {
        List<Car> allCars = new ArrayList<>();
        CarMapper carMapper = new CarMapper();
        try (PreparedStatement ps = connection.prepareStatement("SELECT cars.id, model, number, state," +
                " driver, type FROM CARS LEFT JOIN car_type on cars.car_type_id = car_type.id");
             ResultSet resultSet = ps.executeQuery()) {
            while (resultSet.next()) {
                allCars.add(carMapper.extractFromResultSet(resultSet));
            }
            return Optional.of(allCars);
        } catch (SQLException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    @Override
    public void update(Car car) {

    }

    @Override
    public void delete(Integer integer) {

    }

    @Override
    public void deleteByParameter(String parameterName, String parameterValue) {

    }

    @Override
    public void close() throws Exception {
        connection.close();
    }

    @Override
    public List<Car> getFreeCarByType(Car.Type type) {
        CarMapper carMapper = new CarMapper();
        List<Car> allCars = new ArrayList<>();
        Integer idType = UtilDao.parseCarType(type);
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT cars.id, cars.model, number, state, driver, car_type_id FROM CARS LEFT JOIN " +
                "car_type on cars.car_type_id = car_type.id WHERE type = ? AND state = 'free'")) {
            preparedStatement.setInt(1, idType);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                allCars.add(carMapper.extractFromResultSet(resultSet));
            }
            return allCars;
        } catch (SQLException e) {
            e.printStackTrace();
            return allCars;
        }
    }

    @Override
    public void updateCarState(Integer carId, String carState) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("UPDATE cars set state = ? where id = ?")) {
            preparedStatement.setString(1, carState);
            preparedStatement.setInt(2, carId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
