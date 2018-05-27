package ua.finalproject.controller.commands.car;

import org.junit.Before;
import org.junit.Test;
import ua.finalproject.constants.jsp.JSPPages;
import ua.finalproject.constants.jsp.RequestAttributes;
import ua.finalproject.controller.util.ControllerUtil;
import ua.finalproject.model.services.CarService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class AllCarsCommandTest {

    private AllCarsCommand allCarsCommand;
    private CarService carService;
    private HttpServletRequest request;

    @Before
    public void setUp() {
        carService = mock(CarService.class);
        allCarsCommand = new AllCarsCommand(carService);
        request = mock(HttpServletRequest.class);
    }

    @Test
    public void execute() {
        List cars = mock(List.class);
        when(carService.showAllCars()).thenReturn(Optional.of(cars));
        when(ControllerUtil.getSubListForPagination(request, cars)).thenReturn(cars);
        String page = allCarsCommand.execute(request);
        verify(request).setAttribute(RequestAttributes.CARS, cars);
        assertEquals(page, JSPPages.ALL_CARS_PAGE);
    }


}