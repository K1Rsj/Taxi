package ua.finalproject.model.services;

import org.junit.Before;
import org.junit.Test;
import ua.finalproject.model.dao.CarDao;
import ua.finalproject.model.dao.connection.pool.ConnectionPoolHolder;
import ua.finalproject.model.dao.impl.CarDaoImpl;
import ua.finalproject.model.entities.full.Car;
import ua.finalproject.model.services.impl.CarServiceImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class CarServiceImplTest {

    private CarServiceImpl carServiceImpl;
    private Connection connection;
    private CarDao carDao;
    private PreparedStatement preparedStatement;
    private List<Car> cars;
    private ResultSet resultSet;

    @Before
    public void setUp() {
        preparedStatement = mock(PreparedStatement.class);
        connection = ConnectionPoolHolder.getConnection();

        resultSet = mock(ResultSet.class);

        carDao = mock(CarDaoImpl.class);
        carServiceImpl = mock(CarServiceImpl.class);
        cars = new ArrayList<>();
    }

    @Test
    public void showAllCars() {
//        when(connection.prepareStatement(DbSQLQueries
// .SELECT_FROM_CARS_LEFT_JOIN_CAR_TYPE))
// .thenReturn(preparedStatement);
//        when(preparedStatement.executeQuery()).thenReturn(resultSet);
//        when(resultSet.next()).thenReturn(false);
        //when(AbstractJDBCDaoFactory.getInstance().createCarDao(connection))
        // .thenReturn(carDao);
        //when(carDao.findAll()).thenReturn(Optional.of(cars));
        when(carServiceImpl.showAllCars()).thenReturn(Optional.of(cars));
        List<Car> carsTest = carServiceImpl.showAllCars().get();
        assertEquals(cars, carsTest);
        //verify(carServiceImpl).showAllUsers();
    }

    @Test
    public void changeCarStateFromBusyToFree() {
    }

    @Test
    public void addCar() {
    }
}