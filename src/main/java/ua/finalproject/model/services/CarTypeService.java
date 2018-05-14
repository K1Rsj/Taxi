package ua.finalproject.model.services;

import ua.finalproject.dao.CarDao;
import ua.finalproject.dao.CarTypeDao;
import ua.finalproject.dao.connectionPool.ConnectionPoolHolder;
import ua.finalproject.dao.factory.DaoFactory;
import ua.finalproject.dao.util.UtilDao;
import ua.finalproject.model.entities.impl.CarType;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public class CarTypeService {
    public void updateDiscount(String type, Integer discount) {
        Connection connection = ConnectionPoolHolder.getConnection();
        try (CarTypeDao carType = DaoFactory.getInstance().createCarTypeDao(connection)) {
            Integer typeId = UtilDao.parseTypeString(type);
            carType.updateDiscount(typeId, discount);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Optional<List<CarType>> showAllCarTypes() {
        Connection connection = ConnectionPoolHolder.getConnection();
        try (CarTypeDao carTypeDao = DaoFactory.getInstance().createCarTypeDao(connection)) {
            return carTypeDao.findAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
}