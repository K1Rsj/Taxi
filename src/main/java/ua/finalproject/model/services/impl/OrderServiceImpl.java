package ua.finalproject.model.services.impl;

import org.hibernate.Session;
import ua.finalproject.model.dao.CarDao;
import ua.finalproject.model.dao.CarTypeDao;
import ua.finalproject.model.dao.OrderDao;
import ua.finalproject.model.dao.UserDao;
import ua.finalproject.model.dao.hibernate.factory.AbstractHibernateDaoFactory;
import ua.finalproject.model.dao.hibernate.factory.HibernateDaoFactory;
import ua.finalproject.model.dao.hibernate.session.HibernateSession;
import ua.finalproject.model.dao.util.UtilDao;
import ua.finalproject.model.entities.full.Car;
import ua.finalproject.model.entities.full.CarType;
import ua.finalproject.model.entities.full.Order;
import ua.finalproject.model.entities.full.User;
import ua.finalproject.model.exceptions.NoFreeCarWithSuchTypeException;
import ua.finalproject.model.services.OrderService;
import ua.finalproject.model.util.OrderPriceGenerator;

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

    @Override
    public Order makeOrder(String login, String departureStreet, String destinationStreet, String type)
            throws NoFreeCarWithSuchTypeException {
        Session session = HibernateSession.getSession();
        try (UserDao userDao = AbstractHibernateDaoFactory.getInstance().createHibernateUserDao(session);
             CarDao carDao = AbstractHibernateDaoFactory.getInstance().createHibernateCarDao(session);
             CarTypeDao carTypeDao = AbstractHibernateDaoFactory.getInstance().createHibernateCarTypeDao(session)) {
            session.beginTransaction();
            Optional<Car> car = carDao.getFreeCarByTypeId(UtilDao.parseTypeString(type));
            if (car.isPresent()) {
                carDao.updateCarState(car.get().getId(), Car.State.BUSY.toString());
                User user = userDao.findByLogin(login).get();
                Long moneySpent = user.getMoneySpent();
                CarType carType = carTypeDao.findById(UtilDao.parseTypeString(type)).get();
                Long orderPrice = OrderPriceGenerator
                        .getOrderPrice(moneySpent, departureStreet, destinationStreet, carType);
                session.getTransaction().commit();
                return new Order.OrderBuilder()
                        .setDepartureStreet(departureStreet)
                        .setDestinationStreet(destinationStreet)
                        .setCar(car.get())
                        .setUser(user)
                        .setCarType(carType)
                        .setPrice(orderPrice)
                        .build();
            } else {
                throw new NoFreeCarWithSuchTypeException();
            }

        } catch (NoFreeCarWithSuchTypeException e) {
            throw new NoFreeCarWithSuchTypeException();
        }
        catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
    @Override
    public void confirmOrder(Order order) {
        Session session = HibernateSession.getSession();
        try (OrderDao orderDao = AbstractHibernateDaoFactory.getInstance().createHibernateOrderDao(session);
             UserDao userDao = AbstractHibernateDaoFactory.getInstance().createHibernateUserDao(session);
             CarDao carDao = AbstractHibernateDaoFactory.getInstance().createHibernateCarDao(session)) {
            session.beginTransaction();
            orderDao.create(order);
            Long moneySpent = order.getUser().getMoneySpent();
            moneySpent = moneySpent + order.getPrice();
            userDao.updateMoneySpent(order.getUser().getId(), moneySpent);
            carDao.updateCarState(order.getCar().getId(), Car.State.FREE.toString());
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void cancelOrder(Order order) {
        Session session = HibernateSession.getSession();
        try(CarDao carDao = AbstractHibernateDaoFactory.getInstance().createHibernateCarDao(session)) {
            session.beginTransaction();
            carDao.updateCarState(order.getCar().getId(), Car.State.FREE.toString());
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<List<Order>> getAllUserOrders(String login) {
        Session session = HibernateSession.getSession();
        try(OrderDao orderDao = HibernateDaoFactory.getInstance().createHibernateOrderDao(session);
             UserDao userDao = HibernateDaoFactory.getInstance().createHibernateUserDao(session)){
            return orderDao.findOrdersByUserId(userDao.findByLogin(login).get().getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    //    /**
//     * Makes order for client. Search free car by type and set it state
//     * to busy.
//     *
//     * @param login             user's login
//     * @param departureStreet   departure street
//     * @param destinationStreet destination street
//     * @param type              car's type
//     * @return user's order
//     * @throws NoFreeCarWithSuchTypeException if there is no free car
//     *                                        with some type
//     */
//    @Override
//    public Order makeOrder(String login, String departureStreet, String
//            destinationStreet,
//                           String type) throws
//            NoFreeCarWithSuchTypeException {
//        Connection connection = ConnectionPoolHolder.getConnection();
//        try (CarDao carDao = ABSTRACT_JDBC_DAO_FACTORY.createCarDao(connection);
//             UserDao userDao = ABSTRACT_JDBC_DAO_FACTORY.createUserDao(connection);
//             CarTypeDao carTypeDao = ABSTRACT_JDBC_DAO_FACTORY.createCarTypeDao
//                     (connection)) {
//            Integer typeId = UtilDao.parseTypeString(type);
//            connection.setAutoCommit(false);
//            Optional<Car> car = carDao.getFreeCarByTypeId(typeId);
//            if (car.isPresent()) {
//                carDao.updateCarState(car.get().getId(), Car.State.BUSY
//                        .toString());
//                User user = userDao.findByLogin(login).get();
//                Long moneySpent = user.getMoneySpent();
//                CarType carType = carTypeDao.findById(typeId).get();
//                Long orderPrice = OrderPriceGenerator.getOrderPrice
//                        (moneySpent,
//                                departureStreet, destinationStreet,
//                                carType);
//                Order order = new Order.OrderBuilder()
//                        .setDepartureStreet(departureStreet)
//                        .setDestinationStreet(destinationStreet)
//                        .setCar(car.get())
//                        .setUser(user)
//                        .setCarType(carType)
//                        .setPrice(orderPrice)
//                        .build();
//                connection.commit();
//                return order;
//            } else {
//                throw new NoFreeCarWithSuchTypeException();
//            }
//        } catch (NoFreeCarWithSuchTypeException e) {
//            throw new NoFreeCarWithSuchTypeException();
//        } catch (SQLException e) {
//            logger.error(LogMessages.MAKE_ORDER_ERROR, e.getMessage());
//            SQLExceptionRollbackErrorHandle(connection);
//            throw new RuntimeException(e);
//        } catch (Exception e) {
//            logger.error(LogMessages
//                    .AUTO_CLOSABLE_RESOURCE_ERROR_IN_MAKE_ORDER, e
//                    .getMessage());
//            throw new RuntimeException(e);
//        }
//    }
//
//    /**
//     * Confirms user's order. Adds order to DB and than update user's
//     * spent money and change
//     * car state from busy to free.
//     *
//     * @param order user's order
//     */
//    @Override
//    public void confirmOrder(Order order) {
//        Connection connection = ConnectionPoolHolder.getConnection();
//        try (CarDao carDao = ABSTRACT_JDBC_DAO_FACTORY.createCarDao(connection);
//             OrderDao orderDao = ABSTRACT_JDBC_DAO_FACTORY.createOrderDao(connection);
//             UserDao userDao = ABSTRACT_JDBC_DAO_FACTORY.createUserDao(connection)) {
//            connection.setAutoCommit(false);
//            orderDao.create(order);
//            Long moneySpent = order.getUser().getMoneySpent();
//            moneySpent = moneySpent + order.getPrice();
//            userDao.updateMoneySpent(order.getUser().getId(), moneySpent);
//            carDao.updateCarState(order.getCar().getId(), Car.State.FREE
//                    .toString());
//            connection.commit();
//        } catch (SQLException e) {
//            logger.error(LogMessages.CONFIRM_ORDER_ERROR, e.getMessage());
//            SQLExceptionRollbackErrorHandle(connection);
//            throw new RuntimeException(e);
//        } catch (Exception e) {
//            logger.error(LogMessages
//                    .AUTO_CLOSABLE_RESOURCE_ERROR_IN_CONFIRM_ORDER, e
//                    .getMessage());
//        }
//    }
//
//    /**
//     * Cancels user's order. Changes car state from busy to free.
//     *
//     * @param order user's order
//     */
//    @Override
//    public void cancelOrder(Order order) {
//        Connection connection = ConnectionPoolHolder.getConnection();
//        try (CarDao carDao = ABSTRACT_JDBC_DAO_FACTORY.createCarDao(connection)) {
//            carDao.updateCarState(order.getCar().getId(), Car.State.FREE
//                    .toString());
//        } catch (Exception e) {
//            logger.error(LogMessages
//                    .AUTO_CLOSABLE_RESOURCE_ERROR_IN_CANCEL_ORDER, e
//                    .getMessage());
//        }
//    }
//
//    /**
//     * Finds all order by user
//     *
//     * @param login user's login
//     * @return list of all user's orders
//     */
//    @Override
//    public Optional<List<Order>> getAllUserOrders(String login) {
//        Connection connection = ConnectionPoolHolder.getConnection();
//        try (OrderDao orderDao = ABSTRACT_JDBC_DAO_FACTORY.createOrderDao(connection);
//             UserDao userDao = ABSTRACT_JDBC_DAO_FACTORY.createUserDao(connection)) {
//            connection.setAutoCommit(false);
//            User user = userDao.findByLogin(login).get();
//            Optional<List<Order>> orders = orderDao
//                    .findOrdersByUserId(user.getId());
//            connection.commit();
//            return orders;
//        } catch (Exception e) {
//            logger.error(LogMessages
//                    .AUTO_CLOSABLE_RESOURCE_ERROR_IN_GET_ALL_USERS_ORDERS,
//                    e.getMessage());
//        }
//        return Optional.empty();
//    }
//
//    /**
//     * Handles rollback error
//     *
//     * @param connection connection
//     */
//    private void SQLExceptionRollbackErrorHandle(Connection connection) {
//        try {
//            connection.rollback();
//        } catch (SQLException e1) {
//            logger.error(LogMessages.ODDER_CONNECTION_ROLLBACK_ERROR,
//                    e1.getMessage());
//            throw new RuntimeException(e1);
//        }
//    }
}
