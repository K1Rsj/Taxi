package ua.finalproject.controller.listeners;

import ua.finalproject.constants.jsp.RequestAttributes;
import ua.finalproject.controller.util.ContextUtil;
import ua.finalproject.controller.util.ControllerUtil;
import ua.finalproject.model.services.ServiceFactory;
import ua.finalproject.model.services.impl.CarServiceImpl;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.Locale;

public class SessionListener implements HttpSessionListener {

    /**
     * Adds default locale to user's session
     *
     * @param httpSessionEvent session event
     */
    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        httpSessionEvent.getSession().setAttribute(RequestAttributes.LANGUAGE, Locale
                .getDefault());
    }

    /**
     * Deletes user from context's logged users attribute.
     * Sets car state to free if user make order and didn't accept it or cancel
     *
     * @param httpSessionEvent session event
     */
    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        ContextUtil.deleteLoggedUserFromContext(httpSessionEvent.getSession());
        CarServiceImpl carServiceImpl = ServiceFactory.getInstance()
                .createCarService();
        ControllerUtil.ChangeCarStateToFree(httpSessionEvent.getSession(), carServiceImpl);
    }
}
