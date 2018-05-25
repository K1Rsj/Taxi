package ua.finalproject.controller.commands.redirect;

import org.junit.Before;
import org.junit.Test;
import ua.finalproject.constants.jsp.JSPPages;

import javax.servlet.http.HttpServletRequest;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

public class UserRegistrationPageCommandTest {

    private UserRegistrationPageCommand userRegistrationPageCommand;
    private HttpServletRequest request;

    @Before
    public void setUp() {
        userRegistrationPageCommand = new UserRegistrationPageCommand();
        request = mock(HttpServletRequest.class);
    }

    @Test
    public void execute() {
        String page = userRegistrationPageCommand.execute(request);
        assertEquals(page, JSPPages.USER_REGISTRATION_PAGE);
    }

}