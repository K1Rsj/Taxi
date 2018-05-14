package ua.finalproject.controller.commands.user;

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
                request.setAttribute("wrongInputMessage", DataValidation.loginOrEmailNotUniqueDetermination(e));
                logger.info(LogMessageBuilder.INSTANCE.duplicateUserInfo(user.getLogin()));
                return "/WEB-INF/form/user_registration_page.jsp";
            }
            request.setAttribute("informationMessage", "Registration is successful");
            logger.info(LogMessageBuilder.INSTANCE.userRegistrationInfo(user.getLogin()));
            return "/WEB-INF/index.jsp";
        } else {
            return "/WEB-INF/form/user_registration_page.jsp";
        }
    }

}
