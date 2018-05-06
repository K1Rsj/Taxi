package ua.finalproject.controller.util;

import ua.finalproject.model.entities.impl.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashSet;

public class ControllerUtil {
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
        return (HashSet<String>) session.getServletContext().getAttribute("loggedUsers");
    }

    private static void addLoggedUserToContext(HttpSession session, String userLogin, HashSet<String> loggedUsers) {
        loggedUsers.add(userLogin);
        updateLoggedUsersInContext(session, loggedUsers);
    }

    private static void updateLoggedUsersInContext(HttpSession session, HashSet<String> loggedUsers) {
        session.getServletContext().setAttribute("loggedUsers", loggedUsers);
    }

    public static void setUserRole(HttpServletRequest request, User.Role role, String login) {
        request.getSession().setAttribute("userLogin", login);
        request.getSession().setAttribute("role", role);
    }

    public static void deleteLoggedUserFromContext(HttpSession session) {
        HashSet<String> loggedUsers = getLoggedUsersFromContext(session);
        loggedUsers.remove(session.getAttribute("userLogin").toString());
        updateLoggedUsersInContext(session, loggedUsers);
    }
}
