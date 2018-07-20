package ua.finalproject.model.dao.hibernate.impl;

import org.hibernate.Session;
import ua.finalproject.constants.db.DbHQLQueries;
import ua.finalproject.model.dao.CarDao;
import ua.finalproject.model.entities.full.Car;

import java.util.Optional;

public class HibernateCarDaoImpl extends AbstractHibernateDao<Car> implements CarDao {

    public HibernateCarDaoImpl(Session session, String entityName) {
        super(session, entityName);
    }

    @Override
    public Optional<Car> getFreeCarByTypeId(Integer typeId) {
        return Optional.of(session.createQuery(DbHQLQueries.FIND_FREE_CAR_BY_TYPE_ID, Car.class)
                .setParameter("carTypeId", typeId).setMaxResults(1).getSingleResult());
    }

    @Override
    public void updateCarState(Integer carId, String carState) {
        session.createQuery(DbHQLQueries.UPDATE_CAR_STATE_BY_ID).setParameter("state", Car.State.valueOf(carState))
                .setParameter("id", carId).executeUpdate();
    }
}
