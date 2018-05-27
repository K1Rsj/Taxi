package ua.finalproject.model.dao.impl;

import ua.finalproject.constants.db.DbQueries;
import ua.finalproject.constants.db.TableColumnNames;
import ua.finalproject.constants.db.TableNames;
import ua.finalproject.constants.messages.LogMessages;
import ua.finalproject.model.dao.AbstractDao;
import ua.finalproject.model.dao.UserDao;
import ua.finalproject.model.dao.util.QueryContainer;
import ua.finalproject.model.dao.util.UtilDao;
import ua.finalproject.model.entities.full.User;
import ua.finalproject.util.LogMessageBuilder;

import java.sql.*;
import java.util.Optional;

/**
 * Implementation for user dao
 */
public class UserDaoImpl extends AbstractDao<User> implements UserDao {

    public UserDaoImpl(String tableName, Connection connection) {
        super(tableName, connection);
    }

    /**
     * Extracts user from result set
     * @param resultSet result set
     * @return user from result set
     * @throws SQLException if something went wrong in DB
     */
    @Override
    public User extractFromResultSet(ResultSet resultSet) throws SQLException {
        Integer id = resultSet.getInt(TableColumnNames.ID_USER);
        String login = resultSet.getString(TableColumnNames.LOGIN);
        String password = resultSet.getString(TableColumnNames.PASSWORD);
        String email = resultSet.getString(TableColumnNames.EMAIL);
        String firstName = resultSet.getString(TableColumnNames.FIRST_NAME);
        String secondName = resultSet.getString(TableColumnNames.SECOND_NAME);
        String phoneNumber = resultSet.getString(TableColumnNames.PHONE_NUMBER);
        Long moneySpent = resultSet.getLong(TableColumnNames.MONEY_SPENT);
        User.Role role = UtilDao.parseUserRole(resultSet.getString(TableColumnNames.ROLE));

        return User.builder()
                .id(id)
                .login(login)
                .password(password)
                .email(email)
                .firstName(firstName)
                .secondName(secondName)
                .phoneNumber(phoneNumber)
                .moneySpent(moneySpent)
                .role(role)
                .build();
    }

    /**
     * Adds user to DB
     * @param user that will be added to DB
     * @throws SQLIntegrityConstraintViolationException if user's login or email already exists in DB
     */
    @Override
    public void create(User user) throws SQLIntegrityConstraintViolationException {
        try (PreparedStatement preparedStatement = connection
                .prepareStatement(DbQueries.INSERT_INTO_USERS)) {
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getFirstName());
            preparedStatement.setString(5, user.getSecondName());
            preparedStatement.setString(6, user.getPhoneNumber());
            preparedStatement.executeUpdate();
        } catch (SQLIntegrityConstraintViolationException e) {
            throw new SQLIntegrityConstraintViolationException(e);
        } catch (SQLException e) {
            logger.error(LogMessageBuilder.INSTANCE.createEntryError(TableNames.USERS), e.getMessage());
            throw new RuntimeException(e);
        }
    }

    /**
     * Finds user by login
     * @param login user's login
     * @return user that matches login
     */
    @Override
    public Optional<User> findByLogin(String login) {
        return Optional.of(findOneByQuery(QueryContainer.INSTANCE.findUserByLogin(login)));
    }

    /**
     * Update user's money spent
     * @param userId user's id
     * @param moneySpent user's spent money
     */
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
}
