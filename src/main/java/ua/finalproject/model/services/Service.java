package ua.finalproject.model.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.finalproject.model.dao.factory.AbstractJDBCDaoFactory;

public interface Service {

    AbstractJDBCDaoFactory ABSTRACT_JDBC_DAO_FACTORY = AbstractJDBCDaoFactory.getInstance();
    Logger logger = LogManager.getLogger(Service.class);
}
