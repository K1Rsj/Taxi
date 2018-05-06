package ua.finalproject.dao.util;

import ua.finalproject.model.entities.impl.Car;
import ua.finalproject.model.entities.impl.User;

public class UtilDao {
    public static User.Role parseUserRole(String role) {
        if (role.equalsIgnoreCase(String.valueOf(User.Role.ADMIN))) {
            return User.Role.ADMIN;
        } else if (role.equalsIgnoreCase(String.valueOf(User.Role.USER))) {
            return User.Role.USER;
        } else {
            throw new RuntimeException("Incorrect user role");
        }
    }

    public static Car.State parseCarState(String state) {
        if (state.equalsIgnoreCase(String.valueOf(Car.State.FREE))) {
            return Car.State.FREE;
        } else if (state.equalsIgnoreCase(String.valueOf(Car.State.BUSY))) {
            return Car.State.BUSY;
        } else {
            throw new RuntimeException("Incorrect car state");
        }
    }

    public static Car.Type parseCarType(String type) {
        if (type.equalsIgnoreCase(String.valueOf(Car.Type.STANDARD))) {
            return Car.Type.STANDARD;
        } else if (type.equalsIgnoreCase(String.valueOf(Car.Type.COMFORT))) {
            return Car.Type.COMFORT;
        } else if (type.equalsIgnoreCase(String.valueOf(Car.Type.MINIBUS))) {
            return Car.Type.MINIBUS;
        } else if (type.equalsIgnoreCase(String.valueOf(Car.Type.PREMIUM))) {
            return Car.Type.PREMIUM;
        } else {
            throw new RuntimeException("Incorrect car type");
        }
    }
}
