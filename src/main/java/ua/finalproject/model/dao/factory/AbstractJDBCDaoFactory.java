package ua.finalproject.model.dao.factory;

import ua.finalproject.model.dao.CarDao;
import ua.finalproject.model.dao.CarTypeDao;
import ua.finalproject.model.dao.OrderDao;
import ua.finalproject.model.dao.UserDao;

import java.sql.Connection;

/**
 * Abstract factory for dao
 */
public abstract class AbstractJDBCDaoFactory {
    private static volatile AbstractJDBCDaoFactory abstractJDBCDaoFactory;

    /**
     * @return Dao factory
     */
    public static AbstractJDBCDaoFactory getInstance() {
        if (abstractJDBCDaoFactory == null) {
            synchronized (AbstractJDBCDaoFactory.class) {
                if (abstractJDBCDaoFactory == null) {
                    abstractJDBCDaoFactory = new JDBCDaoFactory();
                }
            }
        }
        return abstractJDBCDaoFactory;
    }

    public abstract UserDao createUserDao(Connection connection);

    public abstract CarDao createCarDao(Connection connection);

    public abstract OrderDao createOrderDao(Connection connection);

    public abstract CarTypeDao createCarTypeDao(Connection connection);
}
