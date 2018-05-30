package ua.finalproject.controller;

import ua.finalproject.constants.GlobalConstants;
import ua.finalproject.constants.command.CommandNames;
import ua.finalproject.controller.commands.Command;
import ua.finalproject.controller.commands.car.AddCarCommand;
import ua.finalproject.controller.commands.car.AllCarsCommand;
import ua.finalproject.controller.commands.car_type.AllCarTypesCommand;
import ua.finalproject.controller.commands.discount.AddDiscountCommand;
import ua.finalproject.controller.commands.discount.MyDiscountCommand;
import ua.finalproject.controller.commands.order.CancelOrderCommand;
import ua.finalproject.controller.commands.order.ConfirmOrderCommand;
import ua.finalproject.controller.commands.order.MakeOrderCommand;
import ua.finalproject.controller.commands.order.MyOrdersCommand;
import ua.finalproject.controller.commands.redirect.*;
import ua.finalproject.controller.commands.user.*;
import ua.finalproject.controller.util.ControllerUtil;
import ua.finalproject.model.dao.connectionPool.ConnectionPoolHolder;
import ua.finalproject.model.dao.factory.DaoFactory;
import ua.finalproject.model.entities.full.User;
import ua.finalproject.model.services.ServiceFactory;
import ua.finalproject.model.services.impl.CarServiceImpl;
import ua.finalproject.model.services.impl.CarTypeServiceImpl;
import ua.finalproject.model.services.impl.OrderServiceImpl;
import ua.finalproject.model.services.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @see HttpServlet
 */
public class Servlet extends HttpServlet {

    private Map<String, Command> commands = new HashMap<>();

    public void init() {
        commands.put(CommandNames.LOGIN, new LoginCommand(ServiceFactory
                .getInstance()
                .createUserService()));
        commands.put(CommandNames.LOGOUT, new LogOutCommand
                (ServiceFactory.getInstance()
                        .createCarService()));
        commands.put(CommandNames.USER_REGISTRATION_PAGE, new
                UserRegistrationPageCommand());
        commands.put(CommandNames.USER_REGISTRATION, new
                UserRegistrationCommand(ServiceFactory
                .getInstance().createUserService()));
        commands.put(CommandNames.INDEX, new IndexPageCommand());
        commands.put(CommandNames.ALL_CARS, new AllCarsCommand
                (ServiceFactory.getInstance()
                        .createCarService()));
        commands.put(CommandNames.MAKE_ORDER_PAGE, new
                MakeOrderPageCommand());
        commands.put(CommandNames.MAKE_ORDER, new MakeOrderCommand
                (ServiceFactory
                        .getInstance().createOrderService()));
        commands.put(CommandNames.CANCEL_ORDER, new CancelOrderCommand
                (ServiceFactory
                        .getInstance().createOrderService()));
        commands.put(CommandNames.CONFIRM_ORDER, new ConfirmOrderCommand
                (ServiceFactory
                        .getInstance().createOrderService()));
        commands.put(CommandNames.ADD_DISCOUNT_PAGE, new
                AddDiscountPageCommand());
        commands.put(CommandNames.ADD_DISCOUNT, new AddDiscountCommand
                (ServiceFactory
                        .getInstance().createCarTypeService()));
        commands.put(CommandNames.MY_ORDERS, new MyOrdersCommand
                (ServiceFactory.getInstance()
                        .createOrderService()));
        commands.put(CommandNames.MY_DISCOUNT, new MyDiscountCommand
                (ServiceFactory
                        .getInstance().createUserService()));
        commands.put(CommandNames.ADD_CAR_PAGE, new AddCarPageCommand());
        commands.put(CommandNames.ADD_CAR, new AddCarCommand
                (ServiceFactory.getInstance()
                        .createCarService()));
        commands.put(CommandNames.ALL_CAR_TYPES, new AllCarTypesCommand
                (ServiceFactory
                        .getInstance().createCarTypeService()));
        commands.put(CommandNames.ALL_USERS, new AllUsersCommand
                (ServiceFactory.getInstance()
                        .createUserService()));
        commands.put(CommandNames.DELETE_USER, new DeleteUserCommand
                (ServiceFactory
                        .getInstance().createUserService()));
    }

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws
            ServletException, IOException {
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse
            response) throws
            ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Gets command name from request uri and do some action depends on
     * command name.
     * Then forward to concrete page.
     *
     * @param request  user's request
     * @param response servlet's response
     * @throws ServletException if the request could not be handled
     * @throws IOException      if an input or output error is detected
     *                          when the servlet
     *                          handles the request
     */
    private void processRequest(HttpServletRequest request,
                                HttpServletResponse response)
            throws ServletException, IOException {

        String path = request.getRequestURI();
        path = path.replaceAll(GlobalConstants
                        .EVERYTHING_BEFORE_TAXI_AND_TAXI,
                GlobalConstants.EMPTY_STRING);
        Command command = commands.getOrDefault(path,
                (HttpServletRequest r) -> ControllerUtil
                        .getUserIndexPage((User.Role)
                                request.getSession().getAttribute
                                        (GlobalConstants.ROLE)));
        String page = command.execute(request);

        if (page.contains(GlobalConstants.REDIRECT)) {
            response.sendRedirect(page.replace(GlobalConstants.REDIRECT,
                    GlobalConstants
                            .EMPTY_STRING));
        } else {
            request.getRequestDispatcher(page).forward(request, response);
        }
    }
}
