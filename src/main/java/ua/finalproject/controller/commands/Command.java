package ua.finalproject.controller.commands;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.finalproject.util.BundleManager;

import javax.servlet.http.HttpServletRequest;

public interface Command {

    /**
     * Logger for Command classes
     *
     * @see LogManager
     */
    Logger logger = LogManager.getLogger(Command.class);

    /**
     * Bundle manager for command classes
     *
     * @see BundleManager
     */
    BundleManager bundleManager = BundleManager.INSTANCE;

    /**
     * Do some logic depends on command and returns path to jsp page
     *
     * @param request request from user
     * @return path to jsp page
     */
    String execute(HttpServletRequest request);
}
