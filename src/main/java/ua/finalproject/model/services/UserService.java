package ua.finalproject.model.services;

import ua.finalproject.dao.UserDao;
import ua.finalproject.dao.connectionPool.ConnectionPoolHolder;
import ua.finalproject.dao.factory.DaoFactory;
import ua.finalproject.model.entities.impl.User;

import java.sql.Connection;
import java.sql.SQLIntegrityConstraintViolationException;
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
        return null;
    }
}
