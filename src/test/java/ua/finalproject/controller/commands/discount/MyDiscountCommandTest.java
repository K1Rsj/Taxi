package ua.finalproject.controller.commands.discount;

import org.junit.Before;
import org.junit.Test;
import ua.finalproject.constants.jsp.JSPPages;
import ua.finalproject.constants.jsp.RequestAttributes;
import ua.finalproject.model.services.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class MyDiscountCommandTest {

    private MyDiscountCommand myDiscountCommand;
    private UserServiceImpl userServiceImpl;
    private HttpServletRequest request;
    private HttpSession session;
    private Integer discount;
    private String login;

    @Before
    public void setUp() {
        userServiceImpl = mock(UserServiceImpl.class);
        myDiscountCommand = new MyDiscountCommand(userServiceImpl);
        request = mock(HttpServletRequest.class);
        session = mock(HttpSession.class);
        discount = 9;
        login = "misha";
    }

    @Test
    public void execute() {
        when(request.getSession()).thenReturn(session);
        when(request.getSession().getAttribute(RequestAttributes
                .USER_LOGIN)).thenReturn
                (login);
        when(userServiceImpl.getUserDiscount(login)).thenReturn(discount);
        String page = myDiscountCommand.execute(request);
        verify(request).setAttribute(RequestAttributes.DISCOUNT, discount);
        assertNotNull(page);
        assertEquals(page, JSPPages.MY_DISCOUNT_PAGE);
        assertEquals(userServiceImpl.getUserDiscount(login), discount);
    }
}