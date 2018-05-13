package ua.finalproject.controller;

import ua.finalproject.controller.commands.*;

import ua.finalproject.controller.commands.order.CancelOrderCommand;
import ua.finalproject.controller.commands.order.ConfirmOrderCommand;
import ua.finalproject.controller.commands.order.MakeOrderCommand;
import ua.finalproject.controller.commands.order.MyOrdersCommand;
import ua.finalproject.controller.commands.redirect.*;

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
        commands.put("login", new LoginCommand());
        commands.put("logout", new LogOutCommand());
        commands.put("user_registration_page", new UserRegistrationPageCommand());
        commands.put("user_registration", new UserRegistrationCommand());
        commands.put("exception" , new ExceptionCommand());
        commands.put("index", new IndexPageCommand());
        commands.put("all_cars", new AllCarsCommand());
        commands.put("admin_foundation", new AdminFoundationPageCommand());
        commands.put("user_foundation", new UserFoundationPageCommand());
        commands.put("make_order_page", new MakeOrderPageCommand());
        commands.put("make_order", new MakeOrderCommand());
        commands.put("cancel_order", new CancelOrderCommand());
        commands.put("confirm_order", new ConfirmOrderCommand());
        commands.put("add_discount_page", new AddDiscountPageCommand());
        commands.put("add_discount", new AddDiscountCommand());
        commands.put("my_orders", new MyOrdersCommand());
        commands.put("my_discount", new MyDiscountCommand());
        commands.put("add_car_page", new AddCarPageCommand());
        commands.put("add_car", new AddCarCommand());
        commands.put("all_car_types", new AllCarTypesCommand());
        commands.put("all_users", new AllUsersCommand());
        commands.put("delete_user", new DeleteUserCommand());
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
        path = path.replaceAll(".*/taxi/" , "");
        System.out.println(path);
        Command command = commands.getOrDefault(path ,
                (HttpServletRequest r) ->"/WEB-INF/index.jsp");
        String page = command.execute(request);
        System.out.println(page);

        if(page.contains("redirect")){
            response.sendRedirect(page.replace("redirect", ""));
        }else {
            request.getRequestDispatcher(page).forward(request, response);
        }
    }
}
