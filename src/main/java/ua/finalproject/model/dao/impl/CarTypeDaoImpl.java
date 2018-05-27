package ua.finalproject.model.dao.impl;

import ua.finalproject.constants.db.DbQueries;
import ua.finalproject.constants.db.TableColumnNames;
import ua.finalproject.constants.db.TableNames;
import ua.finalproject.constants.messages.LogMessages;
import ua.finalproject.model.dao.AbstractDao;
import ua.finalproject.model.dao.CarTypeDao;
import ua.finalproject.model.entities.full.CarType;
import ua.finalproject.util.LogMessageBuilder;

import java.sql.*;

/**
 * Implementation for car type dao
 */
public class CarTypeDaoImpl extends AbstractDao<CarType> implements CarTypeDao {

    public CarTypeDaoImpl(String tableName, Connection connection) {
        super(tableName, connection);
    }

    /**
     * Extracts car type from result set
     * @param resultSet result set
     * @return car type from result set
     * @throws SQLException if something went wrong in DB
     */
    @Override
    public CarType extractFromResultSet(ResultSet resultSet) throws SQLException {
        Integer id = resultSet.getInt(TableColumnNames.ID_CAR_TYPE);
        String type = resultSet.getString(TableColumnNames.TYPE);
        Integer startingPrice = resultSet.getInt(TableColumnNames.STARTING_PRICE);
        Integer pricePerKilometer = resultSet.getInt(TableColumnNames.PRICE_PER_KM);
        Integer discount = resultSet.getInt(TableColumnNames.DISCOUNT);

        return new CarType.CarTypeBuilder()
                .setId(id)
                .setType(type)
                .setStartingPrice(startingPrice)
                .setPricePerKilometer(pricePerKilometer)
                .setDiscount(discount)
                .build();
    }

    /**
     * Adds car type to DB
     * @param carType car type that will be added to DB
     */
    @Override
    public void create(CarType carType) {
        try (PreparedStatement preparedStatement = connection
                .prepareStatement(DbQueries.INSERT_INTO_CAR_TYPE)) {
            preparedStatement.setString(1, carType.getType());
            preparedStatement.setInt(2, carType.getStartingPrice());
            preparedStatement.setInt(3, carType.getPricePerKilometer());
            preparedStatement.setInt(4, carType.getDiscount());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error(LogMessageBuilder.INSTANCE.createEntryError(TableNames.CAR_TYPE), e.getMessage());
            throw new RuntimeException(e);
        }
    }

    /**
     * Updates car type's discount
     * @param id of car type
     * @param discount of car type
     */
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
}
