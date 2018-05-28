package ua.finalproject.model.services.impl;

import ua.finalproject.constants.messages.LogMessages;
import ua.finalproject.model.dao.OrderDao;
import ua.finalproject.model.dao.UserDao;
import ua.finalproject.model.dao.connectionPool.ConnectionPoolHolder;
import ua.finalproject.model.entities.full.User;
import ua.finalproject.model.services.UserService;
import ua.finalproject.model.util.OrderPriceGenerator;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.Optional;

public class UserServiceImpl implements UserService {

    private UserServiceImpl() {
    }

    private static class UserServiceImplHolder {
        static final UserServiceImpl instance = new UserServiceImpl();
    }

    public static UserServiceImpl getInstance() {
        return UserServiceImplHolder.instance;
    }

    /**
     * Adds user to DB.
     *
     * @param user user
     * @throws SQLIntegrityConstraintViolationException if user's login or email already
     *                                                  exists in DB
     */
    @Override
    public void registerUser(User user) throws SQLIntegrityConstraintViolationException {
        Connection connection = ConnectionPoolHolder.getConnection();
        try (UserDao userDao = daoFactory.createUserDao(connection)) {
            userDao.create(user);
        } catch (SQLIntegrityConstraintViolationException e) {
            throw new SQLIntegrityConstraintViolationException(e);
        } catch (Exception e) {
            logger.error(LogMessages.AUTO_CLOSABLE_RESOURCE_ERROR_IN_REGISTER_USER);
        }
    }

    /**
     * Finds user by login
     *
     * @param login user's login
     * @return user
     */
    @Override
    public Optional<User> findUserByLogin(String login) {
        Connection connection = ConnectionPoolHolder.getConnection();
        try (UserDao userDao = daoFactory.createUserDao(connection)) {
            return userDao.findByLogin(login);
        } catch (Exception e) {
            logger.error(LogMessages.AUTO_CLOSABLE_RESOURCE_ERROR_IN_FIND_USER_BY_LOGIN, e
                    .getMessage());
        }
        return Optional.empty();
    }

    /**
     * Gets user's discount
     *
     * @param login user's login
     * @return user's discount
     */
    @Override
    public Integer getUserDiscount(String login) {
        Connection connection = ConnectionPoolHolder.getConnection();
        Integer discount = 0;
        try (UserDao userDao = daoFactory.createUserDao(connection)) {
            discount = OrderPriceGenerator.getDiscountBasedOnMoneySpent(userDao.findByLogin
                    (login).get().getMoneySpent());
        } catch (Exception e) {
            logger.error(LogMessages.AUTO_CLOSABLE_RESOURCE_ERROR_IN_GET_USER_DISCOUNT, e
                    .getMessage());
        }
        return discount;
    }

    /**
     * Shows all users
     *
     * @return list of all users
     */
    @Override
    public Optional<List<User>> showAllUsers() {
        Connection connection = ConnectionPoolHolder.getConnection();
        try (UserDao userDao = daoFactory.createUserDao(connection)) {
            return userDao.findAll();
        } catch (Exception e) {
            logger.error(LogMessages.AUTO_CLOSABLE_RESOURCE_ERROR_IN_SHOW_ALL_USERS, e
                    .getMessage());
        }
        return Optional.empty();
    }

    /**
     * Deletes user by id
     *
     * @param id user's id
     */
    @Override
    public void deleteUserById(Integer id) {
        Connection connection = ConnectionPoolHolder.getConnection();
        try (UserDao userDao = daoFactory.createUserDao(connection);
             OrderDao orderDao = daoFactory.createOrderDao(connection)) {
            connection.setAutoCommit(false);
            orderDao.delete(id);
            userDao.delete(id);
            connection.commit();
        } catch (SQLException e) {
            logger.error(LogMessages.DELETE_USER_BY_ID_ERROR, e.getMessage());
            SQLExceptionRollbackErrorHandle(connection);
            throw new RuntimeException(e);
        } catch (Exception e) {
            logger.error(LogMessages.AUTO_CLOSABLE_RESOURCE_ERROR_IN_DELETE_USER_BY_ID, e
                    .getMessage());
        }
    }

    /**
     * Handles rollback error
     *
     * @param connection connection
     */
    private void SQLExceptionRollbackErrorHandle(Connection connection) {
        try {
            connection.rollback();
        } catch (SQLException e1) {
            logger.error(LogMessages.DELETE_USER_BY_ID_CONNECTION_ROLLBACK_ERROR,
                    e1.getMessage());
            throw new RuntimeException(e1);
        }
    }
}