package ua.finalproject.model.dao.mapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public interface ObjectMapper<T> {

    /**
     * Extracts entity from result set
     * @param resultSet result set
     * @return entity from result set
     * @throws SQLException if something wrong with DB
     */
    T extractFromResultSet(ResultSet resultSet) throws SQLException;

    /**
     * Creating query from car
     * @param entity entity
     * @param preparedStatement prepared statement
     * @throws SQLException if something wrong with DB
     */
    void setValuesForQuery(T entity, PreparedStatement preparedStatement) throws SQLException;

}
