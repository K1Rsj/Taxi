package ua.finalproject.controller.commands;

import ua.finalproject.controller.util.ControllerUtil;
import ua.finalproject.controller.util.DataValidation;
import ua.finalproject.model.entities.impl.User;
import ua.finalproject.model.services.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public class LoginCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        UserService userService = new UserService();
        String login = request.getParameter("login");
        String pass = request.getParameter("password");
        System.out.println("login=" + login + "  " + "password=" + pass);

        if (DataValidation.validationOfLoginAndPassword(request, login, pass)) {
            return "redirect"+"index";
        }

        Optional<User> userOptional = userService.findUserByLogin(login);
        if (!userOptional.isPresent() || !userOptional.get().getPassword().equals(pass)) {
            request.getSession().setAttribute("informationMessage", "Wrong login or password");
            System.out.println(userOptional);
            return "redirect"+"index";
        }
        User user = userOptional.get();

        if (ControllerUtil.checkUserAlreadyIsLogged(request.getSession(), login)) {
            request.getSession().setAttribute("informationMessage", "User is already logged");
            return "redirect"+"index";
        }
        request.getSession().removeAttribute("informationMessage");
        request.getSession().removeAttribute("wrongInputMessage");
        if (user.getRole().equals(User.Role.ADMIN)) {
            ControllerUtil.setUserRole(request, User.Role.ADMIN, login);
            return "redirect"+"admin_foundation.jsp";
        }
        if (user.getRole().equals(User.Role.USER)) {
            ControllerUtil.setUserRole(request, User.Role.USER, login);
            return "redirect"+"user_foundation";
        }
        return "/WEB-INF/index.jsp";
    }
}
