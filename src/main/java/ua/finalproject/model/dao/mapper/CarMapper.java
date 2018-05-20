package ua.finalproject.model.dao.mapper;

import ua.finalproject.constants.db.TableColumnNames;
import ua.finalproject.model.dao.util.UtilDao;
import ua.finalproject.model.entities.impl.Car;
import ua.finalproject.model.entities.impl.CarType;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CarMapper implements ObjectMapper<Car> {
    @Override
    public Car extractFromResultSet(ResultSet resultSet) throws SQLException {
        CarTypeMapper carTypeMapper = new CarTypeMapper();
        Integer id = resultSet.getInt(TableColumnNames.ID_CAR);
        String model = resultSet.getString(TableColumnNames.MODEL);
        String number = resultSet.getString(TableColumnNames.NUMBER);
        Car.State state = UtilDao.parseCarState(resultSet.getString(TableColumnNames.STATE));
        String driver = resultSet.getString(TableColumnNames.DRIVER);
        CarType type = carTypeMapper.extractFromResultSet(resultSet);

        return new Car.CarBuilder()
                .setId(id)
                .setModel(model)
                .setNumber(number)
                .setState(state)
                .setDriver(driver)
                .setCarType(type)
                .build();
    }

    @Override
    public void setValuesForQuery(Car car, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setString(1, car.getModel());
        preparedStatement.setString(2, car.getNumber());
        preparedStatement.setString(3, car.getDriver());
        preparedStatement.setInt(4, car.getCarType().getId());
    }
}


