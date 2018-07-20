package ua.finalproject.model.entities.lazy;

import ua.finalproject.constants.db.TableColumnNames;
import ua.finalproject.constants.db.TableNames;
import ua.finalproject.constants.messages.LogMessages;
import ua.finalproject.model.dao.CarDao;
import ua.finalproject.model.dao.CarTypeDao;
import ua.finalproject.model.dao.UserDao;
import ua.finalproject.model.dao.connection.pool.ConnectionPoolHolder;
import ua.finalproject.model.entities.Lazy;
import ua.finalproject.model.entities.full.Car;
import ua.finalproject.model.entities.full.CarType;
import ua.finalproject.model.entities.full.Order;
import ua.finalproject.model.entities.full.User;

import java.sql.Connection;

public class OrderLazy extends Order implements Lazy {

    @Override
    public User getUser() {
        if (super.getUser() == null) {
            Connection connection = ConnectionPoolHolder.getConnection();
            try (UserDao userDao = ABSTRACT_JDBC_DAO_FACTORY.createUserDao(connection)) {
                Integer userId = userDao.findForeignKeyInTable
                        (TableNames.ORDERS, super
                                        .getId().toString(),
                                TableColumnNames.USERS_ID);
                return userDao.findById(userId).get();
            } catch (Exception e) {
                logger.error(LogMessages
                        .AUTO_CLOSABLE_RESOURCE_ERROR_IN_GET_USER, e
                        .getMessage());
            }
        }
        return null;
    }

    @Override
    public Car getCar() {
        if (super.getCar() == null) {
            Connection connection = ConnectionPoolHolder.getConnection();
            try (CarDao carDao = ABSTRACT_JDBC_DAO_FACTORY.createCarDao(connection)) {
                Integer carId = carDao.findForeignKeyInTable(TableNames
                                .ORDERS, super.getId
                                ().toString(),
                        TableColumnNames.CARS_ID);
                return carDao.findById(carId).get();
            } catch (Exception e) {
                logger.error(LogMessages
                        .AUTO_CLOSABLE_RESOURCE_ERROR_IN_GET_CAR, e
                        .getMessage());
            }
        }
        return null;
    }

    @Override
    public CarType getCarType() {
        if (super.getCarType() == null) {
            Connection connection = ConnectionPoolHolder.getConnection();
            try (CarTypeDao carTypeDao = ABSTRACT_JDBC_DAO_FACTORY.createCarTypeDao
                    (connection)) {
                Integer carTypeId = carTypeDao.findForeignKeyInTable
                        (TableNames.ORDERS,
                                super.getId().toString(),
                                TableColumnNames.CAR_TYPE_ID);
                return carTypeDao.findById(carTypeId).get();
            } catch (Exception e) {
                logger.error(LogMessages
                        .AUTO_CLOSABLE_RESOURCE_ERROR_IN_GET_CAR_TYPE, e
                        .getMessage());
            }
        }
        return null;
    }
}
