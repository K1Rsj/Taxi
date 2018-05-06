package ua.finalproject.controller.commands.redirect;

import ua.finalproject.controller.commands.Command;

import javax.servlet.http.HttpServletRequest;

public class AdminFoundationPageCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        return "/WEB-INF/admin/admin_foundation.jsp";
    }
}
