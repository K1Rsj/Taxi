package ua.finalproject.model.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.Optional;

public interface Dao<T, ID> extends AutoCloseable {

    Logger logger = LogManager.getLogger(Dao.class);

    void create(T entity) throws SQLIntegrityConstraintViolationException;

    Optional<T> findById(ID id);

    Optional<List<T>> findAll();

    void delete(ID id);

}
