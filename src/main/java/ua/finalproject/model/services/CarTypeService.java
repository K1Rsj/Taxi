package ua.finalproject.model.services;

import ua.finalproject.dao.CarTypeDao;
import ua.finalproject.dao.connectionPool.ConnectionPoolHolder;
import ua.finalproject.dao.factory.DaoFactory;
import ua.finalproject.dao.util.UtilDao;

import java.sql.Connection;

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
}
