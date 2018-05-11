package ua.finalproject.controller.commands.order;

import ua.finalproject.controller.commands.Command;
import ua.finalproject.controller.util.ControllerUtil;
import ua.finalproject.model.entities.impl.Order;
import ua.finalproject.model.services.OrderService;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public class ConfirmOrderCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        OrderService orderService = new OrderService();
        Optional<Order> order = Optional.ofNullable((Order) request.getSession().getAttribute("order"));
        if (order.isPresent()) {
            try {
                orderService.confirmOrder(order.get());
            } catch (Exception e) {
                request.setAttribute("errorMessage", "Something wrong with confirmation of an order");
                e.printStackTrace();
                return "/WEB-INF/error.jsp";
            }
            request.getSession().removeAttribute("order");
        }
        return ControllerUtil.getUserIndexPage(request);
    }
}
