package ua.finalproject.dao;

import java.util.List;
import java.util.Optional;

public interface Dao<T, ID> extends AutoCloseable {

    void create(T entity) throws Exception;

    Optional<T> findById(ID id);

    Optional<List<T>> findAll();

    void update(T entity);

    void delete(ID id);

    void deleteByParameter(String parameterName, String parameterValue);
}
