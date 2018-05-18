package ua.finalproject.model.dao.util;

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

    public static Integer parseTypeString(String type) {
        if (type.equals("standard")) {
            return 1;
        } else if (type.equals("comfort")) {
            return 2;
        } else if (type.equals("minibus")) {
            return 3;
        } else if (type.equals("premium")) {
            return 4;
        } else {
            throw new RuntimeException("Incorrect car type");

        }
    }
}
