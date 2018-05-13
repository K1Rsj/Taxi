package ua.finalproject.controller.commands;

import ua.finalproject.controller.util.ContextUtil;
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

        if (DataValidation.validationOfLoginAndPassword(login, pass)) {
            request.setAttribute("informationMessage", "Login or password is missed");
            return "/WEB-INF/index.jsp";
        }

        Optional<User> userOptional = userService.findUserByLogin(login);
        if (!userOptional.isPresent() || !userOptional.get().getPassword().equals(pass)) {
            request.setAttribute("informationMessage", "Wrong login or password");
            System.out.println(userOptional);
            return "/WEB-INF/index.jsp";
        }
        User user = userOptional.get();

        if (ContextUtil.checkUserAlreadyIsLogged(request.getSession(), login)) {
            request.setAttribute("informationMessage", "User is already logged");
            return "/WEB-INF/index.jsp";
        }

        request.getSession().setAttribute("userLogin", login);
        request.getSession().setAttribute("role", user.getRole());
        return ControllerUtil.getUserIndexPage(user.getRole());
    }
}
