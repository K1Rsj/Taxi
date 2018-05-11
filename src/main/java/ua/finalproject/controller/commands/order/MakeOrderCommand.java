package ua.finalproject.controller.commands.order;

import ua.finalproject.controller.commands.Command;
import ua.finalproject.model.entities.impl.Order;
import ua.finalproject.model.exceptions.NoFreeCarWithSuchTypeException;
import ua.finalproject.model.services.OrderService;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public class MakeOrderCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        if (!Optional.ofNullable(request.getSession().getAttribute("order")).isPresent()) {
            OrderService orderService = new OrderService();
            String login = (String) request.getSession().getAttribute("userLogin");
//            if (login.isEmpty()) {
//                request.getSession().setAttribute("informationMessage", "you can't make orders, please log in");
//                return "/WEB-INF/index.jsp";
//            }
            String departureStreet = request.getParameter("departure");
            String destinationStreet = request.getParameter("destination");
            String type = request.getParameter("type");
            try {
                Order order = orderService.makeOrder(login, departureStreet, destinationStreet, type);
                request.getSession().setAttribute("order", order);
            } catch (NoFreeCarWithSuchTypeException e) {
                request.setAttribute("orderInformationMessage", e.getMessage());
            } catch (Exception e2) {
                request.setAttribute("orderInformationMessage", "Order error");
                e2.printStackTrace();
            }
        }
        else {
            request.setAttribute("orderInformationMessage", "You only can have one order");
        }
        return "/WEB-INF/user/user_foundation.jsp";
    }
}
