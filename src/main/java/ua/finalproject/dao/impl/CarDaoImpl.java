package ua.finalproject.dao.impl;

import ua.finalproject.dao.CarDao;
import ua.finalproject.dao.mapper.CarMapper;
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
}
