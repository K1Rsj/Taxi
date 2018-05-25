package ua.finalproject.model.dao.util;

import org.junit.Test;
import ua.finalproject.model.entities.impl.Car;
import ua.finalproject.model.entities.impl.User;

import static org.junit.Assert.*;

public class UtilDaoTest {

    @Test
    public void parseUserRole() {
        String role = "admin";
        User.Role actual = UtilDao.parseUserRole(role);
        assertEquals(User.Role.ADMIN, actual);
    }

    @Test
    public void parseCarState() {
        String state = "free";
        Car.State actual = UtilDao.parseCarState(state);
        assertEquals(Car.State.FREE, actual);
    }

    @Test
    public void parseTypeString() {
        String type = "minibus";
        Integer actual = UtilDao.parseTypeString(type);
        assertEquals(Integer.valueOf(3), actual);
    }
}