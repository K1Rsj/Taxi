package ua.finalproject.model.entities;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.finalproject.model.dao.factory.DaoFactory;

public interface Lazy {

    Logger logger = LogManager.getLogger(Lazy.class);
    DaoFactory daoFactory = DaoFactory.getInstance();
}
