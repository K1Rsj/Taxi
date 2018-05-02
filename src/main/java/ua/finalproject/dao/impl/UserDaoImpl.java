package ua.finalproject.dao.impl;

import ua.finalproject.dao.UserDao;
import ua.finalproject.dao.mapper.UserMapper;
import ua.finalproject.model.entities.impl.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.Optional;

public class UserDaoImpl implements UserDao {

    private Connection connection;

    public UserDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Optional<User> findByLogin(String login) {
        return Optional.empty();
    }

    @Override
    public void create(User user) throws SQLException {
        UserMapper userMapper = new UserMapper();
        try (PreparedStatement preparedStatement = connection
                .prepareStatement("INSERT INTO users(login, password, email, first_name, second_name, phone_number) " +
                        "VALUES (?,?,?,?,?,?) ")) {
            userMapper.setValuesForQuery(user, preparedStatement);
            preparedStatement.executeUpdate();
        }catch (SQLIntegrityConstraintViolationException e) {
            e.printStackTrace();
            throw new SQLIntegrityConstraintViolationException(e);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException(e);
        }
    }

    @Override
    public Optional<User> findById(Integer integer) {
        return Optional.empty();
    }

    @Override
    public Optional<List<User>> findAll() {
        return Optional.empty();
    }

    @Override
    public void update(User entity) {

    }

    @Override
    public void delete(Integer integer) {

    }

    @Override
    public void deleteByParameter(String parameterName, String parameterValue) {

    }

    @Override
    public void close() throws Exception {
        connection.close();
    }
}
