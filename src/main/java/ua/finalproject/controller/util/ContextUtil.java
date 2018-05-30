package ua.finalproject.controller.util;

import ua.finalproject.constants.jsp.RequestAttributes;

import javax.servlet.http.HttpSession;
import java.util.HashSet;

public class ContextUtil {

    /**
     * Checks if user is already logged into the system.
     * If not adds him into logged users.
     *
     * @param session   user's session
     * @param userLogin user's login
     * @return <code>true</code> if the user is already logged
     */
    public static boolean checkUserAlreadyIsLogged(HttpSession session,
                                                   String userLogin) {
        HashSet<String> loggedUsers = getLoggedUsersFromContext(session);
        if (loggedUsers.stream().anyMatch(userLogin::equals)) {
            return true;
        }
        addLoggedUserToContext(session, userLogin, loggedUsers);
        return false;
    }

    /**
     * @param session user's session
     * @return already logged users from context
     */
    @SuppressWarnings("unchecked")
    private static HashSet<String> getLoggedUsersFromContext(HttpSession session) {
        return (HashSet<String>) session.getServletContext()
                .getAttribute(RequestAttributes
                        .LOGGED_USERS);
    }

    /**
     * Adds user to logged users hash set
     *
     * @param session     user's session
     * @param userLogin   user's login
     * @param loggedUsers already logged users
     */
    private static void addLoggedUserToContext(HttpSession session,
                                               String userLogin,
                                               HashSet<String>
                                                       loggedUsers) {
        loggedUsers.add(userLogin);
        updateLoggedUsersInContext(session, loggedUsers);
    }

    /**
     * Updates hash set with already logged users
     *
     * @param session     user's session
     * @param loggedUsers already logged users
     */
    private static void updateLoggedUsersInContext(HttpSession session,
                                                   HashSet<String>
                                                           loggedUsers) {
        session.getServletContext().setAttribute(RequestAttributes
                .LOGGED_USERS, loggedUsers);
    }

    /**
     * Deletes user from already logged users
     *
     * @param session user's session
     */
    public static void deleteLoggedUserFromContext(HttpSession session) {
        HashSet<String> loggedUsers = getLoggedUsersFromContext(session);
        loggedUsers.remove(session.getAttribute(RequestAttributes
                .USER_LOGIN).toString());
        updateLoggedUsersInContext(session, loggedUsers);
    }
}
