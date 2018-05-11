package ua.finalproject.model.services;

import ua.finalproject.dao.CarDao;
import ua.finalproject.dao.CarTypeDao;
import ua.finalproject.dao.OrderDao;
import ua.finalproject.dao.UserDao;
import ua.finalproject.dao.connectionPool.ConnectionPoolHolder;
import ua.finalproject.dao.factory.DaoFactory;
import ua.finalproject.dao.util.UtilDao;
import ua.finalproject.model.entities.impl.Car;
import ua.finalproject.model.entities.impl.CarType;
import ua.finalproject.model.entities.impl.Order;
import ua.finalproject.model.entities.impl.User;
import ua.finalproject.model.exceptions.NoFreeCarWithSuchTypeException;
import ua.finalproject.model.util.OrderPriceGenerator;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;

public class OrderService {
    public Order makeOrder(String login, String departureStreet, String destinationStreet, String type) throws Exception {
        Connection connection = ConnectionPoolHolder.getConnection();
        try (CarDao carDao = DaoFactory.getInstance().createCarDao(connection);
             UserDao userDao = DaoFactory.getInstance().createUserDao(connection);
             CarTypeDao carTypeDao = DaoFactory.getInstance().createCarTypeDao(connection)) {
            Integer typeId = UtilDao.parseTypeString(type);
            connection.setAutoCommit(false);
            Optional<Car> car = carDao.getFreeCarByTypeId(typeId);
            if (!car.isPresent()) {
                throw new NoFreeCarWithSuchTypeException();
            }
            carDao.updateCarState(car.get().getId(), Car.State.BUSY.toString().toLowerCase());
            User user = userDao.findByLogin(login).get();
            Long moneySpent = user.getMoneySpent();
            CarType carType = carTypeDao.findById(typeId).get();
            Long orderPrice = OrderPriceGenerator.getOrderPrice(moneySpent, departureStreet, destinationStreet, carType);
            moneySpent = moneySpent + orderPrice;
            userDao.updateMoneySpent(user.getId(), moneySpent);
            Order order = new Order.OrderBuilder()
                    .setDepartureStreet(departureStreet)
                    .setDestinationStreet(destinationStreet)
                    .setCar(car.get())
                    .setUser(user)
                    .setCarType(carType)
                    .setPrice(orderPrice)
                    .build();
            connection.commit();
            return order;
        } catch (SQLException e) {
            connection.rollback();
            return null;
        }
    }
    public void confirmOrder(Order order) throws Exception {
        Connection connection = ConnectionPoolHolder.getConnection();
        try (CarDao carDao = DaoFactory.getInstance().createCarDao(connection);
             OrderDao orderDao = DaoFactory.getInstance().createOrderDao(connection)) {
            orderDao.create(order);
            carDao.updateCarState(order.getCar().getId(), Car.State.FREE.toString().toLowerCase());
        }
    }

    public void cancelOrder(Order order) throws Exception {
        Connection connection = ConnectionPoolHolder.getConnection();
        try (CarDao carDao = DaoFactory.getInstance().createCarDao(connection)) {
            carDao.updateCarState(order.getCar().getId(), Car.State.FREE.toString().toLowerCase());
        }
    }


}
