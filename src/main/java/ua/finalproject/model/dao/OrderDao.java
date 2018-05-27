package ua.finalproject.model.dao;

import ua.finalproject.model.entities.full.Order;

import java.util.List;
import java.util.Optional;

public interface OrderDao extends Dao<Order, Integer> {

    /**
     * Finds all user's orders
     * @param login user's login
     * @return list of all user's orders
     */
    Optional<List<Order>> findOrdersByUserLogin(String login);
}
