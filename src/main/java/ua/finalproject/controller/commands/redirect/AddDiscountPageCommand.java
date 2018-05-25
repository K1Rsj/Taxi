package ua.finalproject.controller.commands.redirect;

import ua.finalproject.constants.jsp.JSPPages;
import ua.finalproject.controller.commands.Command;

import javax.servlet.http.HttpServletRequest;

/**
 * Command for redirect to add discount page
 */
public class AddDiscountPageCommand implements Command {

    /**
     *
     * @param request request from user
     * @return path to add discount page
     */
    @Override
    public String execute(HttpServletRequest request) {
        return JSPPages.ADD_DISCOUNT_PAGE;
    }
}
