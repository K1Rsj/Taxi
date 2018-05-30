package ua.finalproject.model.services.impl;

import ua.finalproject.constants.messages.LogMessages;
import ua.finalproject.model.dao.CarTypeDao;
import ua.finalproject.model.dao.connectionPool.ConnectionPoolHolder;
import ua.finalproject.model.dao.util.UtilDao;
import ua.finalproject.model.entities.full.CarType;
import ua.finalproject.model.services.CarTypeService;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public class CarTypeServiceImpl implements CarTypeService {

    private CarTypeServiceImpl() {
    }

    private static class CarTypeServiceImplHolder {
        static final CarTypeServiceImpl instance = new
                CarTypeServiceImpl();
    }

    public static CarTypeServiceImpl getInstance() {
        return CarTypeServiceImplHolder.instance;
    }

    /**
     * Updates discount of car type
     *
     * @param type     type of car
     * @param discount car type discount
     */
    @Override
    public void updateDiscount(String type, Integer discount) {
        Connection connection = ConnectionPoolHolder.getConnection();
        try (CarTypeDao carTypeDao = daoFactory.createCarTypeDao
                (connection)) {
            Integer typeId = UtilDao.parseTypeString(type);
            carTypeDao.updateDiscount(typeId, discount);
        } catch (Exception e) {
            logger.error(LogMessages
                    .AUTO_CLOSABLE_RESOURCE_ERROR_IN_UPDATE_DISCOUNT, e
                    .getMessage());
        }
    }

    /**
     * Shows all car types
     *
     * @return list of all car types
     */
    @Override
    public Optional<List<CarType>> showAllCarTypes() {
        Connection connection = ConnectionPoolHolder.getConnection();
        try (CarTypeDao carTypeDao = daoFactory.createCarTypeDao
                (connection)) {
            return carTypeDao.findAll();
        } catch (Exception e) {
            logger.error(LogMessages
                    .AUTO_CLOSABLE_RESOURCE_ERROR_IN_SHOW_ALL_CAR_TYPES, e
                    .getMessage());
        }
        return Optional.empty();
    }
}
