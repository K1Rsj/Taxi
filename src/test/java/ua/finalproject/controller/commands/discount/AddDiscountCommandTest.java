package ua.finalproject.controller.commands.discount;

import org.junit.Before;
import org.junit.Test;
import ua.finalproject.constants.jsp.JSPPages;
import ua.finalproject.constants.jsp.RequestAttributes;
import ua.finalproject.constants.jsp.RequestParameters;
import ua.finalproject.constants.messages.Messages;
import ua.finalproject.model.services.impl.CarTypeServiceImpl;
import ua.finalproject.util.BundleManager;

import javax.servlet.http.HttpServletRequest;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class AddDiscountCommandTest {

    private AddDiscountCommand addDiscountCommand;
    private HttpServletRequest request;
    private Integer discount;
    private String type;


    @Before
    public void setUp() {
        CarTypeServiceImpl carTypeServiceImpl = mock(CarTypeServiceImpl
                .class);
        addDiscountCommand = new AddDiscountCommand(carTypeServiceImpl);
        request = mock(HttpServletRequest.class);
        discount = 10;
        type = "standard";
    }

    @Test
    public void execute() {

        when(request.getParameter(RequestParameters.DISCOUNT))
                .thenReturn(String.valueOf
                        (discount));
        when(request.getParameter(RequestParameters.TYPE)).thenReturn
                (type);
        String page = addDiscountCommand.execute(request);
        verify(request).setAttribute(RequestAttributes
                .INFORMATION_MESSAGE, BundleManager
                .INSTANCE.getString(Messages.DISCOUNT_SUCCESSFULLY_ADDED));
        assertEquals(page, JSPPages.ADMIN_FOUNDATION_PAGE);
    }
}