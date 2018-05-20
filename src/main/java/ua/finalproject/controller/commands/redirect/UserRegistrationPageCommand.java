package ua.finalproject.controller.commands.redirect;

import ua.finalproject.constants.jsp.JSPPages;
import ua.finalproject.controller.commands.Command;

import javax.servlet.http.HttpServletRequest;

public class UserRegistrationPageCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        return JSPPages.USER_REGISTRATION_PAGE;
    }
}
