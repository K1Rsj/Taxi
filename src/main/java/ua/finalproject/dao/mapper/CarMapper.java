package ua.finalproject.dao.mapper;

import ua.finalproject.dao.util.UtilDao;
import ua.finalproject.model.entities.impl.Car;
import ua.finalproject.model.entities.impl.CarType;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

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
    public void setValuesForQuery(Car car, PreparedStatement preparedStatement) {

    }

    @Override
    public Car makeUnique(Map<Integer, Car> cache, Car car) {
        return null;
    }
}
