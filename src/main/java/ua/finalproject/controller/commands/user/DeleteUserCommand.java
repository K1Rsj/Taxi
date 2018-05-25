package ua.finalproject.controller.commands.user;

import ua.finalproject.constants.GlobalConstants;
import ua.finalproject.constants.jsp.RequestParameters;
import ua.finalproject.controller.commands.Command;
import ua.finalproject.model.services.UserService;

import javax.servlet.http.HttpServletRequest;

/**
 * Command for deleting user from DB
 */
public class DeleteUserCommand implements Command {

    private UserService userService;

    public DeleteUserCommand(UserService userService) {
        this.userService = userService;
    }

    /**
     *
     * @param request request from user
     * @return all users command
     */
    @Override
    public String execute(HttpServletRequest request) {
        Integer userId = Integer.parseInt(request.getParameter(RequestParameters.ID));
        userService.deleteUserById(userId);
        return GlobalConstants.ALL_USERS;
    }
}
