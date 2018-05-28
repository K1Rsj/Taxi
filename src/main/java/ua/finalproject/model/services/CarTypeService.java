package ua.finalproject.model.services;

import ua.finalproject.model.entities.full.CarType;

import java.util.List;
import java.util.Optional;

public interface CarTypeService extends Service {

    void updateDiscount(String type, Integer discount);
    Optional<List<CarType>> showAllCarTypes();
}
