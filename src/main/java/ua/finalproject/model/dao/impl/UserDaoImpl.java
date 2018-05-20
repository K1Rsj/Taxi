package ua.finalproject.model.dao.impl;

import ua.finalproject.constants.db.DbQueries;
import ua.finalproject.constants.db.TableNames;
import ua.finalproject.constants.messages.LogMessages;
import ua.finalproject.model.dao.UserDao;
import ua.finalproject.model.dao.mapper.UserMapper;
import ua.finalproject.model.entities.impl.User;
import ua.finalproject.util.LogMessageBuilder;

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
                .prepareStatement(DbQueries.INSERT_INTO_USERS)) {
            userMapper.setValuesForQuery(user, preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLIntegrityConstraintViolationException e) {
            throw new SQLIntegrityConstraintViolationException(e);
        } catch (SQLException e) {
            logger.error(LogMessageBuilder.INSTANCE.createEntryError(TableNames.USERS), e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<User> findById(Integer id) {
        UserMapper userMapper = new UserMapper();
        try (PreparedStatement preparedStatement = connection.prepareStatement(DbQueries.SELECT_FROM_USERS_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return Optional.of(userMapper.extractFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            logger.error(LogMessageBuilder.INSTANCE.findByIdError(TableNames.USERS), e.getMessage());
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }

    @Override
    public Optional<List<User>> findAll() {
        List<User> allUsers = new ArrayList<>();
        UserMapper userMapper = new UserMapper();
        try (PreparedStatement preparedStatement = connection.prepareStatement(DbQueries.SELECT_ALL_FROM_USERS);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                allUsers.add(userMapper.extractFromResultSet(resultSet));
            }
            return Optional.of(allUsers);
        } catch (SQLException e) {
            logger.error(LogMessageBuilder.INSTANCE.findAllError(TableNames.USERS), e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Integer id) {
        try (PreparedStatement preparedStatement = connection
                .prepareStatement(DbQueries.DELETE_FROM_USERS_BY_ID)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error(LogMessageBuilder.INSTANCE.deleteEntryError(TableNames.USERS, id), e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<User> findByLogin(String login) {
        UserMapper userMapper = new UserMapper();
        try (PreparedStatement preparedStatement = connection.prepareStatement(DbQueries.SELECT_FROM_USERS_BY_LOGIN)) {
            preparedStatement.setString(1, login);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return Optional.of(userMapper.extractFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            logger.error(LogMessages.FIND_BY_LOGIN_ERROR, e.getMessage());
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }

    @Override
    public void updateMoneySpent(Integer userId, Long moneySpent) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(DbQueries.UPDATE_USER_MONEY_SPENT)) {
            preparedStatement.setLong(1, moneySpent);
            preparedStatement.setInt(2, userId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error(LogMessages.UPDATE_USER_MONEY_SPENT_ERROR, e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            logger.error(LogMessages.CONNECTION_CLOSE_ERROR, e.getMessage());
            throw new RuntimeException(e);
        }
    }
}