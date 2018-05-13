package ua.finalproject.controller.commands;

import ua.finalproject.model.services.UserService;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

public class DeleteUserCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        Integer userId = Integer.parseInt(request.getParameter("id"));
        UserService userService = new UserService();
        try {
            userService.deleteUserById(userId);
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "User wasn't deleted");
            return "/WEB-INF/error.jsp";
        }
        return "redirect"+"all_users";
    }
}
