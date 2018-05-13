package ua.finalproject.controller.commands;

import ua.finalproject.model.entities.impl.User;
import ua.finalproject.model.services.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

public class AllUsersCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        UserService userService = new UserService();
        Optional<List<User>> allUsers = userService.showAllUsers();
        if (allUsers.isPresent()) {
            request.setAttribute("users", allUsers.get());
            return "/WEB-INF/admin/all_users.jsp";
        }
        request.setAttribute("message", "There are no car types");
        return "/WEB-INF/admin/all_users.jsp";
    }
}
