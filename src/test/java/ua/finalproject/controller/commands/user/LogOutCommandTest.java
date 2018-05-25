package ua.finalproject.controller.commands.user;

import org.junit.Before;
import org.junit.Test;
import ua.finalproject.constants.GlobalConstants;
import ua.finalproject.constants.jsp.JSPPages;
import ua.finalproject.constants.jsp.RequestAttributes;
import ua.finalproject.model.services.CarService;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.util.HashSet;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class LogOutCommandTest {

    private LogOutCommand logOutCommand;
    private HttpServletRequest request;
    private HttpSession session;
    private String login;

    @Before
    public void setUp() {
        CarService carService = mock(CarService.class);
        logOutCommand = new LogOutCommand(carService);
        request = mock(HttpServletRequest.class);
        session = mock(HttpSession.class);
        login = "mishasa";
    }

    @Test
    public void executeWithoutLogin() {
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute(RequestAttributes.USER_LOGIN)).thenReturn(GlobalConstants.EMPTY_STRING);
        String page = logOutCommand.execute(request);
        assertNotNull(page);
        assertEquals(page, JSPPages.INDEX_PAGE);
    }

    @Test
    public void executeWithLogin() {
        ServletContext servletContext = mock(ServletContext.class);
        HashSet<String> loggedUsers = new HashSet<>();
        loggedUsers.add(login);
        when(request.getSession()).thenReturn(session);
        when(request.getSession().getAttribute(RequestAttributes.USER_LOGIN)).thenReturn(login);
        when(request.getSession().getServletContext()).thenReturn(servletContext);
        when(servletContext.getAttribute(RequestAttributes.LOGGED_USERS)).thenReturn(loggedUsers);
        String page = logOutCommand.execute(request);
        verify(session).removeAttribute(RequestAttributes.ORDER);
        verify(session).removeAttribute(RequestAttributes.USER_LOGIN);
        verify(session).removeAttribute(RequestAttributes.ROLE);
        assertNotNull(page);
        assertEquals(page, GlobalConstants.REDIRECT + GlobalConstants.INDEX);
    }

}