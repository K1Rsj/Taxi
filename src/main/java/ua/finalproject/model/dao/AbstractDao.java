package ua.finalproject.model.dao;

import ua.finalproject.constants.messages.LogMessages;
import ua.finalproject.model.dao.util.QueryContainer;
import ua.finalproject.model.entities.Entity;
import ua.finalproject.util.LogMessageBuilder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Abstract class for DAO
 *
 * @param <T>
 */
public abstract class AbstractDao<T extends Entity<Integer>> implements Dao<T, Integer> {

    /**
     * @see Connection
     */
    public Connection connection;
    /**
     * name of table in data base
     */
    private String tableName;

    public AbstractDao(String tableName, Connection connection) {
        this.tableName = tableName;
        this.connection = connection;
    }

    /**
     * Finds entry by id
     *
     * @param id id of entry
     * @return entry that matches id
     */
    @Override
    public Optional<T> findById(Integer id) {
        String query = QueryContainer.INSTANCE.findFromTableById(tableName, id);
        return findOneByQuery(query);
    }

    /**
     * Finds all entries
     *
     * @return list of all entries
     */
    @Override
    public Optional<List<T>> findAll() {
        String query = QueryContainer.INSTANCE.findAllFromTable(tableName);
        return findAllByQuery(query);
    }

    /**
     * Deletes entry by id
     *
     * @param id id of entry
     */
    @Override
    public void delete(Integer id) {
        String query = QueryContainer.INSTANCE.deleteFromTableById(tableName, id);
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error(LogMessageBuilder.INSTANCE.deleteEntryError(tableName, id), e
                    .getMessage());
            throw new RuntimeException(e);
        }
    }

    /**
     * Deletes entry by parameter
     *
     * @param parameterName  name of parameter
     * @param parameterValue parameter value
     */
    public void deleteByParameter(String parameterName, String parameterValue) {
        String query = QueryContainer.INSTANCE.deleteFromTableByParameter(tableName,
                parameterName, parameterValue);
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error(LogMessageBuilder.INSTANCE.deleteEntryByParameterError(tableName,
                    parameterName), e.getMessage());
            throw new RuntimeException(e);
        }
    }

    /**
     * @param tableName name of table
     * @param entityId  entry id
     * @param idName    id name
     * @return id name
     */
    @Override
    public Integer findForeignKeyInTable(String tableName, String entityId, String idName) {
        String query = QueryContainer.INSTANCE.findForeignKeyIdFromTable(tableName,
                entityId, idName);
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            try (ResultSet resultSet = ps.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt(idName);
                } else {
                    return null;
                }
            }
        } catch (SQLException e) {
            logger.error(LogMessages.FIND_OTHER_ID_FROM_TABLE_ERROR, e.getMessage());
            throw new RuntimeException(e);
        }
    }

    /**
     * Finds one entry by query
     *
     * @param query query
     * @return entry
     */
    protected Optional<T> findOneByQuery(String query) {
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            try (ResultSet resultSet = ps.executeQuery()) {
                if (resultSet.next()) {
                    return Optional.of(extractFromResultSet(resultSet));
                } else {
                    return Optional.empty();
                }
            }
        } catch (SQLException e) {
            logger.error(LogMessages.FIND_BY_QUERY_ERROR, e.getMessage());
            throw new RuntimeException(e);
        }
    }

    /**
     * Finds all entry by query
     *
     * @param query query
     * @return list of entries
     */
    protected Optional<List<T>> findAllByQuery(String query) {
        List<T> resultList = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(query);
             ResultSet resultSet = ps.executeQuery()) {
            while (resultSet.next()) {
                resultList.add(extractFromResultSet(resultSet));
            }
            return Optional.of(resultList);
        } catch (SQLException e) {
            logger.error(LogMessages.FIND_ALL_BY_QUERY_ERROR);
            throw new RuntimeException(e);
        }
    }

    /**
     * Auto-closing the connection
     */
    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            logger.error(LogMessages.CONNECTION_CLOSE_ERROR, e.getMessage());
            throw new RuntimeException(e);
        }
    }

    /**
     * Extracts entity from result set
     *
     * @param resultSet result set
     * @return entity from result set
     * @throws SQLException if something went wrong in DB
     */
    protected abstract T extractFromResultSet(ResultSet resultSet) throws SQLException;
}
