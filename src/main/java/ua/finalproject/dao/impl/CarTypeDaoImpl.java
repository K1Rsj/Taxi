package ua.finalproject.dao.impl;

import ua.finalproject.dao.CarTypeDao;
import ua.finalproject.dao.mapper.CarTypeMapper;
import ua.finalproject.model.entities.impl.CarType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class CarTypeDaoImpl implements CarTypeDao {


    private Connection connection;

    public CarTypeDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(CarType entity) {

    }

    @Override
    public Optional<CarType> findById(Integer id) {
        CarTypeMapper carTypeMapper = new CarTypeMapper();
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM car_type WHERE id_car_type = ?")) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return Optional.of(carTypeMapper.extractFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Optional<List<CarType>> findAll() {
        return Optional.empty();
    }

    @Override
    public void update(CarType carType) {

    }

    @Override
    public void delete(Integer integer) {

    }

    @Override
    public void deleteByParameter(String parameterName, String parameterValue) {

    }

    @Override
    public void updateDiscount(Integer id, Integer discount) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("UPDATE car_type SET discount = ? WHERE id_car_type = ?")) {
            preparedStatement.setInt(1, discount);
            preparedStatement.setInt(2, id);
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
