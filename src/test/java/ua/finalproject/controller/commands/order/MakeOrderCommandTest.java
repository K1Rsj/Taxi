package ua.finalproject.controller.commands.order;

import org.junit.Before;
import org.junit.Test;
import ua.finalproject.constants.jsp.JSPPages;
import ua.finalproject.constants.jsp.RequestAttributes;
import ua.finalproject.constants.jsp.RequestParameters;
import ua.finalproject.model.entities.full.Car;
import ua.finalproject.model.entities.full.Order;
import ua.finalproject.model.entities.full.User;
import ua.finalproject.model.exceptions.NoFreeCarWithSuchTypeException;
import ua.finalproject.model.services.impl.OrderServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class MakeOrderCommandTest {

    private MakeOrderCommand makeOrderCommand;
    private OrderServiceImpl orderServiceImpl;
    private HttpServletRequest request;
    private HttpSession session;
    private String login;
    private String departureStreet;
    private String destinationStreet;
    private String type;
    private Order order;

    @Before
    public void setUp() {
        orderServiceImpl = mock(OrderServiceImpl.class);
        makeOrderCommand = new MakeOrderCommand(orderServiceImpl);
        request = mock(HttpServletRequest.class);
        session = mock(HttpSession.class);
        order = new Order();
        Car car = new Car.CarBuilder().setNumber("AA2121AA").build();
        User user = User.builder().firstName("Misha").secondName("Ivanov").build();
        order.setCar(car);
        order.setUser(user);
        login = "vetal";
        departureStreet = "Uborevicha 7";
        destinationStreet = "Paladina 5";
        type = "standard";
    }

    @Test
    public void execute() throws NoFreeCarWithSuchTypeException {
        when(request.getSession()).thenReturn(session);
        when(request.getSession().getAttribute(RequestAttributes.USER_LOGIN)).thenReturn
                (login);
        when(request.getParameter(RequestParameters.DEPARTURE_STREET)).thenReturn
                (departureStreet);
        when(request.getParameter(RequestParameters.DESTINATION_STREET)).thenReturn
                (destinationStreet);
        when(request.getParameter(RequestParameters.TYPE)).thenReturn(type);
        when(orderServiceImpl.makeOrder(login, departureStreet, destinationStreet, type))
                .thenReturn(order);
        String page = makeOrderCommand.execute(request);
        verify(orderServiceImpl).makeOrder(login, departureStreet, destinationStreet, type);
        verify(session).setAttribute(RequestAttributes.ORDER, order);
        assertEquals(page, JSPPages.USER_FOUNDATION_PAGE);

    }

    @Test
    public void executeWithException() throws NoFreeCarWithSuchTypeException {
        when(request.getSession()).thenReturn(session);
        when(request.getSession().getAttribute(RequestAttributes.USER_LOGIN)).thenReturn
                (login);
        when(request.getParameter(RequestParameters.DEPARTURE_STREET)).thenReturn
                (departureStreet);
        when(request.getParameter(RequestParameters.DESTINATION_STREET)).thenReturn
                (destinationStreet);
        when(request.getParameter(RequestParameters.TYPE)).thenReturn(type);
        when(orderServiceImpl.makeOrder(login, departureStreet, destinationStreet, type))
                .thenThrow(new NoFreeCarWithSuchTypeException());
        String page = makeOrderCommand.execute(request);
        verify(orderServiceImpl).makeOrder(login, departureStreet, destinationStreet, type);
        assertEquals(page, JSPPages.USER_FOUNDATION_PAGE);

    }
}