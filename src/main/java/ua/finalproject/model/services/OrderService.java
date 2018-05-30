package ua.finalproject.model.services;

import ua.finalproject.model.entities.full.Order;
import ua.finalproject.model.exceptions.NoFreeCarWithSuchTypeException;

import java.util.List;
import java.util.Optional;

public interface OrderService extends Service {

    Order makeOrder(String login, String departureStreet, String
            destinationStreet,
                    String type) throws NoFreeCarWithSuchTypeException;

    void confirmOrder(Order order);

    void cancelOrder(Order order);

    Optional<List<Order>> getAllUserOrders(String login);

}
