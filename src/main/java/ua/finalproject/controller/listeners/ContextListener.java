package ua.finalproject.controller.listeners;

import ua.finalproject.constants.jsp.RequestAttributes;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.HashSet;
import java.util.Locale;

public class ContextListener implements ServletContextListener {

    /**
     * Adds to servlet context hash set that will store logged users.
     * Sets default locale to english
     *
     * @param servletContextEvent context event
     */
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        servletContextEvent.getServletContext().setAttribute(RequestAttributes.LOGGED_USERS,
                new HashSet<String>());
        Locale.setDefault(Locale.US);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
