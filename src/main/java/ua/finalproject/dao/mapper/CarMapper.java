package ua.finalproject.dao.mapper;

import ua.finalproject.dao.util.UtilDao;
import ua.finalproject.model.entities.impl.Car;
import ua.finalproject.model.entities.impl.CarType;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CarMapper implements ObjectMapper<Car> {
    @Override
    public Car extractFromResultSet(ResultSet resultSet) throws SQLException {
        CarTypeMapper carTypeMapper = new CarTypeMapper();
        Integer id = resultSet.getInt("id_car");
        String model = resultSet.getString("model");
        String number = resultSet.getString("number");
        Car.State state = UtilDao.parseCarState(resultSet.getString("state"));
        String driver = resultSet.getString("driver");
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


