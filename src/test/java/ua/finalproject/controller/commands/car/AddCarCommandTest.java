package ua.finalproject.controller.commands.car;

import org.junit.Before;
import org.junit.Test;
import ua.finalproject.constants.jsp.JSPPages;
import ua.finalproject.constants.jsp.RequestAttributes;
import ua.finalproject.constants.jsp.RequestParameters;
import ua.finalproject.constants.messages.Messages;
import ua.finalproject.model.services.impl.CarServiceImpl;
import ua.finalproject.util.BundleManager;

import javax.servlet.http.HttpServletRequest;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class AddCarCommandTest {

    private AddCarCommand addCarCommand;
    private CarServiceImpl carServiceImpl;
    private HttpServletRequest request;
    private String model;
    private String number;
    private String driver;
    private String carType;

    @Before
    public void setUp() {
        carServiceImpl = mock(CarServiceImpl.class);
        addCarCommand = new AddCarCommand(carServiceImpl);
        request = mock(HttpServletRequest.class);
        model = "Peugeot 208";
        number = "АА7686АА";
        driver = "Desmond Cenias";
        carType = "standard";
    }

    @Test
    public void execute() {

        when(request.getParameter(RequestParameters.MODEL)).thenReturn(model);
        when(request.getParameter(RequestParameters.NUMBER)).thenReturn(number);
        when(request.getParameter(RequestParameters.DRIVER)).thenReturn(driver);
        when(request.getParameter(RequestParameters.TYPE)).thenReturn(carType);
        String page = addCarCommand.execute(request);
        verify(request).setAttribute(RequestAttributes.INFORMATION_MESSAGE,
                BundleManager.INSTANCE.getString(Messages.CAR_SUCCESSFULLY_ADDED));
        assertEquals(page, JSPPages.ADMIN_FOUNDATION_PAGE);
    }

}