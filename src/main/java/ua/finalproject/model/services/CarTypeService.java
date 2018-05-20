package ua.finalproject.model.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.finalproject.constants.messages.LogMessages;
import ua.finalproject.model.dao.CarTypeDao;
import ua.finalproject.model.dao.connectionPool.ConnectionPoolHolder;
import ua.finalproject.model.dao.factory.DaoFactory;
import ua.finalproject.model.dao.util.UtilDao;
import ua.finalproject.model.entities.impl.CarType;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public class CarTypeService {

    private static final Logger logger = LogManager.getLogger(CarTypeService.class);


    public void updateDiscount(String type, Integer discount) {
        Connection connection = ConnectionPoolHolder.getConnection();
        try (CarTypeDao carType = DaoFactory.getInstance().createCarTypeDao(connection)) {
            Integer typeId = UtilDao.parseTypeString(type);
            carType.updateDiscount(typeId, discount);
        } catch (Exception e) {
            logger.error(LogMessages.AUTO_CLOSABLE_RESOURCE_ERROR_IN_UPDATE_DISCOUNT, e.getMessage());
        }
    }

    public Optional<List<CarType>> showAllCarTypes() {
        Connection connection = ConnectionPoolHolder.getConnection();
        try (CarTypeDao carTypeDao = DaoFactory.getInstance().createCarTypeDao(connection)) {
            return carTypeDao.findAll();
        } catch (Exception e) {
            logger.error(LogMessages.AUTO_CLOSABLE_RESOURCE_ERROR_IN_SHOW_ALL_CAR_TYPES, e.getMessage());
        }
        return Optional.empty();
    }
}
