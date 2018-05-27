package ua.finalproject.controller.util;

import org.junit.Test;
import ua.finalproject.constants.jsp.JSPPages;
import ua.finalproject.model.entities.full.User;

import static org.junit.Assert.*;

public class ControllerUtilTest {

    @Test
    public void getUserIndexPage() {
        User.Role role = User.Role.USER;
        String page = ControllerUtil.getUserIndexPage(role);
        assertEquals(JSPPages.USER_FOUNDATION_PAGE, page);
        assertNotEquals(JSPPages.ADMIN_FOUNDATION_PAGE, page);
    }


}