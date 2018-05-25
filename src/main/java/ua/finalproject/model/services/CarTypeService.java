package ua.finalproject.model.services;

import ua.finalproject.model.dao.CarTypeDao;
import ua.finalproject.model.dao.util.UtilDao;
import ua.finalproject.model.entities.impl.CarType;

import java.util.List;
import java.util.Optional;

public class CarTypeService {

    private CarTypeDao carTypeDao;

    public CarTypeService(CarTypeDao carTypeDao) {
        this.carTypeDao = carTypeDao;
    }

    /**
     * Updates discount of car type
     * @param type type of car
     * @param discount car type discount
     */
    public void updateDiscount(String type, Integer discount) {
            Integer typeId = UtilDao.parseTypeString(type);
            carTypeDao.updateDiscount(typeId, discount);
    }

    /**
     * Shows all car types
     * @return list of all car types
     */
    public Optional<List<CarType>> showAllCarTypes() {
            return carTypeDao.findAll();
    }
}
