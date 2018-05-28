package ua.finalproject.controller.commands.redirect;

import ua.finalproject.constants.jsp.JSPPages;
import ua.finalproject.controller.commands.Command;

import javax.servlet.http.HttpServletRequest;

/**
 * Command for redirect to user registration page
 */
public class UserRegistrationPageCommand implements Command {

    /**
     * @param request request from user
     * @return path to user registration page
     */
    @Override
    public String execute(HttpServletRequest request) {
        return JSPPages.USER_REGISTRATION_PAGE;
    }
}
