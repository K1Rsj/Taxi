package ua.finalproject.controller.util;

import ua.finalproject.constants.jsp.JSPPages;
import ua.finalproject.constants.jsp.RequestAttributes;
import ua.finalproject.constants.jsp.RequestParameters;
import ua.finalproject.model.entities.full.Order;
import ua.finalproject.model.entities.full.User;
import ua.finalproject.model.services.impl.CarServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class ControllerUtil {

    /**
     * Gets user's index page depending on user's role
     *
     * @param role user's role
     * @return path to jsp page
     */
    public static String getUserIndexPage(User.Role role) {
        if (role == User.Role.ADMIN) {
            return JSPPages.ADMIN_FOUNDATION_PAGE;
        }
        if (role == User.Role.USER) {
            return JSPPages.USER_FOUNDATION_PAGE;
        }
        return JSPPages.INDEX_PAGE;
    }

    /**
     * Chenges car's state if user user make order and didn't accept or
     * cancel it
     *
     * @param session        user's session
     * @param carServiceImpl car service
     */
    public static void ChangeCarStateToFree(HttpSession session,
                                            CarServiceImpl
                                                    carServiceImpl) {
        Optional<Order> order = Optional.ofNullable((Order) session
                .getAttribute
                        (RequestAttributes.ORDER));
        order.ifPresent(carServiceImpl::changeCarStateFromBusyToFree);
    }

    /**
     * Makes sub list from list for pagination
     *
     * @param request request from user
     * @param list    list
     * @return sub list for pagination depending on current page
     */
    public static List getSubListForPagination(HttpServletRequest request,
                                               List list) {

        Integer numberOfPages = (int) Math.ceil((double) list.size() / 5);
        String page = request.getParameter(RequestParameters.CURRENT_PAGE);
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
        request.setAttribute(RequestAttributes.NUMBER_OF_PAGES,
                numberOfPages);
        request.setAttribute(RequestAttributes.FIRST_INDEX, firstIndex);

        return list.subList(firstIndex, lastIndex);
    }
}
