package ua.finalproject.controller.commands.redirect;

import ua.finalproject.constants.jsp.JSPPages;
import ua.finalproject.controller.commands.Command;

import javax.servlet.http.HttpServletRequest;

public class MakeOrderPageCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        return JSPPages.MAKE_ORDER_PAGE;
    }
}
