package ua.finalproject.model.dao.hibernate.factory;

import org.hibernate.Session;
import ua.finalproject.constants.db.EntityNames;
import ua.finalproject.model.dao.CarDao;
import ua.finalproject.model.dao.CarTypeDao;
import ua.finalproject.model.dao.OrderDao;
import ua.finalproject.model.dao.UserDao;
import ua.finalproject.model.dao.hibernate.impl.HibernateCarDaoImpl;
import ua.finalproject.model.dao.hibernate.impl.HibernateCarTypeDaoImpl;
import ua.finalproject.model.dao.hibernate.impl.HibernateOrderDaoImpl;
import ua.finalproject.model.dao.hibernate.impl.HibernateUserDaoImpl;

public class HibernateDaoFactory extends AbstractHibernateDaoFactory {

    @Override
    public UserDao createHibernateUserDao(Session session) {
        return new HibernateUserDaoImpl(session, EntityNames.USER);
    }

    @Override
    public CarDao createHibernateCarDao(Session session) {
        return new HibernateCarDaoImpl(session, EntityNames.CAR);
    }

    @Override
    public CarTypeDao createHibernateCarTypeDao(Session session) {
        return new HibernateCarTypeDaoImpl(session, EntityNames.CAR_TYPE);
    }

    @Override
    public OrderDao createHibernateOrderDao(Session session) {
        return new HibernateOrderDaoImpl(session, EntityNames.ORDER);
    }
}
