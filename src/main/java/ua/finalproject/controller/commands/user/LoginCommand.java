package ua.finalproject.controller.commands.user;

import ua.finalproject.constants.GlobalConstants;
import ua.finalproject.constants.jsp.JSPPages;
import ua.finalproject.constants.jsp.RequestAttributes;
import ua.finalproject.constants.jsp.RequestParameters;
import ua.finalproject.constants.messages.ExceptionMessages;
import ua.finalproject.constants.messages.Messages;
import ua.finalproject.constants.messages.ValidationMessages;
import ua.finalproject.controller.commands.Command;
import ua.finalproject.controller.util.ContextUtil;
import ua.finalproject.controller.util.ControllerUtil;
import ua.finalproject.controller.util.DataValidation;
import ua.finalproject.model.entities.full.User;
import ua.finalproject.model.services.impl.UserServiceImpl;
import ua.finalproject.util.LogMessageBuilder;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

/**
 * Command for logging into the system
 */
public class LoginCommand implements Command {

    private UserServiceImpl userServiceImpl;

    public LoginCommand(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }

    /**
     * Checks if user exist in DB.
     * If user exist sets session user's role and login attributes.
     * Adds user to servlet context if he doesn't already logged into the system.
     *
     * @param request request from user
     * @return path to index page depends on user's role
     */
    @Override
    public String execute(HttpServletRequest request) {
        String login = request.getParameter(RequestParameters.LOGIN);
        String pass = request.getParameter(RequestParameters.PASSWORD);

        if (DataValidation.validationOfLoginAndPassword(login, pass)) {
            request.setAttribute(RequestAttributes.INFORMATION_MESSAGE, bundleManager
                    .getString(ValidationMessages.LOGIN_OR_PASSWORD_IS_MISSED));
            return JSPPages.INDEX_PAGE;
        }

        Optional<User> userOptional = userServiceImpl.findUserByLogin(login);
        if (!userOptional.isPresent() || !userOptional.get().getPassword().equals(pass)) {
            request.setAttribute(RequestAttributes.INFORMATION_MESSAGE, bundleManager
                    .getString(ValidationMessages.WRONG_LOGIN_OR_PASSWORD));
            logger.info(LogMessageBuilder.INSTANCE.invalidAttemptOfLogInInfo(login));
            return JSPPages.INDEX_PAGE;
        }
        User user = userOptional.get();

        if (ContextUtil.checkUserAlreadyIsLogged(request.getSession(), login)) {
            request.setAttribute(RequestAttributes.INFORMATION_MESSAGE, bundleManager
                    .getString(ExceptionMessages.USER_ALREADY_LOGGED));
            logger.info(LogMessageBuilder.INSTANCE.userAlreadyLoggedInfo(login));
            return JSPPages.INDEX_PAGE;
        }

        request.getSession().setAttribute(RequestAttributes.USER_LOGIN, login);
        request.getSession().setAttribute(RequestAttributes.ROLE, user.getRole());
        logger.info(LogMessageBuilder.INSTANCE.userLogInInfo(login));
        request.setAttribute(RequestAttributes.INFORMATION_MESSAGE,
                bundleManager.getString(Messages.HELLO) + GlobalConstants.WHITE_SPACE +
                        user.getFirstName() + GlobalConstants.WHITE_SPACE + user
                        .getSecondName());
        return ControllerUtil.getUserIndexPage(user.getRole());
    }
}
