package ua.finalproject.model.dao;

import ua.finalproject.model.entities.full.User;

import java.util.Optional;

public interface UserDao extends Dao<User, Integer> {

    /**
     * Finds user by login
     *
     * @param login user's login
     * @return user that matches login
     */
    Optional<User> findByLogin(String login);

    /**
     * Update user's money spent
     *
     * @param userId     user's id
     * @param moneySpent user's spent money
     */
    void updateMoneySpent(Integer userId, Long moneySpent);
}
