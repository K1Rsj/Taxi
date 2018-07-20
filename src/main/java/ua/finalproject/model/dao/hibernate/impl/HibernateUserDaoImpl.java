package ua.finalproject.model.dao.hibernate.impl;

import org.hibernate.Session;
import ua.finalproject.constants.db.DbHQLQueries;
import ua.finalproject.model.dao.UserDao;
import ua.finalproject.model.entities.full.User;

import java.util.Optional;

public class HibernateUserDaoImpl extends AbstractHibernateDao<User> implements UserDao {

    public HibernateUserDaoImpl(Session session, String entityName) {
        super(session, entityName);
    }

    @Override
    public Optional<User> findByLogin(String login) {
        return Optional.of(session.createQuery(DbHQLQueries.FIND_USER_BY_LOGIN, User.class)
                .setParameter("login", login).getSingleResult());
    }

    @Override
    public void updateMoneySpent(Integer userId, Long moneySpent) {
        session.createQuery(DbHQLQueries.UPDATE_USER_MONEY_SPENT).setParameter("moneySpent", moneySpent)
                .setParameter("id", userId).executeUpdate();
    }
}
