package ua.finalproject.controller.commands.discount;

import ua.finalproject.constants.jsp.JSPPages;
import ua.finalproject.constants.jsp.RequestAttributes;
import ua.finalproject.controller.commands.Command;
import ua.finalproject.model.services.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;

/**
 * Command for getting user's discount depends on spent money by user
 */
public class MyDiscountCommand implements Command {

    private UserServiceImpl userServiceImpl;

    public MyDiscountCommand(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }

    /**
     * @param request request from user
     * @return path to my discount page
     */
    @Override
    public String execute(HttpServletRequest request) {
        Integer discount = userServiceImpl.getUserDiscount((String) request.getSession()
                .getAttribute(RequestAttributes.USER_LOGIN));
        request.setAttribute(RequestAttributes.DISCOUNT, discount);
        return JSPPages.MY_DISCOUNT_PAGE;
    }
}
