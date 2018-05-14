package ua.finalproject.controller.commands.discount;

import ua.finalproject.controller.commands.Command;
import ua.finalproject.model.services.UserService;

import javax.servlet.http.HttpServletRequest;

public class MyDiscountCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        UserService userService = new UserService();
        Integer discount = userService.getUserDiscount((String)request.getSession().getAttribute("userLogin"));
        request.setAttribute("discount", discount);
        return "/WEB-INF/user/my_discount.jsp";
    }
}
