package ua.finalproject.model.dao;

import ua.finalproject.model.entities.impl.Order;

import java.util.List;
import java.util.Optional;

public interface OrderDao extends Dao<Order, Integer> {
    Optional<List<Order>> findOrdersByUserLogin(String login);
}
