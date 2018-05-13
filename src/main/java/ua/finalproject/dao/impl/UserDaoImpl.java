package ua.finalproject.dao.impl;

import ua.finalproject.dao.UserDao;
import ua.finalproject.dao.mapper.UserMapper;
import ua.finalproject.model.entities.impl.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDaoImpl implements UserDao {

    private Connection connection;

    public UserDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(User user) throws SQLIntegrityConstraintViolationException {
        UserMapper userMapper = new UserMapper();
        try (PreparedStatement preparedStatement = connection
                .prepareStatement("INSERT INTO users(login, password, email, first_name, second_name, phone_number) " +
                        "VALUES (?,?,?,?,?,?) ")) {
            userMapper.setValuesForQuery(user, preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLIntegrityConstraintViolationException e) {
            e.printStackTrace();
            throw new SQLIntegrityConstraintViolationException(e);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<User> findById(Integer id) {
        UserMapper userMapper = new UserMapper();
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM USERS WHERE id_user = ?")) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return Optional.of(userMapper.extractFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Optional<List<User>> findAll() {
        List<User> allUsers = new ArrayList<>();
        UserMapper userMapper = new UserMapper();
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT *" +
                "  FROM USERS");
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                allUsers.add(userMapper.extractFromResultSet(resultSet));
            }
            return Optional.of(allUsers);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public void delete(Integer id) {
        try (PreparedStatement preparedStatement = connection
                .prepareStatement("DELETE FROM USERS WHERE id_user = ?")) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<User> findByLogin(String login) {
        UserMapper userMapper = new UserMapper();
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM USERS WHERE LOGIN = ?")) {
            preparedStatement.setString(1, login);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return Optional.of(userMapper.extractFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public void updateMoneySpent(Integer userId, Long moneySpent) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("UPDATE USERS SET money_spent = ?" +
                " where id_user = ?")) {
            preparedStatement.setLong(1, moneySpent);
            preparedStatement.setInt(2, userId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void close() throws Exception {
        connection.close();
    }
}
