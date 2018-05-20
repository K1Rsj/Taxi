package ua.finalproject.controller.commands;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.finalproject.util.BundleManager;

import javax.servlet.http.HttpServletRequest;

public interface Command {
    Logger logger = LogManager.getLogger(Command.class);

    BundleManager bundleManager = BundleManager.INSTANCE;

    String execute(HttpServletRequest request);
}
