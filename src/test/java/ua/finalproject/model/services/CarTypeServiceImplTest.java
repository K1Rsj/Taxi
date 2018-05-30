package ua.finalproject.model.services;

import org.junit.Before;
import org.junit.Test;
import ua.finalproject.model.dao.CarTypeDao;
import ua.finalproject.model.entities.full.CarType;
import ua.finalproject.model.services.impl.CarTypeServiceImpl;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class CarTypeServiceImplTest {

    private CarTypeServiceImpl carTypeServiceImpl;
    private CarTypeDao carTypeDao;
    private List<CarType> carTypes;

    @Before
    public void setUp() {

        carTypeDao = mock(CarTypeDao.class);
        //carTypeServiceImpl = new CarTypeServiceImpl(carTypeDao);
        carTypes = new ArrayList<>();
    }

    @Test
    public void updateDiscount() {
        Integer typeId = 1;
        Integer discount = 6;
        doNothing().when(carTypeDao).updateDiscount(typeId, discount);
        carTypeDao.updateDiscount(typeId, discount);
        verify(carTypeDao).updateDiscount(typeId, discount);

    }

    @Test
    public void showAllCarTypes() {
        when(carTypeDao.findAll()).thenReturn(Optional.of(carTypes));
        List<CarType> carTypesTest = carTypeServiceImpl.showAllCarTypes
                ().get();
        verify(carTypeDao).findAll();
        assertEquals(carTypes, carTypesTest);


    }
}