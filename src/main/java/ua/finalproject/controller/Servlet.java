package ua.finalproject.controller;

import ua.finalproject.controller.commands.*;

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
        commands.put("user_registration_page", new UserRegPageCommand());
        commands.put("user_registration", new UserRegistrationCommand());
        commands.put("exception" , new ExceptionCommand());
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
            response.sendRedirect(page.replace("redirect", "/taxi"));
        }else {
            request.getRequestDispatcher(page).forward(request, response);
        }
    }
}
