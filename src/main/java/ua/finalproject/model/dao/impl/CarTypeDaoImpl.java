package ua.finalproject.model.dao.impl;

import ua.finalproject.constants.db.DbQueries;
import ua.finalproject.constants.db.TableNames;
import ua.finalproject.constants.messages.LogMessages;
import ua.finalproject.model.dao.CarTypeDao;
import ua.finalproject.model.dao.mapper.CarTypeMapper;
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
                .prepareStatement(DbQueries.INSERT_INTO_CAR_TYPE)) {
            carTypeMapper.setValuesForQuery(carType, preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error(LogMessageBuilder.INSTANCE.createEntryError(TableNames.CAR_TYPE), e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<CarType> findById(Integer id) {
        CarTypeMapper carTypeMapper = new CarTypeMapper();
        try (PreparedStatement preparedStatement = connection.prepareStatement(DbQueries.SELECT_FROM_CAR_TYPE_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return Optional.of(carTypeMapper.extractFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            logger.error(LogMessageBuilder.INSTANCE.findByIdError(TableNames.CAR_TYPE), e.getMessage());
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }

    @Override
    public Optional<List<CarType>> findAll() {
        List<CarType> allCarTypes = new ArrayList<>();
        CarTypeMapper carTypeMapper = new CarTypeMapper();
        try (PreparedStatement preparedStatement = connection.prepareStatement(DbQueries.SELECT_ALL_FROM_CAR_TYPE);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                allCarTypes.add(carTypeMapper.extractFromResultSet(resultSet));
            }
            return Optional.of(allCarTypes);
        } catch (SQLException e) {
            logger.error(LogMessageBuilder.INSTANCE.findAllError(TableNames.CAR_TYPE), e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Integer id) {
        try (PreparedStatement preparedStatement = connection
                .prepareStatement(DbQueries.DELETE_FROM_CAR_TYPE_BY_ID)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error(LogMessageBuilder.INSTANCE.deleteEntryError(TableNames.CAR_TYPE, id), e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateDiscount(Integer id, Integer discount) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(DbQueries.UPDATE_CAR_TYPE_SET_DISCOUNT)) {
            preparedStatement.setInt(1, discount);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error(LogMessages.UPDATE_DISCOUNT_ERROR, e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            logger.error(LogMessages.CONNECTION_CLOSE_ERROR, e.getMessage());
            throw new RuntimeException(e);
        }
    }
}