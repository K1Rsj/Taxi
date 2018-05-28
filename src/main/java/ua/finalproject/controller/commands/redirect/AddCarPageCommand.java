package ua.finalproject.controller.commands.redirect;

import ua.finalproject.constants.jsp.JSPPages;
import ua.finalproject.controller.commands.Command;

import javax.servlet.http.HttpServletRequest;

/**
 * Command for redirect to add car page
 */
public class AddCarPageCommand implements Command {

    /**
     * @param request request from user
     * @return path to add car page
     */
    @Override
    public String execute(HttpServletRequest request) {
        return JSPPages.ADD_CAR_PAGE;
    }
}
