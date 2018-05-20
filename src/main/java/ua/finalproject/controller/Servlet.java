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
import ua.finalproject.model.entities.impl.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Servlet extends HttpServlet {

    private Map<String, Command> commands = new HashMap<>();

    public void init(){
        commands.put(CommandNames.LOGIN, new LoginCommand());
        commands.put(CommandNames.LOGOUT, new LogOutCommand());
        commands.put(CommandNames.USER_REGISTRATION_PAGE, new UserRegistrationPageCommand());
        commands.put(CommandNames.USER_REGISTRATION, new UserRegistrationCommand());
        commands.put(CommandNames.INDEX, new IndexPageCommand());
        commands.put(CommandNames.ALL_CARS, new AllCarsCommand());
        commands.put(CommandNames.MAKE_ORDER_PAGE, new MakeOrderPageCommand());
        commands.put(CommandNames.MAKE_ORDER, new MakeOrderCommand());
        commands.put(CommandNames.CANCEL_ORDER, new CancelOrderCommand());
        commands.put(CommandNames.CONFIRM_ORDER, new ConfirmOrderCommand());
        commands.put(CommandNames.ADD_DISCOUNT_PAGE, new AddDiscountPageCommand());
        commands.put(CommandNames.ADD_DISCOUNT, new AddDiscountCommand());
        commands.put(CommandNames.MY_ORDERS, new MyOrdersCommand());
        commands.put(CommandNames.MY_DISCOUNT, new MyDiscountCommand());
        commands.put(CommandNames.ADD_CAR_PAGE, new AddCarPageCommand());
        commands.put(CommandNames.ADD_CAR, new AddCarCommand());
        commands.put(CommandNames.ALL_CAR_TYPES, new AllCarTypesCommand());
        commands.put(CommandNames.ALL_USERS, new AllUsersCommand());
        commands.put(CommandNames.DELETE_USER, new DeleteUserCommand());
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String path = request.getRequestURI();
        System.out.println(path);
        path = path.replaceAll(GlobalConstants.EVERYTHING_BEFORE_TAXI_AND_TAXI, GlobalConstants.EMPTY_STRING);
        System.out.println(path);
        Command command = commands.getOrDefault(path ,
                (HttpServletRequest r) -> ControllerUtil.getUserIndexPage((User.Role)request.getSession().getAttribute(GlobalConstants.ROLE)));
        String page = command.execute(request);
        System.out.println(page);

        if(page.contains(GlobalConstants.REDIRECT)){
            response.sendRedirect(page.replace(GlobalConstants.REDIRECT, GlobalConstants.EMPTY_STRING));
        }else {
            request.getRequestDispatcher(page).forward(request, response);
        }
    }
}
