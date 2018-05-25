package ua.finalproject.controller.commands.redirect;

import ua.finalproject.constants.jsp.JSPPages;
import ua.finalproject.controller.commands.Command;

import javax.servlet.http.HttpServletRequest;

/**
 * Command for redirect to make order page
 */
public class MakeOrderPageCommand implements Command {

    /**
     *
     * @param request request from user
     * @return path to make order page
     */
    @Override
    public String execute(HttpServletRequest request) {
        return JSPPages.MAKE_ORDER_PAGE;
    }
}
