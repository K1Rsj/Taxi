package ua.finalproject.dao.impl;

import ua.finalproject.dao.OrderDao;
import ua.finalproject.dao.mapper.OrderMapper;
import ua.finalproject.model.entities.impl.Order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
                .prepareStatement("INSERT INTO orders(departure_street, destination_street, cars_id, users_id, car_type_id, price) " +
                        "VALUES (?,?,?,?,?,?)")) {
            orderMapper.setValuesForQuery(order, preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<Order> findById(Integer integer) {
        return Optional.empty();
    }

    @Override
    public Optional<List<Order>> findAll() {
        return Optional.empty();
    }

    @Override
    public void update(Order entity) {

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
