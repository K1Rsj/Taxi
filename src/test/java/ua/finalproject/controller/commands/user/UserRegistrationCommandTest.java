package ua.finalproject.controller.commands.user;

import org.junit.Before;
import org.junit.Test;
import ua.finalproject.constants.jsp.JSPPages;
import ua.finalproject.constants.jsp.RequestAttributes;
import ua.finalproject.constants.jsp.RequestParameters;
import ua.finalproject.constants.messages.Messages;
import ua.finalproject.model.services.impl.UserServiceImpl;
import ua.finalproject.util.BundleManager;

import javax.servlet.http.HttpServletRequest;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class UserRegistrationCommandTest {

    private UserRegistrationCommand userRegistrationCommand;
    private HttpServletRequest request;
    private String login;
    private String email;
    private String firstName;
    private String secondName;
    private String phoneNumber;
    private String password;
    private String password2;

    @Before
    public void setUp() {
        UserServiceImpl userServiceImpl = mock(UserServiceImpl.class);
        userRegistrationCommand = new UserRegistrationCommand(userServiceImpl);
        request = mock(HttpServletRequest.class);
        login = "zeus";
        email = "zeus@gmail.com";
        firstName = "Ivan";
        secondName = "Shevchenko";
        phoneNumber = "0664567893";
        password = "123456";
        password2 = "123456";
    }

    @Test
    public void execute() {
        when(request.getParameter(RequestParameters.LOGIN)).thenReturn(login);
        when(request.getParameter(RequestParameters.EMAIL)).thenReturn(email);
        when(request.getParameter(RequestParameters.FIRST_NAME)).thenReturn(firstName);
        when(request.getParameter(RequestParameters.SECOND_NAME)).thenReturn(secondName);
        when(request.getParameter(RequestParameters.PHONE_NUMBER)).thenReturn(phoneNumber);
        when(request.getParameter(RequestParameters.PASSWORD)).thenReturn(password);
        when(request.getParameter(RequestParameters.SECOND_PASSWORD)).thenReturn(password2);

        String page = userRegistrationCommand.execute(request);
        verify(request).setAttribute(RequestAttributes.INFORMATION_MESSAGE, BundleManager
                .INSTANCE.getString(Messages.SUCCESSFUL_REGISTRATION));
        assertEquals(page, JSPPages.INDEX_PAGE);
    }

}