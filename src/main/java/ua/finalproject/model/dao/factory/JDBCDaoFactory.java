package ua.finalproject.model.dao.factory;

import ua.finalproject.model.dao.CarDao;
import ua.finalproject.model.dao.CarTypeDao;
import ua.finalproject.model.dao.OrderDao;
import ua.finalproject.model.dao.UserDao;
import ua.finalproject.model.dao.impl.CarDaoImpl;
import ua.finalproject.model.dao.impl.CarTypeDaoImpl;
import ua.finalproject.model.dao.impl.OrderDaoImpl;
import ua.finalproject.model.dao.impl.UserDaoImpl;

import java.sql.Connection;

/**
 * JDBC Dao factory
 * @see UserDaoImpl
 * @see CarDaoImpl
 * @see OrderDaoImpl
 * @see CarTypeDaoImpl
 */
public class JDBCDaoFactory extends DaoFactory {

    /**
     *
     * @param connection connection
     * @return implementation of user dao
     */
    @Override
    public UserDao createUserDao(Connection connection) {
        return new UserDaoImpl(connection);
    }

    /**
     *
     * @param connection connection
     * @return implementation of car dao
     */
    @Override
    public CarDao createCarDao(Connection connection) {
        return new CarDaoImpl(connection);
    }

    /**
     *
     * @param connection connection
     * @return implementation of order dao
     */
    @Override
    public OrderDao createOrderDao(Connection connection) {
        return new OrderDaoImpl(connection);
    }

    /**
     *
     * @param connection connection
     * @return implementation of car type dao
     */
    @Override
    public CarTypeDao createCarTypeDao(Connection connection) {
        return new CarTypeDaoImpl(connection);
    }


}
