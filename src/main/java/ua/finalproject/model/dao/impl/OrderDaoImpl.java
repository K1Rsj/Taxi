package ua.finalproject.model.dao.impl;

import ua.finalproject.constants.db.DbQueries;
import ua.finalproject.constants.db.TableColumnNames;
import ua.finalproject.constants.db.TableNames;
import ua.finalproject.model.dao.AbstractDao;
import ua.finalproject.model.dao.OrderDao;
import ua.finalproject.model.dao.util.QueryContainer;
import ua.finalproject.model.entities.full.Order;
import ua.finalproject.model.entities.lazy.OrderLazy;
import ua.finalproject.util.LogMessageBuilder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

/**
 * Implementation for order dao
 */
public class OrderDaoImpl extends AbstractDao<Order> implements OrderDao {

    public OrderDaoImpl(String tableName, Connection connection) {
        super(tableName, connection);
    }

    /**
     * Extracts order from result set
     *
     * @param resultSet result set
     * @return order from result set
     * @throws SQLException if something wrong with DB
     */
    @Override
    public Order extractFromResultSet(ResultSet resultSet) throws
            SQLException {

        Integer id = resultSet.getInt(TableColumnNames.ID_ORDER);
        String departureStreet = resultSet.getString(TableColumnNames
                .DEPARTURE_STREET);
        String destinationStreet = resultSet.getString(TableColumnNames
                .DESTINATION_STREET);
        Long price = resultSet.getLong(TableColumnNames.PRICE);

        return new OrderLazy.OrderBuilder()
                .setId(id)
                .setDepartureStreet(departureStreet)
                .setDestinationStreet(destinationStreet)
                .setPrice(price)
                .buildLazy();
    }

    /**
     * Adds order to DB
     *
     * @param order order that will be added to DB
     */
    @Override
    public void create(Order order) {
        try (PreparedStatement preparedStatement = connection
                .prepareStatement(DbQueries.INSERT_INTO_ORDERS)) {
            preparedStatement.setString(1, order.getDepartureStreet());
            preparedStatement.setString(2, order.getDestinationStreet());
            preparedStatement.setInt(3, order.getCar().getId());
            preparedStatement.setInt(4, order.getUser().getId());
            preparedStatement.setInt(5, order.getCarType().getId());
            preparedStatement.setLong(6, order.getPrice());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error(LogMessageBuilder.INSTANCE.createEntryError
                    (TableNames.ORDERS), e
                    .getMessage());
            throw new RuntimeException(e);
        }
    }

    /**
     * Finds all user's orders
     *
     * @param id user's id
     * @return list of all user's orders
     */
    @Override
    public Optional<List<Order>> findOrdersByUserId(Integer id) {
        return findAllByQuery(QueryContainer.INSTANCE.
                findOrdersByUserId(id));
    }
}
