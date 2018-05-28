package ua.finalproject.model.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.Optional;

public interface Dao<T, ID> extends AutoCloseable {

    /**
     * Logger for Dao classes
     *
     * @see LogManager
     */
    Logger logger = LogManager.getLogger(Dao.class);

    /**
     * Adds entity to DB
     *
     * @param entity entity that will be added to DB
     * @throws SQLIntegrityConstraintViolationException if entity value already exists in DB
     */
    void create(T entity) throws SQLIntegrityConstraintViolationException;

    /**
     * Finds entity by id
     *
     * @param id id of entity
     * @return entity that matches id
     */
    Optional<T> findById(ID id);

    /**
     * Finds all entities
     *
     * @return list of all entities
     */
    Optional<List<T>> findAll();

    /**
     * Deletes entity by id
     *
     * @param id id of entity
     */
    void delete(ID id);

    /**
     * Deletes entry from data source by parameter
     *
     * @param parameterName  name of parameter
     * @param parameterValue value of parameter
     */
    void deleteByParameter(String parameterName, String parameterValue);

    /**
     * Finds foreign key in table
     *
     * @param tableName name of table
     * @param entityId  entity id
     * @param idName    id name
     * @return foreign key
     */
    Integer findForeignKeyInTable(String tableName, String entityId, String idName);

}
