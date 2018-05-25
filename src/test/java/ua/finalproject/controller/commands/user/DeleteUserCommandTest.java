package ua.finalproject.controller.commands.user;

import org.junit.Before;
import org.junit.Test;
import ua.finalproject.constants.GlobalConstants;
import ua.finalproject.constants.jsp.RequestParameters;
import ua.finalproject.model.services.UserService;

import javax.servlet.http.HttpServletRequest;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class DeleteUserCommandTest {

    private DeleteUserCommand deleteUserCommand;
    private HttpServletRequest request;

    @Before
    public void setUp() {
        UserService userService = mock(UserService.class);
        deleteUserCommand = new DeleteUserCommand(userService);
        request = mock(HttpServletRequest.class);
    }

    @Test
    public void execute() {
        String userID = "11";
        when(request.getParameter(RequestParameters.ID)).thenReturn(userID);
        String page = deleteUserCommand.execute(request);
        assertEquals(page, GlobalConstants.ALL_USERS);
    }

}