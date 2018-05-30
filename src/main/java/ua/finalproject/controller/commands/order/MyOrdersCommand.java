package ua.finalproject.controller.commands.order;

import ua.finalproject.constants.jsp.JSPPages;
import ua.finalproject.constants.jsp.RequestAttributes;
import ua.finalproject.constants.messages.Messages;
import ua.finalproject.controller.commands.Command;
import ua.finalproject.controller.util.ControllerUtil;
import ua.finalproject.model.entities.full.Order;
import ua.finalproject.model.services.impl.OrderServiceImpl;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Command for getting all user's orders
 */
public class MyOrdersCommand implements Command {

    private OrderServiceImpl orderServiceImpl;

    public MyOrdersCommand(OrderServiceImpl orderServiceImpl) {
        this.orderServiceImpl = orderServiceImpl;
    }

    /**
     * @param request request from user
     * @return path to my orders page
     */
    @Override
    public String execute(HttpServletRequest request) {
        List<Order> orders = orderServiceImpl.getAllUserOrders((String)
                request.getSession()
                        .getAttribute(RequestAttributes.USER_LOGIN)).get();
        if (orders.isEmpty()) {
            request.setAttribute(RequestAttributes.MESSAGE,
                    bundleManager.getString(Messages
                            .THERE_ARE_NO_ORDERS));
            return JSPPages.MY_ORDERS_PAGE;
        }
        request.setAttribute(RequestAttributes.ORDERS, ControllerUtil
                .getSubListForPagination(request, orders));
        return JSPPages.MY_ORDERS_PAGE;
    }
}
