package ua.finalproject.model.services;

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

    public void registerUser(User user) throws SQLIntegrityConstraintViolationException {
        Connection connection = ConnectionPoolHolder.getConnection();
        try (UserDao userDao = DaoFactory.getInstance().createUserDao(connection)) {
            userDao.create(user);
        } catch (SQLIntegrityConstraintViolationException e) {
            e.printStackTrace();
            throw new SQLIntegrityConstraintViolationException(e);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Optional<User> findUserByLogin(String login) {
        Connection connection = ConnectionPoolHolder.getConnection();
        try (UserDao userDao = DaoFactory.getInstance().createUserDao(connection)) {
            return userDao.findByLogin(login);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public Integer getUserDiscount(String login) {
        Connection connection = ConnectionPoolHolder.getConnection();
        Integer discount = 0;
        try (UserDao userDao = DaoFactory.getInstance().createUserDao(connection)) {
            discount = OrderPriceGenerator.getDiscountBasedOnMoneySpent(userDao.findByLogin(login).get().getMoneySpent());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return discount;
    }

    public Optional<List<User>> showAllUsers() {
        Connection connection = ConnectionPoolHolder.getConnection();
        try (UserDao userDao = DaoFactory.getInstance().createUserDao(connection)) {
            return userDao.findAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public void deleteUserById(Integer id) throws SQLException {
        Connection connection = ConnectionPoolHolder.getConnection();
        try (UserDao userDao = DaoFactory.getInstance().createUserDao(connection);
             OrderDao orderDao = DaoFactory.getInstance().createOrderDao(connection)) {
            connection.setAutoCommit(false);
            orderDao.delete(id);
            userDao.delete(id);
            connection.commit();
        } catch (Exception e) {
            e.printStackTrace();
            connection.rollback();
        }
    }
}