package ua.finalproject.model.dao;

import ua.finalproject.model.entities.full.Order;

import java.util.List;
import java.util.Optional;

public interface OrderDao extends Dao<Order, Integer> {

    /**
     * Finds all user's orders
     *
     * @param id user's id
     * @return list of all user's orders
     */
    Optional<List<Order>> findOrdersByUserId(Integer id);
}
