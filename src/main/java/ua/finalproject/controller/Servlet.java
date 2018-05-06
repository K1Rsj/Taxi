package ua.finalproject.controller;

import ua.finalproject.controller.commands.*;
import ua.finalproject.controller.commands.redirect.AdminFoundationPageCommand;
import ua.finalproject.controller.commands.redirect.IndexPageCommand;
import ua.finalproject.controller.commands.redirect.UserFoundationPageCommand;
import ua.finalproject.controller.commands.redirect.UserRegistrationPageCommand;

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
        commands.put("all_cars", new AllCarsCommand());
        commands.put("index", new IndexPageCommand());
        commands.put("admin_foundation", new AdminFoundationPageCommand());
        commands.put("user_foundation", new UserFoundationPageCommand());
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
                (HttpServletRequest r) ->"/index.jsp)");
        String page = command.execute(request);
        System.out.println(page);

        if(page.contains("redirect")){
            response.sendRedirect(page.replace("redirect", ""));
        }else {
            request.getRequestDispatcher(page).forward(request, response);
        }
    }
}
