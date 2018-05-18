package ua.finalproject.model.dao;

import ua.finalproject.model.entities.impl.User;

import java.util.Optional;

public interface UserDao extends Dao<User, Integer> {
    Optional<User> findByLogin(String login);
    void updateMoneySpent(Integer userId, Long moneySpent);
}
