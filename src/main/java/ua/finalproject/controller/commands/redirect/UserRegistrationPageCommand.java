package ua.finalproject.controller.commands.redirect;

import ua.finalproject.controller.commands.Command;

import javax.servlet.http.HttpServletRequest;

public class UserRegistrationPageCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        return "/user_registration_page.jsp";
    }
}