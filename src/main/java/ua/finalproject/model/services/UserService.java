package ua.finalproject.model.services;

import ua.finalproject.model.entities.full.User;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.Optional;

public interface UserService extends Service {

    void registerUser(User user) throws
            SQLIntegrityConstraintViolationException;

    Optional<User> findUserByLogin(String login);

    Integer getUserDiscount(String login);

    Optional<List<User>> showAllUsers();

    void deleteUserById(Integer id);
}
