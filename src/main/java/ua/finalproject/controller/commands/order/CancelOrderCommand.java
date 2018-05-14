package ua.finalproject.controller.commands.order;

import ua.finalproject.controller.commands.Command;
import ua.finalproject.controller.util.ControllerUtil;
import ua.finalproject.model.entities.impl.Order;
import ua.finalproject.model.entities.impl.User;
import ua.finalproject.model.services.OrderService;
import ua.finalproject.util.LogMessageBuilder;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public class CancelOrderCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        OrderService orderService = new OrderService();
        Optional<Order> order = Optional.ofNullable((Order) request.getSession().getAttribute("order"));
        if (order.isPresent()) {
            try {
                orderService.cancelOrder(order.get());
            } catch (Exception e) {
                request.setAttribute("errorMessage", "Something wrong with cancellation of an order");
                logger.error("Error with cancellation of an order", e.getMessage());
                return "/WEB-INF/error.jsp";
            }
            request.getSession().removeAttribute("order");
            logger.info(LogMessageBuilder.INSTANCE.cancelOrderInfo(order.get().getUser().getFirstName(),
                    order.get().getUser().getSecondName()));
        }
        return ControllerUtil.getUserIndexPage((User.Role)request.getSession().getAttribute("role"));
    }
}
