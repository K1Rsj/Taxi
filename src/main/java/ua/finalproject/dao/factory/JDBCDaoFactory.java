package ua.finalproject.dao.factory;

import ua.finalproject.dao.CarDao;
import ua.finalproject.dao.OrderDao;
import ua.finalproject.dao.UserDao;
import ua.finalproject.dao.impl.CarDaoImpl;
import ua.finalproject.dao.impl.OrderDaoImpl;
import ua.finalproject.dao.impl.UserDaoImpl;

import java.sql.Connection;

public class JDBCDaoFactory extends DaoFactory {
    @Override
    public UserDao createUserDao(Connection connection) {
        return new UserDaoImpl(connection);
    }

    @Override
    public CarDao createCarDao(Connection connection) {
        return new CarDaoImpl(connection);
    }

    @Override
    public OrderDao createOrderDao(Connection connection) {
        return new OrderDaoImpl(connection);
    }
}
