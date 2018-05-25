package ua.finalproject.controller.commands.order;

import ua.finalproject.constants.jsp.JSPPages;
import ua.finalproject.constants.jsp.RequestAttributes;
import ua.finalproject.constants.jsp.RequestParameters;
import ua.finalproject.constants.messages.ExceptionMessages;
import ua.finalproject.constants.messages.LogMessages;
import ua.finalproject.constants.messages.ValidationMessages;
import ua.finalproject.controller.commands.Command;
import ua.finalproject.controller.util.DataValidation;
import ua.finalproject.model.entities.impl.Order;
import ua.finalproject.model.exceptions.NoFreeCarWithSuchTypeException;
import ua.finalproject.model.services.OrderService;
import ua.finalproject.util.LogMessageBuilder;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

/**
 * Command for making order
 */
public class MakeOrderCommand implements Command {

    private OrderService orderService;

    public MakeOrderCommand(OrderService orderService) {
        this.orderService = orderService;
    }

    /**
     *
     * @param request request from user
     * @return path to user foundation page if validation was successful
     *  or else return path to make order page
     */
    @Override
    public String execute(HttpServletRequest request) {
        if (!Optional.ofNullable(request.getSession().getAttribute(RequestAttributes.ORDER)).isPresent()) {
            String login = (String) request.getSession().getAttribute(RequestAttributes.USER_LOGIN);

            String departureStreet = request.getParameter(RequestParameters.DEPARTURE_STREET);
            String destinationStreet = request.getParameter(RequestParameters.DESTINATION_STREET);
            String type = request.getParameter(RequestParameters.TYPE);
            if (!DataValidation.orderDataValidation(departureStreet, destinationStreet)) {
                request.setAttribute(RequestAttributes.ORDER_INFORMATION_MESSAGE, bundleManager.getString(ValidationMessages.WRONG_STREET_FORMAT));
                return JSPPages.MAKE_ORDER_PAGE;
            }
            try {
                Order order = orderService.makeOrder(login, departureStreet, destinationStreet, type);
                request.getSession().setAttribute(RequestAttributes.ORDER, order);
                logger.info(LogMessageBuilder.INSTANCE.makeOrderInfo(order.getUser().getFirstName(),
                        order.getUser().getSecondName(), order.getCar().getNumber()));

            } catch (NoFreeCarWithSuchTypeException e) {
                request.setAttribute(RequestAttributes.ORDER_INFORMATION_MESSAGE, e.getMessage());
                logger.info(LogMessages.NO_FREE_CAR_SUCH_TYPE);
            }
        } else {
            request.setAttribute(RequestAttributes.ORDER_INFORMATION_MESSAGE, bundleManager.getString(ExceptionMessages.YOU_CAN_ONLY_HAVE_ONE_ORDER_AT_TIME));
        }
        return JSPPages.USER_FOUNDATION_PAGE;
    }
}
