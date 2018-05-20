package ua.finalproject.model.dao.mapper;

import ua.finalproject.constants.db.TableColumnNames;
import ua.finalproject.model.entities.impl.Car;
import ua.finalproject.model.entities.impl.CarType;
import ua.finalproject.model.entities.impl.Order;
import ua.finalproject.model.entities.impl.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderMapper implements ObjectMapper<Order> {
    @Override
    public Order extractFromResultSet(ResultSet resultSet) throws SQLException {
        CarMapper carMapper = new CarMapper();
        UserMapper userMapper = new UserMapper();
        CarTypeMapper carTypeMapper = new CarTypeMapper();
        Integer id = resultSet.getInt(TableColumnNames.ID_ORDER);
        String departureStreet = resultSet.getString(TableColumnNames.DEPARTURE_STREET);
        String destinationStreet = resultSet.getString(TableColumnNames.DESTINATION_STREET);
        Car car = carMapper.extractFromResultSet(resultSet);
        User user = userMapper.extractFromResultSet(resultSet);
        CarType carType = carTypeMapper.extractFromResultSet(resultSet);
        Long price = resultSet.getLong(TableColumnNames.PRICE);

        return new Order.OrderBuilder()
                .setId(id)
                .setDepartureStreet(departureStreet)
                .setDestinationStreet(destinationStreet)
                .setCar(car)
                .setUser(user)
                .setCarType(carType)
                .setPrice(price)
                .build();
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
}
