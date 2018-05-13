package ua.finalproject.controller.util;

import ua.finalproject.model.entities.impl.Order;
import ua.finalproject.model.entities.impl.User;
import ua.finalproject.model.services.CarService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Objects;
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

    public static List getSubListForPagination(HttpServletRequest request,
                                               List list) {

        Integer numberOfPages = (int) Math.ceil((double) list.size() / 5);
        String page = request.getParameter("currentPage");
        Integer currentPage;
        if (Objects.isNull(page)) {
            currentPage = 1;
        } else {
            currentPage = Integer.valueOf(page);
        }
        if (currentPage > numberOfPages) {
            currentPage = numberOfPages;
        }
        Integer lastIndex = 5 * currentPage;
        Integer firstIndex = lastIndex - 5;

        if (lastIndex > list.size()) {
            lastIndex = list.size();
        }
        request.setAttribute("numberOfPages", numberOfPages);
        request.setAttribute("firstIndex", firstIndex);

        return list.subList(firstIndex, lastIndex);
    }
}
