package ua.finalproject.controller.listeners;

import ua.finalproject.constants.jsp.RequestAttributes;
import ua.finalproject.controller.util.ContextUtil;
import ua.finalproject.controller.util.ControllerUtil;
import ua.finalproject.model.services.CarService;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.Locale;

public class SessionListener implements HttpSessionListener {
    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
            httpSessionEvent.getSession().setAttribute(RequestAttributes.LANGUAGE, Locale.getDefault());
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        ContextUtil.deleteLoggedUserFromContext(httpSessionEvent.getSession());
        CarService carService = new CarService();
        ControllerUtil.ChangeCarStateToFree(httpSessionEvent.getSession(), carService);
    }
}
