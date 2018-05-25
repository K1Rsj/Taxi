package ua.finalproject.controller.commands.user;

import org.junit.Before;
import org.junit.Test;
import ua.finalproject.constants.jsp.JSPPages;
import ua.finalproject.constants.jsp.RequestAttributes;
import ua.finalproject.constants.jsp.RequestParameters;
import ua.finalproject.model.entities.impl.User;
import ua.finalproject.model.services.UserService;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.util.HashSet;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class LoginCommandTest {

    private LoginCommand loginCommand;
    private UserService userService;
    private HttpServletRequest request;
    private HttpSession session;
    private User user;
    private String login;
    private String password;
    private User.Role role;
    private ServletContext servletContext;
    private HashSet<String> loggedUsers;

    @Before
    public void setUp() {
        userService = mock(UserService.class);
        loginCommand = new LoginCommand(userService);
        user = mock(User.class);
        request = mock(HttpServletRequest.class);
        session = mock(HttpSession.class);
        login = "misha";
        password = "qwerty";
        role = User.Role.USER;
        servletContext = mock(ServletContext.class);
        loggedUsers = new HashSet<>();

    }

    @Test
    public void execute() {
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute(RequestAttributes.USER_LOGIN)).thenReturn(login);
        when(session.getServletContext()).thenReturn(servletContext);
        when(servletContext.getAttribute(RequestAttributes.LOGGED_USERS)).thenReturn(loggedUsers);
        when(userService.findUserByLogin(login)).thenReturn(Optional.of(user));
        when(request.getParameter(RequestParameters.LOGIN)).thenReturn(login);
        when(request.getParameter(RequestParameters.PASSWORD)).thenReturn(password);
        when(user.getRole()).thenReturn(role);
        when(user.getPassword()).thenReturn(password);
        String page = loginCommand.execute(request);
        verify(session).setAttribute(RequestAttributes.USER_LOGIN, login);
        verify(session).setAttribute(RequestAttributes.ROLE, role);
        assertNotNull(page);
        assertEquals(page, JSPPages.USER_FOUNDATION_PAGE);
    }

}