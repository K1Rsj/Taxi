package ua.finalproject.model.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.finalproject.model.dao.factory.DaoFactory;

public interface Service {

    DaoFactory daoFactory = DaoFactory.getInstance();
    Logger logger = LogManager.getLogger(Service.class);
}
