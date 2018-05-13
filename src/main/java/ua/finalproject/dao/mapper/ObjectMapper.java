package ua.finalproject.dao.mapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public interface ObjectMapper<T> {

    T extractFromResultSet(ResultSet resultSet) throws SQLException;

    void setValuesForQuery(T entity, PreparedStatement preparedStatement) throws SQLException;

}
