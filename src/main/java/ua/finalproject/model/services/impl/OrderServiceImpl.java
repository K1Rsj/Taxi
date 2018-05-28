package ua.finalproject.model.services.impl;

import ua.finalproject.constants.messages.LogMessages;
import ua.finalproject.model.dao.CarDao;
import ua.finalproject.model.dao.CarTypeDao;
import ua.finalproject.model.dao.OrderDao;
import ua.finalproject.model.dao.UserDao;
import ua.finalproject.model.dao.connectionPool.ConnectionPoolHolder;
import ua.finalproject.model.dao.util.UtilDao;
import ua.finalproject.model.entities.full.Car;
import ua.finalproject.model.entities.full.CarType;
import ua.finalproject.model.entities.full.Order;
import ua.finalproject.model.entities.full.User;
import ua.finalproject.model.exceptions.NoFreeCarWithSuchTypeException;
import ua.finalproject.model.services.OrderService;
import ua.finalproject.model.util.OrderPriceGenerator;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class OrderServiceImpl implements OrderService {

    private OrderServiceImpl() {
    }

    private static class OrderServiceImplHolder {
        static final OrderServiceImpl instance = new OrderServiceImpl();
    }

    public static OrderServiceImpl getInstance() {
        return OrderServiceImplHolder.instance;
    }

    /**
     * Makes order for client. Search free car by type and set it state to busy.
     *
     * @param login             user's login
     * @param departureStreet   departure street
     * @param destinationStreet destination street
     * @param type              car's type
     * @return user's order
     * @throws NoFreeCarWithSuchTypeException if there is no free car with some type
     */
    @Override
    public Order makeOrder(String login, String departureStreet, String destinationStreet,
                           String type) throws NoFreeCarWithSuchTypeException {
        Connection connection = ConnectionPoolHolder.getConnection();
        try (CarDao carDao = daoFactory.createCarDao(connection);
             UserDao userDao = daoFactory.createUserDao(connection);
             CarTypeDao carTypeDao = daoFactory.createCarTypeDao(connection)) {
            Integer typeId = UtilDao.parseTypeString(type);
            connection.setAutoCommit(false);
            Optional<Car> car = carDao.getFreeCarByTypeId(typeId);
            if (car.isPresent()) {
                carDao.updateCarState(car.get().getId(), Car.State.BUSY.toString()
                        .toLowerCase());
                User user = userDao.findByLogin(login).get();
                Long moneySpent = user.getMoneySpent();
                CarType carType = carTypeDao.findById(typeId).get();
                Long orderPrice = OrderPriceGenerator.getOrderPrice(moneySpent,
                        departureStreet, destinationStreet, carType);
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
            } else {
                throw new NoFreeCarWithSuchTypeException();
            }
        } catch (NoFreeCarWithSuchTypeException e) {
            throw new NoFreeCarWithSuchTypeException();
        } catch (SQLException e) {
            logger.error(LogMessages.MAKE_ORDER_ERROR, e.getMessage());
            SQLExceptionRollbackErrorHandle(connection);
            throw new RuntimeException(e);
        } catch (Exception e) {
            logger.error(LogMessages.AUTO_CLOSABLE_RESOURCE_ERROR_IN_MAKE_ORDER, e
                    .getMessage());
            throw new RuntimeException(e);
        }
    }

    /**
     * Confirms user's order. Adds order to DB and than update user's spent money and change
     * car state from busy to free.
     *
     * @param order user's order
     */
    @Override
    public void confirmOrder(Order order) {
        Connection connection = ConnectionPoolHolder.getConnection();
        try (CarDao carDao = daoFactory.createCarDao(connection);
             OrderDao orderDao = daoFactory.createOrderDao(connection);
             UserDao userDao = daoFactory.createUserDao(connection)) {
            connection.setAutoCommit(false);
            orderDao.create(order);
            Long moneySpent = order.getUser().getMoneySpent();
            moneySpent = moneySpent + order.getPrice();
            userDao.updateMoneySpent(order.getUser().getId(), moneySpent);
            carDao.updateCarState(order.getCar().getId(), Car.State.FREE.toString()
                    .toLowerCase());
            connection.commit();
        } catch (SQLException e) {
            logger.error(LogMessages.CONFIRM_ORDER_ERROR, e.getMessage());
            SQLExceptionRollbackErrorHandle(connection);
            throw new RuntimeException(e);
        } catch (Exception e) {
            logger.error(LogMessages.AUTO_CLOSABLE_RESOURCE_ERROR_IN_CONFIRM_ORDER, e
                    .getMessage());
        }
    }

    /**
     * Cancels user's order. Changes car state from busy to free.
     *
     * @param order user's order
     */
    @Override
    public void cancelOrder(Order order) {
        Connection connection = ConnectionPoolHolder.getConnection();
        try (CarDao carDao = daoFactory.createCarDao(connection)) {
            carDao.updateCarState(order.getCar().getId(), Car.State.FREE.toString()
                    .toLowerCase());
        } catch (Exception e) {
            logger.error(LogMessages.AUTO_CLOSABLE_RESOURCE_ERROR_IN_CANCEL_ORDER, e
                    .getMessage());
        }
    }

    /**
     * Finds all order by user
     *
     * @param login user's login
     * @return list of all user's orders
     */
    @Override
    public Optional<List<Order>> getAllUserOrders(String login) {
        Connection connection = ConnectionPoolHolder.getConnection();
        try (OrderDao orderDao = daoFactory.createOrderDao(connection)) {
            return orderDao.findOrdersByUserLogin(login);
        } catch (Exception e) {
            logger.error(LogMessages.AUTO_CLOSABLE_RESOURCE_ERROR_IN_GET_ALL_USERS_ORDERS, e
                    .getMessage());
        }
        return Optional.empty();
    }

    /**
     * Handles rollback error
     *
     * @param connection connection
     */
    private void SQLExceptionRollbackErrorHandle(Connection connection) {
        try {
            connection.rollback();
        } catch (SQLException e1) {
            logger.error(LogMessages.ODDER_CONNECTION_ROLLBACK_ERROR, e1.getMessage());
            throw new RuntimeException(e1);
        }
    }
}
