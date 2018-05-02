package ua.finalproject.controller.commands;

import ua.finalproject.controller.util.CreateEntityFromRequest;
import ua.finalproject.controller.util.DataValidation;
import ua.finalproject.model.entities.impl.User;
import ua.finalproject.model.services.UserService;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLIntegrityConstraintViolationException;

public class UserRegistrationCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        if (!DataValidation.userDataValidation(request)) {
            return "/user_registration_page.jsp";
        } else {
            User user = CreateEntityFromRequest.getUserFromRequest(request);
            UserService userService = new UserService();
            try {
                userService.registerUser(user);
            } catch (SQLIntegrityConstraintViolationException e) {
                e.printStackTrace();
                request.setAttribute("wrongInputMessage", DataValidation.loginOrEmailNotUniqueDetermination(e));
                return "/user_registration_page.jsp";
            }
            request.setAttribute("informationMessage", "Registration is successful");
            return "/index.jsp";
        }
    }

}
