package ua.finalproject.controller.commands.order;

import ua.finalproject.controller.commands.Command;
import ua.finalproject.controller.util.DataValidation;
import ua.finalproject.model.entities.impl.Order;
import ua.finalproject.model.exceptions.NoFreeCarWithSuchTypeException;
import ua.finalproject.model.services.OrderService;
import ua.finalproject.util.LogMessageBuilder;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public class MakeOrderCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        if (!Optional.ofNullable(request.getSession().getAttribute("order")).isPresent()) {
            OrderService orderService = new OrderService();
            String login = (String) request.getSession().getAttribute("userLogin");

            String departureStreet = request.getParameter("departure");
            String destinationStreet = request.getParameter("destination");
            String type = request.getParameter("type");
            if (!DataValidation.orderDataValidation(departureStreet, destinationStreet)) {
                request.setAttribute("orderInformationMessage", "Wrong street format");
                return "/WEB-INF/user/make_order_page.jsp";
            }
            try {
                Order order = orderService.makeOrder(login, departureStreet, destinationStreet, type);
                request.getSession().setAttribute("order", order);
                logger.info(LogMessageBuilder.INSTANCE.makeOrderInfo(order.getUser().getFirstName(),
                        order.getUser().getSecondName(), order.getCar().getNumber()));
            } catch (NoFreeCarWithSuchTypeException e) {
                request.setAttribute("orderInformationMessage", e.getMessage());
                logger.info(e.getMessage());
            } catch (Exception e) {
                request.setAttribute("orderInformationMessage", "Order error");
                logger.error("Make order error", e.getMessage());
            }
        }
        else {
            request.setAttribute("orderInformationMessage", "You only can have one order");
        }
        return "/WEB-INF/user/user_foundation.jsp";
    }
}
