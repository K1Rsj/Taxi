package ua.finalproject.model.entities;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.finalproject.model.dao.factory.AbstractJDBCDaoFactory;

public interface Lazy {

    Logger logger = LogManager.getLogger(Lazy.class);
    AbstractJDBCDaoFactory ABSTRACT_JDBC_DAO_FACTORY = AbstractJDBCDaoFactory.getInstance();
}
