package ua.finalproject.controller.commands;

import javax.servlet.http.HttpServletRequest;

public class UserRegPageCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        return "/user_registration_page.jsp";
    }
}
