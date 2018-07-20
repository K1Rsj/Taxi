package ua.finalproject.model.dao.hibernate.impl;

import org.hibernate.Session;
import org.hibernate.query.Query;
import ua.finalproject.constants.db.DbHQLQueries;
import ua.finalproject.model.dao.CarTypeDao;
import ua.finalproject.model.entities.full.Car;
import ua.finalproject.model.entities.full.CarType;

import java.util.Optional;

public class HibernateCarTypeDaoImpl extends AbstractHibernateDao<CarType> implements CarTypeDao {

    public HibernateCarTypeDaoImpl(Session session, String tableName) {
        super(session, tableName);
    }

    @Override
    public void updateDiscount(Integer id, Integer discount) {
        session.createQuery(DbHQLQueries.UPDATE_CAR_TYPE_SET_DISCOUNT).setParameter("discount", discount)
                .setParameter("id", id).executeUpdate();
    }
}
