package ua.finalproject.controller.listeners;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.HashSet;
import java.util.Locale;

public class ContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        servletContextEvent.getServletContext().setAttribute("loggedUsers", new HashSet<String>());
        Locale.setDefault(Locale.US);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
