package ua.finalproject.model.dao.hibernate.factory;

import org.hibernate.Session;
import ua.finalproject.model.dao.CarDao;
import ua.finalproject.model.dao.CarTypeDao;
import ua.finalproject.model.dao.OrderDao;
import ua.finalproject.model.dao.UserDao;

public abstract class AbstractHibernateDaoFactory {

    private static volatile AbstractHibernateDaoFactory abstractHibernateDaoFactory;

    /**
     * @return Dao factory
     */
    public static AbstractHibernateDaoFactory getInstance() {
        if (abstractHibernateDaoFactory == null) {
            synchronized (AbstractHibernateDaoFactory.class) {
                if (abstractHibernateDaoFactory == null) {
                    abstractHibernateDaoFactory = new HibernateDaoFactory();
                }
            }
        }
        return abstractHibernateDaoFactory;
    }

    public abstract UserDao createHibernateUserDao(Session session);

    public abstract CarDao createHibernateCarDao(Session session);

    public abstract CarTypeDao createHibernateCarTypeDao(Session session);

    public abstract OrderDao createHibernateOrderDao(Session session);

}
