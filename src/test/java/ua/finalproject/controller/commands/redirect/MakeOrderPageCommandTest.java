package ua.finalproject.controller.commands.redirect;

import org.junit.Before;
import org.junit.Test;
import ua.finalproject.constants.jsp.JSPPages;

import javax.servlet.http.HttpServletRequest;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

public class MakeOrderPageCommandTest {

    private MakeOrderPageCommand makeOrderPageCommand;
    private HttpServletRequest request;

    @Before
    public void setUp() {
        makeOrderPageCommand = new MakeOrderPageCommand();
        request = mock(HttpServletRequest.class);
    }

    @Test
    public void execute() {
        String page = makeOrderPageCommand.execute(request);
        assertEquals(page, JSPPages.MAKE_ORDER_PAGE);
    }

}