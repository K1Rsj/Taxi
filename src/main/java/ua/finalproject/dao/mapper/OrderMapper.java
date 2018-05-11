package ua.finalproject.dao.mapper;

import ua.finalproject.dao.util.UtilDao;
import ua.finalproject.model.entities.impl.Order;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class OrderMapper implements ObjectMapper<Order> {
    @Override
    public Order extractFromResultSet(ResultSet resultSet) {
        return null;
    }

    @Override
    public void setValuesForQuery(Order order, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setString(1, order.getDepartureStreet());
        preparedStatement.setString(2, order.getDestinationStreet());
        preparedStatement.setInt(3, order.getCar().getId());
        preparedStatement.setInt(4, order.getUser().getId());
        preparedStatement.setInt(5, order.getCarType().getId());
        preparedStatement.setLong(6, order.getPrice());
    }

    @Override
    public Order makeUnique(Map<Integer, Order> cache, Order order) {
        return null;
    }
}
