package ua.finalproject.controller.commands.order;

import ua.finalproject.constants.jsp.JSPPages;
import ua.finalproject.constants.jsp.RequestAttributes;
import ua.finalproject.constants.messages.Messages;
import ua.finalproject.controller.commands.Command;
import ua.finalproject.model.entities.full.Order;
import ua.finalproject.model.services.impl.OrderServiceImpl;
import ua.finalproject.util.LogMessageBuilder;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

/**
 * Command for cancelling order
 */
public class CancelOrderCommand implements Command {

    private OrderServiceImpl orderServiceImpl;

    public CancelOrderCommand(OrderServiceImpl orderServiceImpl) {
        this.orderServiceImpl = orderServiceImpl;
    }

    /**
     * @param request request from user
     * @return path to user foundation page
     */
    @Override
    public String execute(HttpServletRequest request) {
        Optional<Order> order = Optional.ofNullable((Order) request.getSession()
                .getAttribute(RequestAttributes.ORDER));
        if (order.isPresent()) {
            orderServiceImpl.cancelOrder(order.get());
            request.setAttribute(RequestAttributes.ORDER_INFORMATION_MESSAGE, bundleManager
                    .getString(Messages.WE_ARE_DISAPPOINTED));
            request.getSession().removeAttribute(RequestAttributes.ORDER);
            logger.info(LogMessageBuilder.INSTANCE.cancelOrderInfo((String) request
                    .getSession().getAttribute(RequestAttributes.USER_LOGIN)));
        }
        return JSPPages.USER_FOUNDATION_PAGE;
    }
}
