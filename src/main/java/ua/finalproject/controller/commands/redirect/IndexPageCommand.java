package ua.finalproject.controller.commands.redirect;

import ua.finalproject.constants.jsp.JSPPages;
import ua.finalproject.controller.commands.Command;

import javax.servlet.http.HttpServletRequest;

/**
 * Command for redirect to index page
 */
public class IndexPageCommand implements Command {

    /**
     *
     * @param request request from user
     * @return path to index page
     */
    @Override
    public String execute(HttpServletRequest request) {
        return JSPPages.INDEX_PAGE;
    }
}
