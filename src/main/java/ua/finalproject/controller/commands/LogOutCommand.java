package ua.finalproject.controller.commands;

import com.google.common.base.Strings;
import ua.finalproject.controller.util.ContexUtil;
import ua.finalproject.controller.util.ControllerUtil;
import ua.finalproject.model.services.CarService;

import javax.servlet.http.HttpServletRequest;

public class LogOutCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        String login = (String) request.getSession().getAttribute("userLogin");
        if(Strings.isNullOrEmpty(login) ) {
            return "/WEB-INF/index.jsp";
        }
        ContexUtil.deleteLoggedUserFromContext(request.getSession());
        CarService carService = new CarService();
        ControllerUtil.ChangeCarStateToFree(request.getSession(), carService);
        request.getSession().removeAttribute("order");
        request.getSession().removeAttribute("userLogin");
        request.getSession().removeAttribute("role");
        return "redirect"+"index";
    }


}
