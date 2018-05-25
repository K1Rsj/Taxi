package ua.finalproject.model.dao;

import ua.finalproject.model.entities.impl.CarType;

public interface CarTypeDao extends Dao<CarType, Integer> {

    /**
     * Updates car type's discount
     * @param id of car type
     * @param discount of car type
     */
    void updateDiscount(Integer id, Integer discount);
}
