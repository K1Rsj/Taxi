package ua.finalproject.controller.listeners;

import ua.finalproject.controller.util.ControllerUtil;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class SessionListener implements HttpSessionListener {
    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {

    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        ControllerUtil.deleteLoggedUserFromContext(httpSessionEvent.getSession());
    }
}
