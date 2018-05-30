package ua.finalproject.model.dao.impl;

import ua.finalproject.constants.db.DbQueries;
import ua.finalproject.constants.db.TableColumnNames;
import ua.finalproject.constants.db.TableNames;
import ua.finalproject.constants.messages.LogMessages;
import ua.finalproject.model.dao.AbstractDao;
import ua.finalproject.model.dao.CarDao;
import ua.finalproject.model.dao.util.QueryContainer;
import ua.finalproject.model.dao.util.UtilDao;
import ua.finalproject.model.entities.full.Car;
import ua.finalproject.model.entities.lazy.CarLazy;
import ua.finalproject.util.LogMessageBuilder;

import java.sql.*;
import java.util.Optional;

/**
 * Implementation for car dao
 */
public class CarDaoImpl extends AbstractDao<Car> implements CarDao {

    public CarDaoImpl(String tableName, Connection connection) {
        super(tableName, connection);
    }

    /**
     * Extracts car from result set
     *
     * @param resultSet result set
     * @return car from result set
     * @throws SQLException if something went wrong in DB
     */
    @Override
    public Car extractFromResultSet(ResultSet resultSet) throws SQLException {
        Integer id = resultSet.getInt(TableColumnNames.ID_CAR);
        String model = resultSet.getString(TableColumnNames.MODEL);
        String number = resultSet.getString(TableColumnNames.NUMBER);
        Car.State state = UtilDao.parseCarState(resultSet.getString(TableColumnNames.STATE));
        String driver = resultSet.getString(TableColumnNames.DRIVER);

        return new CarLazy.CarBuilder()
                .setId(id)
                .setModel(model)
                .setNumber(number)
                .setState(state)
                .setDriver(driver)
                .buildLazy();
    }

    /**
     * Adds car to DB
     *
     * @param car car that will be added to DB
     * @throws SQLIntegrityConstraintViolationException if car number already exists in DB
     */
    @Override
    public void create(Car car) throws SQLIntegrityConstraintViolationException {
        try (PreparedStatement preparedStatement = connection
                .prepareStatement(DbQueries.INSERT_INTO_CARS)) {
            preparedStatement.setString(1, car.getModel());
            preparedStatement.setString(2, car.getNumber());
            preparedStatement.setString(3, car.getDriver());
            preparedStatement.setInt(4, car.getCarType().getId());
            preparedStatement.executeUpdate();
        } catch (SQLIntegrityConstraintViolationException e) {
            throw new SQLIntegrityConstraintViolationException(e);
        } catch (SQLException e) {
            logger.error(LogMessageBuilder.INSTANCE.createEntryError(TableNames.CARS), e
                    .getMessage());
            throw new RuntimeException(e);
        }
    }

    /**
     * Gets free car by the car type id
     *
     * @param typeId id of the car type
     * @return car that is free and matches the car type
     */
    @Override
    public Optional<Car> getFreeCarByTypeId(Integer typeId) {
        return findOneByQuery(QueryContainer.INSTANCE.findFreeCarByType(typeId));
    }

    /**
     * Updates car state
     *
     * @param carId    id of the car
     * @param carState new car state
     */
    @Override
    public void updateCarState(Integer carId, String carState) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(DbQueries
                .UPDATE_CAR_STATE_BY_ID)) {
            preparedStatement.setString(1, carState);
            preparedStatement.setInt(2, carId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error(LogMessages.UPDATE_CAR_STATE_ERROR, e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
