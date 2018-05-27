package ua.finalproject.model.entities.lazy;

import ua.finalproject.constants.db.TableColumnNames;
import ua.finalproject.constants.db.TableNames;
import ua.finalproject.constants.messages.LogMessages;
import ua.finalproject.model.dao.CarTypeDao;
import ua.finalproject.model.dao.connectionPool.ConnectionPoolHolder;
import ua.finalproject.model.entities.Lazy;
import ua.finalproject.model.entities.full.Car;
import ua.finalproject.model.entities.full.CarType;

import java.sql.Connection;

public class CarLazy extends Car implements Lazy {

    @Override
    public CarType getCarType() {
        if (super.getCarType() == null) {
            Connection connection = ConnectionPoolHolder.getConnection();
            try (CarTypeDao carTypeDao = daoFactory.createCarTypeDao(connection)) {
                Integer carTypeId = carTypeDao.findForeignKeyInTable(TableNames.CARS, super.getId().toString(),
                        TableColumnNames.CAR_TYPE_ID);
                return carTypeDao.findById(carTypeId).get();
            } catch (Exception e) {
                logger.error(LogMessages.AUTO_CLOSABLE_RESOURCE_ERROR_IN_GET_CAR_TYPE, e.getMessage());
            }
        }
        return null;
    }
}
