package ua.finalproject.dao.factory;

import ua.finalproject.dao.CarDao;
import ua.finalproject.dao.CarTypeDao;
import ua.finalproject.dao.OrderDao;
import ua.finalproject.dao.UserDao;

import java.sql.Connection;

public abstract class DaoFactory {
    private static volatile DaoFactory daoFactory;

    public abstract UserDao createUserDao(Connection connection);
    public abstract CarDao createCarDao(Connection connection);
    public abstract OrderDao createOrderDao(Connection connection);
    public abstract CarTypeDao createCarTypeDao(Connection connection);

    public static DaoFactory getInstance(){
        if( daoFactory == null ){
            synchronized (DaoFactory.class){
                if(daoFactory == null){
                    daoFactory = new JDBCDaoFactory();
                }
            }
        }
        return daoFactory;
    }
}
