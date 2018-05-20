package ua.finalproject.controller.commands.discount;

import ua.finalproject.constants.jsp.JSPPages;
import ua.finalproject.constants.jsp.RequestAttributes;
import ua.finalproject.controller.commands.Command;
import ua.finalproject.model.services.UserService;

import javax.servlet.http.HttpServletRequest;

public class MyDiscountCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        UserService userService = new UserService();
        Integer discount = userService.getUserDiscount((String)request.getSession().getAttribute(RequestAttributes.USER_LOGIN));
        request.setAttribute(RequestAttributes.DISCOUNT, discount);
        return JSPPages.MY_DISCOUNT_PAGE;
    }
}
