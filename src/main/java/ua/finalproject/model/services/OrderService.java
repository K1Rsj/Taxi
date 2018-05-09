package ua.finalproject.model.services;

import ua.finalproject.dao.CarDao;
import ua.finalproject.dao.OrderDao;
import ua.finalproject.dao.UserDao;
import ua.finalproject.dao.connectionPool.ConnectionPoolHolder;
import ua.finalproject.dao.factory.DaoFactory;
import ua.finalproject.model.entities.impl.Car;
import ua.finalproject.model.entities.impl.Order;
import ua.finalproject.model.entities.impl.User;
import ua.finalproject.model.exceptions.NoFreeCarWithSuchTypeException;
import ua.finalproject.model.util.OrderPriceGenerator;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class OrderService {
    public Order makeOrder(Order order, String login) throws Exception {
        Connection connection = ConnectionPoolHolder.getConnection();
        try (CarDao carDao = DaoFactory.getInstance().createCarDao(connection);
             OrderDao orderDao = DaoFactory.getInstance().createOrderDao(connection);
             UserDao userDao = DaoFactory.getInstance().createUserDao(connection)) {
            connection.setAutoCommit(false);
            List<Car> cars = carDao.getFreeCarByType(order.getType());
            if (cars.isEmpty()) {
                throw new NoFreeCarWithSuchTypeException();
            }
            carDao.updateCarState(cars.get(0).getId(), Car.State.BUSY.toString());
            Optional<User> user = userDao.findByLogin(login);
            Long moneySpent = user.get().getMoneySpent();

            //TODO getType from car_type

            Long orderPrice = OrderPriceGenerator.getOrderPrice(moneySpent, order);
            moneySpent = moneySpent + orderPrice;

            userDao.updateMoneySpent(user.get().getId(), moneySpent);
            orderDao.create(order);
            connection.commit();
            return order;
        } catch (SQLException e) {
            connection.rollback();
            return null;
        }
    }
}
