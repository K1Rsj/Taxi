package ua.finalproject.controller.commands.user;

import ua.finalproject.controller.commands.Command;
import ua.finalproject.model.services.UserService;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

public class DeleteUserCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        Integer userId = Integer.parseInt(request.getParameter("id"));
        UserService userService = new UserService();
        userService.deleteUserById(userId);
        return "redirect" + "all_users";
    }
}
