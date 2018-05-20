package ua.finalproject.controller.commands.user;

import ua.finalproject.constants.jsp.JSPPages;
import ua.finalproject.constants.jsp.RequestAttributes;
import ua.finalproject.constants.messages.Messages;
import ua.finalproject.controller.commands.Command;
import ua.finalproject.controller.util.CreateEntityFromRequest;
import ua.finalproject.controller.util.DataValidation;
import ua.finalproject.model.entities.impl.User;
import ua.finalproject.model.services.UserService;
import ua.finalproject.util.LogMessageBuilder;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLIntegrityConstraintViolationException;

public class UserRegistrationCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        if (DataValidation.userDataValidation(request)) {
            User user = CreateEntityFromRequest.getUserFromRequest(request);
            UserService userService = new UserService();
            try {
                userService.registerUser(user);
            } catch (SQLIntegrityConstraintViolationException e) {
                request.setAttribute(RequestAttributes.WRONG_INPUT_MESSAGE, DataValidation.loginOrEmailNotUniqueDetermination(e));
                logger.info(LogMessageBuilder.INSTANCE.duplicateUserInfo(user.getLogin()));
                return JSPPages.USER_REGISTRATION_PAGE;
            }
            request.setAttribute(RequestAttributes.INFORMATION_MESSAGE, bundleManager.getString(Messages.SUCCESSFUL_REGISTRATION));
            logger.info(LogMessageBuilder.INSTANCE.userRegistrationInfo(user.getLogin()));
            return JSPPages.INDEX_PAGE;
        } else {
            return JSPPages.USER_REGISTRATION_PAGE;
        }
    }

}
