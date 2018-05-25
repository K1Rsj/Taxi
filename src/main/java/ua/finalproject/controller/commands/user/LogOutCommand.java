package ua.finalproject.controller.commands.user;

import com.google.common.base.Strings;
import ua.finalproject.constants.GlobalConstants;
import ua.finalproject.constants.jsp.JSPPages;
import ua.finalproject.constants.jsp.RequestAttributes;
import ua.finalproject.controller.commands.Command;
import ua.finalproject.controller.util.ContextUtil;
import ua.finalproject.controller.util.ControllerUtil;
import ua.finalproject.model.services.CarService;

import javax.servlet.http.HttpServletRequest;

/**
 * Command for logging out of the system
 */
public class LogOutCommand implements Command {

    private CarService carService;

    public LogOutCommand(CarService carService) {
        this.carService = carService;
    }

    /**
     * Checks if user is logged in and removes session's attributes
     * @param request request from user
     * @return redirects to index page
     */
    @Override
    public String execute(HttpServletRequest request) {
        String login = (String) request.getSession().getAttribute(RequestAttributes.USER_LOGIN);
        if(Strings.isNullOrEmpty(login) ) {
            return JSPPages.INDEX_PAGE;
        }
        ContextUtil.deleteLoggedUserFromContext(request.getSession());
        ControllerUtil.ChangeCarStateToFree(request.getSession(), carService);
        request.getSession().removeAttribute(RequestAttributes.ORDER);
        request.getSession().removeAttribute(RequestAttributes.USER_LOGIN);
        request.getSession().removeAttribute(RequestAttributes.ROLE);
        return GlobalConstants.REDIRECT + GlobalConstants.INDEX;
    }


}
