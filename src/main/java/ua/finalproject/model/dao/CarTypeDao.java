package ua.finalproject.model.dao;

import ua.finalproject.model.entities.impl.CarType;

public interface CarTypeDao extends Dao<CarType, Integer> {
    void updateDiscount(Integer id, Integer discount);
}
