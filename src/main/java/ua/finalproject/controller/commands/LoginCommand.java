package ua.finalproject.controller.commands;

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
        System.out.println("login="+login+"  "+"password="+pass);
        //Check if login or password are not empty
        if( login == null || login.equals("") || pass == null || pass.equals("")  ){
            request.setAttribute("informationMessage", "Login or password is missed");
            return "/index.jsp";
        }

        //Check if user is registered in system and password is correct
//        Optional<User> userOptional = userService.findUserByLogin(login);
//        if(!userOptional.isPresent() || !userOptional.get().getPassword().equals(pass)){
//            request.setAttribute(ParametersAndAttributes.INFORMATION_MESSAGE,
//                    Messages.WRONG_LOGIN_OR_PASS);
//            System.out.println(userOptional);
//            return Templates.INDEX;
//        }
//        User user = userOptional.get();
//
//        //Check if user is not already logged the system
//        if(ControllerUtil.checkUserAlreadyIsLogged(request, login)){
//            request.setAttribute(ParametersAndAttributes.INFORMATION_MESSAGE, Messages.USER_ALREADY_LOGGED);
//            return Templates.INDEX;
//        }
//
//        //Forward to the web-page depends on user role
//        if (user.getRole().equals(User.ROLE.ADMIN)){
//            ControllerUtil.setUserRole(request, User.ROLE.ADMIN, login);
//            return Templates.ADMIN_BASIS;
//        } else if(user.getRole().equals(User.ROLE.USER)) {
//            ControllerUtil.setUserRole(request, User.ROLE.USER, login);
//            return Templates.USER_BASIS;
//        } else {
//            ControllerUtil.setUserRole(request, User.ROLE.UNKNOWN, login);
//            return Templates.INDEX;
//        }
return null;
    }
}
