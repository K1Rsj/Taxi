package ua.finalproject.controller.commands.user;

import org.junit.Before;
import org.junit.Test;
import ua.finalproject.constants.GlobalConstants;
import ua.finalproject.constants.jsp.RequestParameters;
import ua.finalproject.model.services.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class DeleteUserCommandTest {

    private DeleteUserCommand deleteUserCommand;
    private HttpServletRequest request;

    @Before
    public void setUp() {
        UserServiceImpl userServiceImpl = mock(UserServiceImpl.class);
        deleteUserCommand = new DeleteUserCommand(userServiceImpl);
        request = mock(HttpServletRequest.class);
    }

    @Test
    public void execute() {
        String userID = "11";
        when(request.getParameter(RequestParameters.ID)).thenReturn
                (userID);
        String page = deleteUserCommand.execute(request);
        assertEquals(page, GlobalConstants.ALL_USERS);
    }

}