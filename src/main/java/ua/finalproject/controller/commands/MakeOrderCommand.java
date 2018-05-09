package ua.finalproject.controller.commands;

import ua.finalproject.controller.util.CreateEntityFromRequest;
import ua.finalproject.model.entities.impl.Order;
import ua.finalproject.model.entities.impl.User;
import ua.finalproject.model.exceptions.NoFreeCarWithSuchTypeException;
import ua.finalproject.model.services.OrderService;
import ua.finalproject.model.services.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public class MakeOrderCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
//        UserService userService = new UserService();
//        OrderService orderService = new OrderService();
//        Optional<String> login = Optional.ofNullable((String) request.getSession().getAttribute("userLogin"));
//        Optional<User> userOptional;
//        if (!login.isPresent()) {
//            request.getSession().setAttribute("informationMessage", "you can't make orders, please log in");
//            return "/WEB-INF/index.jsp";
//        }
//        userOptional = userService.findUserByLogin(login.get());
//        User user;
//        if (!userOptional.isPresent()) {
//            request.getSession().setAttribute("informationMessage", "it's impossible but you don't exist in database");
//            return "/WEB-INF/index.jsp";
//        }
//        user = userOptional.get();
        OrderService orderService = new OrderService();
        String login = (String) request.getSession().getAttribute("userLogin");
        if (login.isEmpty()) {
            request.getSession().setAttribute("informationMessage", "you can't make orders, please log in");
            return "/WEB-INF/index.jsp";
        }
        Order order = CreateEntityFromRequest.getOrderFromRequest(request);
        try {
            order = orderService.makeOrder(order, login);
            request.setAttribute("order", order);
            return "";
        } catch (NoFreeCarWithSuchTypeException e) {
            request.setAttribute("informationMessage", e.getMessage());
            return "";
        } catch (Exception e2) {
            request.setAttribute("informationMessage", "Order error");
            return "";
        }
    }
}
