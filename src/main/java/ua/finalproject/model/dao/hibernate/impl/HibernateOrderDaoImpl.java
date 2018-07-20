package ua.finalproject.model.dao.hibernate.impl;

import org.hibernate.Session;
import ua.finalproject.constants.db.DbHQLQueries;
import ua.finalproject.constants.db.EntityNames;
import ua.finalproject.model.dao.OrderDao;
import ua.finalproject.model.dao.util.HQLQueryContainer;
import ua.finalproject.model.entities.full.Order;

import java.util.List;
import java.util.Optional;

public class HibernateOrderDaoImpl extends AbstractHibernateDao<Order> implements OrderDao {

    public HibernateOrderDaoImpl(Session session, String entityName) {
        super(session, entityName);
    }

    @Override
    public Optional<List<Order>> findOrdersByUserId(Integer id) {
        return Optional.of(session.createQuery(DbHQLQueries.FIND_ORDERS_BY_USER_ID,
                Order.class).setParameter("userId", id).getResultList());
    }
}
