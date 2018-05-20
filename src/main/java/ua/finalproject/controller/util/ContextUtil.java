package ua.finalproject.controller.util;

import ua.finalproject.constants.jsp.RequestAttributes;

import javax.servlet.http.HttpSession;
import java.util.HashSet;

public class ContextUtil {
    public static boolean checkUserAlreadyIsLogged(HttpSession session, String userLogin) {
        HashSet<String> loggedUsers = getLoggedUsersFromContext(session);
        if (loggedUsers.stream().anyMatch(userLogin::equals)) {
            return true;
        }
        addLoggedUserToContext(session, userLogin, loggedUsers);
        return false;
    }

    @SuppressWarnings("unchecked")
    private static HashSet<String> getLoggedUsersFromContext(HttpSession session) {
        return (HashSet<String>) session.getServletContext().getAttribute(RequestAttributes.LOGGED_USERS);
    }

    private static void addLoggedUserToContext(HttpSession session, String userLogin, HashSet<String> loggedUsers) {
        loggedUsers.add(userLogin);
        updateLoggedUsersInContext(session, loggedUsers);
    }

    private static void updateLoggedUsersInContext(HttpSession session, HashSet<String> loggedUsers) {
        session.getServletContext().setAttribute(RequestAttributes.LOGGED_USERS, loggedUsers);
    }

    public static void deleteLoggedUserFromContext(HttpSession session) {
        HashSet<String> loggedUsers = getLoggedUsersFromContext(session);
        loggedUsers.remove(session.getAttribute(RequestAttributes.USER_LOGIN).toString());
        updateLoggedUsersInContext(session, loggedUsers);
    }
}
