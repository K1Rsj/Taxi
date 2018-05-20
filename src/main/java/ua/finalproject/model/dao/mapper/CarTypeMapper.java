package ua.finalproject.model.dao.mapper;

import ua.finalproject.constants.db.TableColumnNames;
import ua.finalproject.model.entities.impl.CarType;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CarTypeMapper implements ObjectMapper<CarType> {
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

    @Override
    public void setValuesForQuery(CarType carType, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setString(1, carType.getType());
        preparedStatement.setInt(2, carType.getStartingPrice());
        preparedStatement.setInt(3, carType.getPricePerKilometer());
        preparedStatement.setInt(4, carType.getDiscount());
    }
}