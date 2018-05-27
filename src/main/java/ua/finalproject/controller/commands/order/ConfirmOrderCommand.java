package ua.finalproject.controller.commands.order;

import ua.finalproject.constants.jsp.JSPPages;
import ua.finalproject.constants.jsp.RequestAttributes;
import ua.finalproject.constants.messages.Messages;
import ua.finalproject.controller.commands.Command;
import ua.finalproject.model.entities.full.Order;
import ua.finalproject.model.services.OrderService;
import ua.finalproject.util.LogMessageBuilder;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

/**
 * Command for confirming order
 */
public class ConfirmOrderCommand implements Command {

    private OrderService orderService;

    public ConfirmOrderCommand(OrderService orderService) {
        this.orderService = orderService;
    }

    /**
     *
     * @param request request from user
     * @return path to user foundation page
     */
    @Override
    public String execute(HttpServletRequest request) {
        Optional<Order> order = Optional.ofNullable((Order) request.getSession().getAttribute(RequestAttributes.ORDER));
        if (order.isPresent()) {
            orderService.confirmOrder(order.get());
            request.setAttribute(RequestAttributes.ORDER_INFORMATION_MESSAGE, bundleManager.getString(Messages.HAVE_A_GOOD_TRIP));
            request.getSession().removeAttribute(RequestAttributes.ORDER);
            logger.info(LogMessageBuilder.INSTANCE.confirmOrderInfo((String)request.getSession().getAttribute(RequestAttributes.USER_LOGIN)));
        }
        return JSPPages.USER_FOUNDATION_PAGE;
    }
}
