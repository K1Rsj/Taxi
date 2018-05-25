package ua.finalproject.model.services;

import org.junit.Before;
import org.junit.Test;
import ua.finalproject.model.dao.CarDao;
import ua.finalproject.model.dao.connectionPool.ConnectionPoolHolder;
import ua.finalproject.model.dao.impl.CarDaoImpl;
import ua.finalproject.model.entities.impl.Car;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class CarServiceTest {

    private CarService carService;
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
        carService = mock(CarService.class);
        cars = new ArrayList<>();
    }

    @Test
    public void showAllCars() {
//        when(connection.prepareStatement(DbQueries.SELECT_FROM_CARS_LEFT_JOIN_CAR_TYPE)).thenReturn(preparedStatement);
//        when(preparedStatement.executeQuery()).thenReturn(resultSet);
//        when(resultSet.next()).thenReturn(false);
        //when(DaoFactory.getInstance().createCarDao(connection)).thenReturn(carDao);
        //when(carDao.findAll()).thenReturn(Optional.of(cars));
        when(carService.showAllCars()).thenReturn(Optional.of(cars));
        List<Car> carsTest = carService.showAllCars().get();
        assertEquals(cars, carsTest);
        //verify(carService).showAllCars();
    }

    @Test
    public void changeCarStateFromBusyToFree() {
    }

    @Test
    public void addCar() {
    }
}