package ua.finalproject.dao.mapper;

import ua.finalproject.model.entities.impl.CarType;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

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
    public void setValuesForQuery(CarType entity, PreparedStatement preparedStatement) {

    }

    @Override
    public CarType makeUnique(Map<Integer, CarType> cache, CarType entity) {
        return null;
    }
}
