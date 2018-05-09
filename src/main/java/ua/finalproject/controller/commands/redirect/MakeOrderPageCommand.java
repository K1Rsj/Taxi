package ua.finalproject.controller.commands.redirect;

import ua.finalproject.controller.commands.Command;

import javax.servlet.http.HttpServletRequest;

public class MakeOrderPageCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        return "/WEB-INF/user/make_order_page.jsp";
    }
}
