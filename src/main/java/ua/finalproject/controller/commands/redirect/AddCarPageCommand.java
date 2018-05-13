package ua.finalproject.controller.commands.redirect;

import ua.finalproject.controller.commands.Command;

import javax.servlet.http.HttpServletRequest;

public class AddCarPageCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        return "/WEB-INF/admin/add_car_page.jsp";
    }
}
