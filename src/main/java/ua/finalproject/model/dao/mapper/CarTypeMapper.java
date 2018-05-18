package ua.finalproject.model.dao.mapper;

import ua.finalproject.model.entities.impl.CarType;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CarTypeMapper implements ObjectMapper<CarType> {
    @Override
    public CarType extractFromResultSet(ResultSet resultSet) throws SQLException {
        Integer id = resultSet.getInt("id_car_type");
        String type = resultSet.getString("type");
        Integer startingPrice = resultSet.getInt("starting_price");
        Integer pricePerKilometer = resultSet.getInt("price_per_km");
        Integer discount = resultSet.getInt("discount");

        return new CarType.CarTypeBuilder()
                .setId(id)
                .setType(type)
                .setStartingPrice(startingPrice)
                .setPricePerKilometer(pricePerKilometer)
                .setDiscount(discount)
                .build();
    }

    @Override
    public void setValuesForQuery(CarType carType, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setString(1, carType.getType());
        preparedStatement.setInt(2, carType.getStartingPrice());
        preparedStatement.setInt(3, carType.getPricePerKilometer());
        preparedStatement.setInt(4, carType.getDiscount());
    }
}
