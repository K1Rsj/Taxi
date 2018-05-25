package ua.finalproject.controller.commands.redirect;

import org.junit.Before;
import org.junit.Test;
import ua.finalproject.constants.jsp.JSPPages;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

public class AddCarPageCommandTest {

    private AddCarPageCommand addCarPageCommand;
    private HttpServletRequest request;

    @Before
    public void setUp() {
        addCarPageCommand = new AddCarPageCommand();
        request = mock(HttpServletRequest.class);
    }

    @Test
    public void execute() {
        String page = addCarPageCommand.execute(request);
        assertEquals(page, JSPPages.ADD_CAR_PAGE);
    }

}