package ua.finalproject.controller.listeners;

import ua.finalproject.controller.util.ContexUtil;
import ua.finalproject.controller.util.ControllerUtil;
import ua.finalproject.model.services.CarService;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class SessionListener implements HttpSessionListener {
    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {

    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        ContexUtil.deleteLoggedUserFromContext(httpSessionEvent.getSession());
        CarService carService = new CarService();
        ControllerUtil.ChangeCarStateToFree(httpSessionEvent.getSession(), carService);
    }
}
