package ua.finalproject.model.dao.factory;

import ua.finalproject.model.dao.CarDao;
import ua.finalproject.model.dao.CarTypeDao;
import ua.finalproject.model.dao.OrderDao;
import ua.finalproject.model.dao.UserDao;

import java.sql.Connection;

/**
 * Abstract factory for dao
 */
public abstract class DaoFactory {
    private static volatile DaoFactory daoFactory;

    /**
     * @return Dao factory
     */
    public static DaoFactory getInstance() {
        if (daoFactory == null) {
            synchronized (DaoFactory.class) {
                if (daoFactory == null) {
                    daoFactory = new JDBCDaoFactory();
                }
            }
        }
        return daoFactory;
    }

    public abstract UserDao createUserDao(Connection connection);

    public abstract CarDao createCarDao(Connection connection);

    public abstract OrderDao createOrderDao(Connection connection);

    public abstract CarTypeDao createCarTypeDao(Connection connection);
}
