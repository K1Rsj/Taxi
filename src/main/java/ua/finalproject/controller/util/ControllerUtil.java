package ua.finalproject.controller.util;

import ua.finalproject.model.entities.impl.Order;
import ua.finalproject.model.entities.impl.User;
import ua.finalproject.model.services.CarService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

public class ControllerUtil {
    public static String getUserIndexPage(User.Role role) {
        if (role == User.Role.ADMIN) {
            return "/WEB-INF/admin/admin_foundation.jsp";
        }
        if (role == User.Role.USER) {
            return "/WEB-INF/user/user_foundation.jsp";
        }
        return "/WEB-INF/index.jsp";
    }

    public static void ChangeCarStateToFree(HttpSession session, CarService carService) {
        Optional<Order> order = Optional.ofNullable((Order) session.getAttribute("order"));
        order.ifPresent(carService::changeCarStateFromBusyToFree);
    }
}
