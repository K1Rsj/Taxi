package ua.finalproject.controller.commands.redirect;

import org.junit.Before;
import org.junit.Test;
import ua.finalproject.constants.jsp.JSPPages;

import javax.servlet.http.HttpServletRequest;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

public class IndexPageCommandTest {

    private IndexPageCommand indexPageCommand;
    private HttpServletRequest request;

    @Before
    public void setUp() {
        indexPageCommand = new IndexPageCommand();
        request = mock(HttpServletRequest.class);
    }

    @Test
    public void execute() {
        String page = indexPageCommand.execute(request);
        assertEquals(page, JSPPages.INDEX_PAGE);
    }

}