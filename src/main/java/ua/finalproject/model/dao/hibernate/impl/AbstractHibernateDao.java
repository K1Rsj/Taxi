package ua.finalproject.model.dao.hibernate.impl;

import org.hibernate.Session;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.query.Query;
import ua.finalproject.model.dao.Dao;
import ua.finalproject.model.dao.util.HQLQueryContainer;
import ua.finalproject.model.entities.Entity;

import java.util.List;
import java.util.Optional;

public abstract class AbstractHibernateDao<T extends Entity<Integer>> implements Dao<T, Integer> {

    public Session session;
    private String entityName;

    public AbstractHibernateDao(Session session, String entityName) {
        this.session = session;
        this.entityName = entityName;
    }

    @Override
    public void create(T entity) throws ConstraintViolationException {
        session.persist(entity);
    }

    @Override
    public Optional<T> findById(Integer id) {
        String query = HQLQueryContainer.INSTANCE.findFromTableById(entityName, id);
        Query<T> result = session.createQuery(query);
        return Optional.of(result.getSingleResult());
    }

    @Override
    public Optional<List<T>> findAll() {
        String query = HQLQueryContainer.INSTANCE.findAllFromTable(entityName);
        Query<T> result = session.createQuery(query);
        return Optional.of(result.getResultList());
    }

    @Override
    public void delete(Integer id) {
        String query = HQLQueryContainer.INSTANCE.deleteFromTableById(entityName, id);
        session.createQuery(query).executeUpdate();
    }

    @Override
    public void deleteByParameter(String parameterName, String parameterValue) {
        String query = HQLQueryContainer.INSTANCE.deleteFromTableByParameter(entityName, parameterName, parameterValue);
        session.createQuery(query).executeUpdate();
    }

    @Override
    public void close() {
        session.close();
    }

}
