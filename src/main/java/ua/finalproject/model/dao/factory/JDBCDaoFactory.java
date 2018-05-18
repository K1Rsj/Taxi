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

    @Override
    public CarTypeDao createCarTypeDao(Connection connection) {
        return new CarTypeDaoImpl(connection);
    }


}
