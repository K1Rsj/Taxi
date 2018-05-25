package ua.finalproject.controller.commands.order;

import org.junit.Before;
import org.junit.Test;
import ua.finalproject.constants.jsp.JSPPages;
import ua.finalproject.constants.jsp.RequestAttributes;
import ua.finalproject.constants.messages.Messages;
import ua.finalproject.model.entities.impl.Order;
import ua.finalproject.model.entities.impl.User;
import ua.finalproject.model.services.OrderService;
import ua.finalproject.util.BundleManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

public class ConfirmOrderCommandTest {

    private ConfirmOrderCommand confirmOrderCommand;
    private OrderService orderService;
    private HttpServletRequest request;
    private HttpSession session;
    private Order order;
    private User.Role role;


    @Before
    public void setUp() {
        orderService = mock(OrderService.class);
        confirmOrderCommand = new ConfirmOrderCommand(orderService);
        request = mock(HttpServletRequest.class);
        session = mock(HttpSession.class);
        order = mock(Order.class);
        role = User.Role.USER;
    }

    @Test
    public void execute() {
        when(request.getSession()).thenReturn(session);
        when(request.getSession().getAttribute(RequestAttributes.ORDER)).thenReturn(order);
        when(request.getSession().getAttribute(RequestAttributes.ROLE)).thenReturn(role);
        String page = confirmOrderCommand.execute(request);
        verify(request).setAttribute(RequestAttributes.ORDER_INFORMATION_MESSAGE, BundleManager.INSTANCE.getString(Messages.HAVE_A_GOOD_TRIP));
        verify(orderService).confirmOrder(order);
        verify(session).removeAttribute(RequestAttributes.ORDER);
        assertNotNull(page);
        assertEquals(page, JSPPages.USER_FOUNDATION_PAGE);

    }
}