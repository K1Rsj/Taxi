package ua.finalproject.controller.commands.user;

import ua.finalproject.constants.jsp.JSPPages;
import ua.finalproject.constants.jsp.RequestAttributes;
import ua.finalproject.constants.messages.Messages;
import ua.finalproject.controller.commands.Command;
import ua.finalproject.controller.util.ControllerUtil;
import ua.finalproject.model.entities.impl.User;
import ua.finalproject.model.services.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Command for getting all users
 */
public class AllUsersCommand implements Command {

    private UserService userService;

    public AllUsersCommand(UserService userService) {
        this.userService = userService;
    }

    /**
     *
     * @param request request from user
     * @return path to all users page
     */
    @Override
    public String execute(HttpServletRequest request) {
        List<User> allUsers = userService.showAllUsers().get();
        if (allUsers.isEmpty()) {
            request.setAttribute(RequestAttributes.MESSAGE, bundleManager.getString(Messages.THERE_ARE_NO_USERS_IN_DB));
            return JSPPages.ALL_USERS_PAGE;
        }
        request.setAttribute(RequestAttributes.USERS, ControllerUtil.getSubListForPagination(request, allUsers));
        return JSPPages.ALL_USERS_PAGE;
    }
}
