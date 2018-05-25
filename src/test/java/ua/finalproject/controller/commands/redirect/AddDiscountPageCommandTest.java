package ua.finalproject.controller.commands.redirect;

import org.junit.Before;
import org.junit.Test;
import ua.finalproject.constants.jsp.JSPPages;

import javax.servlet.http.HttpServletRequest;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

public class AddDiscountPageCommandTest {

    private AddDiscountPageCommand addDiscountPageCommand;
    private HttpServletRequest request;

    @Before
    public void setUp() {
        addDiscountPageCommand = new AddDiscountPageCommand();
        request = mock(HttpServletRequest.class);
    }

    @Test
    public void execute() {
        String page = addDiscountPageCommand.execute(request);
        assertEquals(page, JSPPages.ADD_DISCOUNT_PAGE);
    }

}