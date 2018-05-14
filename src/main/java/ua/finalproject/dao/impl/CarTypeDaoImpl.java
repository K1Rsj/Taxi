package ua.finalproject.dao.impl;

import ua.finalproject.dao.CarTypeDao;
import ua.finalproject.dao.mapper.CarTypeMapper;
import ua.finalproject.model.entities.impl.CarType;
import ua.finalproject.util.LogMessageBuilder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CarTypeDaoImpl implements CarTypeDao {


    private Connection connection;

    public CarTypeDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(CarType carType) {
        CarTypeMapper carTypeMapper = new CarTypeMapper();
        try (PreparedStatement preparedStatement = connection
                .prepareStatement("INSERT INTO car_type(type, starting_price, price_per_km, discount) " +
                        "VALUES (?,?,?,?) ")) {
            carTypeMapper.setValuesForQuery(carType, preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error(LogMessageBuilder.INSTANCE.createEntryError("car_type"), e.getMessage());
        }
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
            logger.error(LogMessageBuilder.INSTANCE.findByIdError("car_type"), e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public Optional<List<CarType>> findAll() {
        List<CarType> allCarTypes = new ArrayList<>();
        CarTypeMapper carTypeMapper = new CarTypeMapper();
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT *" +
                "  FROM car_type");
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                allCarTypes.add(carTypeMapper.extractFromResultSet(resultSet));
            }
            return Optional.of(allCarTypes);
        } catch (SQLException e) {
            logger.error(LogMessageBuilder.INSTANCE.findAllError("car_type"), e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public void delete(Integer id) {
        try (PreparedStatement preparedStatement = connection
                .prepareStatement("DELETE FROM car_type WHERE id_car_type = ?")) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error(LogMessageBuilder.INSTANCE.deleteEntryError("car_type", id), e.getMessage());
        }
    }

    @Override
    public void updateDiscount(Integer id, Integer discount) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("UPDATE car_type" +
                " SET discount = ? WHERE id_car_type = ?")) {
            preparedStatement.setInt(1, discount);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Update discount error", e.getMessage());
        }
    }

    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            logger.error("Connection close error", e.getMessage());
        }
    }
}
