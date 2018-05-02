package ua.finalproject.dao;

import ua.finalproject.model.entities.impl.User;

import java.util.Optional;

public interface UserDao extends Dao<User, Integer> {
    Optional<User> findByLogin(String login);

}
