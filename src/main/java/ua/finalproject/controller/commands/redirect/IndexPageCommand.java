package ua.finalproject.controller.commands.redirect;

import ua.finalproject.constants.jsp.JSPPages;
import ua.finalproject.controller.commands.Command;

import javax.servlet.http.HttpServletRequest;

public class IndexPageCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        return JSPPages.INDEX_PAGE;
    }
}
