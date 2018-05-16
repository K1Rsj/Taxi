package ua.finalproject.controller.commands.order;

import ua.finalproject.controller.commands.Command;
import ua.finalproject.controller.util.ControllerUtil;
import ua.finalproject.model.entities.impl.Order;
import ua.finalproject.model.services.OrderService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

public class MyOrdersCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        OrderService orderService = new OrderService();
        Optional<List<Order>> orders = orderService.getAllUserOrders((String)request.getSession().getAttribute("userLogin"));
        if (orders.isPresent()) {
            request.setAttribute("orders", ControllerUtil.getSubListForPagination(request,orders.get()));
            return "/WEB-INF/user/my_orders.jsp";
        }
        request.setAttribute("message", "There are no cars");
        return "/WEB-INF/user/my_orders.jsp";
    }
}
