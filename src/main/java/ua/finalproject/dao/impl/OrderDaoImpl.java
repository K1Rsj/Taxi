package ua.finalproject.dao.impl;

import ua.finalproject.dao.OrderDao;
import ua.finalproject.dao.mapper.OrderMapper;
import ua.finalproject.model.entities.impl.Order;

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
                .prepareStatement("INSERT INTO orders(departure_street, destination_street, cars_id, users_id, car_type_id, price) " +
                        "VALUES (?,?,?,?,?,?)")) {
            orderMapper.setValuesForQuery(order, preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<Order> findById(Integer id) {
        OrderMapper orderMapper = new OrderMapper();
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM ORDERS WHERE id_order = ?")) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return Optional.of(orderMapper.extractFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Optional<List<Order>> findAll() {
        List<Order> orders = new ArrayList<>();
        OrderMapper orderMapper = new OrderMapper();
        try (PreparedStatement ps = connection.prepareStatement("SELECT * FROM USERS" +
                " LEFT JOIN ORDERS on id_user = users_id LEFT JOIN cars on cars_id = id_car LEFT JOIN" +
                " car_type on id_car_type = cars.car_type_id");
             ResultSet resultSet = ps.executeQuery()) {
            while (resultSet.next()) {
                orders.add(orderMapper.extractFromResultSet(resultSet));
            }
            return Optional.of(orders);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public void delete(Integer id) {
        try (PreparedStatement preparedStatement = connection
                .prepareStatement("DELETE FROM ORDERS WHERE users_id = ?")){
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<List<Order>> findOrdersByUserLogin(String login) {
        List<Order> userOrders = new ArrayList<>();
        OrderMapper orderMapper = new OrderMapper();
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM USERS" +
                " LEFT JOIN ORDERS on id_user = users_id LEFT JOIN cars on cars_id = id_car" +
                " LEFT JOIN car_type on id_car_type = cars.car_type_id WHERE login = ?")) {
            preparedStatement.setString(1, login);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                userOrders.add(orderMapper.extractFromResultSet(resultSet));
            }
            return Optional.of(userOrders);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public void close() throws Exception {
        connection.close();
    }
}
