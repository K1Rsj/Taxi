package ua.finalproject.model.dao.impl;

import ua.finalproject.constants.db.DbQueries;
import ua.finalproject.constants.db.TableNames;
import ua.finalproject.constants.messages.LogMessages;
import ua.finalproject.model.dao.OrderDao;
import ua.finalproject.model.dao.mapper.OrderMapper;
import ua.finalproject.model.entities.impl.Order;
import ua.finalproject.util.LogMessageBuilder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrderDaoImpl implements OrderDao {

    private Connection connection;

    public OrderDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(Order order) {
        OrderMapper orderMapper = new OrderMapper();
        try (PreparedStatement preparedStatement = connection
                .prepareStatement(DbQueries.INSERT_INTO_ORDERS)) {
            orderMapper.setValuesForQuery(order, preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error(LogMessageBuilder.INSTANCE.createEntryError(TableNames.ORDERS), e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Order> findById(Integer id) {
        OrderMapper orderMapper = new OrderMapper();
        try (PreparedStatement preparedStatement = connection.prepareStatement(DbQueries.SELECT_FROM_ORDERS_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return Optional.of(orderMapper.extractFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            logger.error(LogMessageBuilder.INSTANCE.findByIdError(TableNames.ORDERS), e.getMessage());
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }

    @Override
    public Optional<List<Order>> findAll() {
        List<Order> orders = new ArrayList<>();
        OrderMapper orderMapper = new OrderMapper();
        try (PreparedStatement ps = connection.prepareStatement(DbQueries.SELECT_ALL_FROM_JOIN_ALL_TABLES);
             ResultSet resultSet = ps.executeQuery()) {
            while (resultSet.next()) {
                orders.add(orderMapper.extractFromResultSet(resultSet));
            }
            return Optional.of(orders);
        } catch (SQLException e) {
            logger.error(LogMessageBuilder.INSTANCE.findAllError(TableNames.ORDERS), e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Integer id) {
        try (PreparedStatement preparedStatement = connection
                .prepareStatement(DbQueries.DELETE_FROM_ORDERS_BY_ID)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error(LogMessageBuilder.INSTANCE.deleteEntryError(TableNames.ORDERS, id), e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<List<Order>> findOrdersByUserLogin(String login) {
        List<Order> userOrders = new ArrayList<>();
        OrderMapper orderMapper = new OrderMapper();
        try (PreparedStatement preparedStatement = connection.prepareStatement(DbQueries.SELECT_FROM_JOIN_ALL_TABLES_BY_ID)) {
            preparedStatement.setString(1, login);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                userOrders.add(orderMapper.extractFromResultSet(resultSet));
            }
            return Optional.of(userOrders);
        } catch (SQLException e) {
            logger.error(LogMessages.FIND_USER_ORDERS_ERROR, e.getMessage());
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
