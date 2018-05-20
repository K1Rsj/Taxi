package ua.finalproject.controller.commands.user;

import ua.finalproject.constants.GlobalConstants;
import ua.finalproject.constants.jsp.RequestParameters;
import ua.finalproject.controller.commands.Command;
import ua.finalproject.model.services.UserService;

import javax.servlet.http.HttpServletRequest;

public class DeleteUserCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        Integer userId = Integer.parseInt(request.getParameter(RequestParameters.ID));
        UserService userService = new UserService();
        userService.deleteUserById(userId);
        return GlobalConstants.ALL_USERS;
    }
}
