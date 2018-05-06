package ua.finalproject.controller.commands;

import ua.finalproject.controller.util.ControllerUtil;

import javax.servlet.http.HttpServletRequest;

public class LogOutCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        ControllerUtil.deleteLoggedUserFromContext(request.getSession());
        request.getSession().removeAttribute("userLogin");
        request.getSession().removeAttribute("role");
        return "redirect"+"index";
    }
}
