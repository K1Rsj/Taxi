package ua.finalproject.model.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.finalproject.dao.OrderDao;
import ua.finalproject.dao.UserDao;
import ua.finalproject.dao.connectionPool.ConnectionPoolHolder;
import ua.finalproject.dao.factory.DaoFactory;
import ua.finalproject.model.entities.impl.User;
import ua.finalproject.model.util.OrderPriceGenerator;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.Optional;

public class UserService {

    private static final Logger logger = LogManager.getLogger(UserService.class);

    public void registerUser(User user) throws SQLIntegrityConstraintViolationException {
        Connection connection = ConnectionPoolHolder.getConnection();
        try (UserDao userDao = DaoFactory.getInstance().createUserDao(connection)) {
            userDao.create(user);
        } catch (SQLIntegrityConstraintViolationException e) {
            throw new SQLIntegrityConstraintViolationException(e);
        } catch (Exception e) {
            logger.error("User registration error");
        }
    }

    public Optional<User> findUserByLogin(String login) {
        Connection connection = ConnectionPoolHolder.getConnection();
        try (UserDao userDao = DaoFactory.getInstance().createUserDao(connection)) {
            return userDao.findByLogin(login);
        } catch (Exception e) {
            logger.error("Find user by login error", e.getMessage());
        }
        return Optional.empty();
    }

    public Integer getUserDiscount(String login) {
        Connection connection = ConnectionPoolHolder.getConnection();
        Integer discount = 0;
        try (UserDao userDao = DaoFactory.getInstance().createUserDao(connection)) {
            discount = OrderPriceGenerator.getDiscountBasedOnMoneySpent(userDao.findByLogin(login).get().getMoneySpent());
        } catch (Exception e) {
            logger.error("Get user discount error", e.getMessage());
        }
        return discount;
    }

    public Optional<List<User>> showAllUsers() {
        Connection connection = ConnectionPoolHolder.getConnection();
        try (UserDao userDao = DaoFactory.getInstance().createUserDao(connection)) {
            return userDao.findAll();
        } catch (Exception e) {
            logger.error("Show all users error", e.getMessage());
        }
        return Optional.empty();
    }

    public void deleteUserById(Integer id) {
        Connection connection = ConnectionPoolHolder.getConnection();
        try (UserDao userDao = DaoFactory.getInstance().createUserDao(connection);
             OrderDao orderDao = DaoFactory.getInstance().createOrderDao(connection)) {
            connection.setAutoCommit(false);
            orderDao.delete(id);
            userDao.delete(id);
            connection.commit();
        } catch (Exception e) {
            logger.error("Delete user by id error", e.getMessage());
            SQLExceptionRollbackErrorHandle(connection);
        }
    }

    private void SQLExceptionRollbackErrorHandle(Connection connection) {
        try {
            connection.rollback();
        } catch (SQLException e1) {
            logger.error("Delete user by id connection rollback error", e1.getMessage());
        }
    }
}