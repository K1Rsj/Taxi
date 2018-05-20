package ua.finalproject.model.dao.util;

import ua.finalproject.model.entities.impl.Car;
import ua.finalproject.model.entities.impl.User;

public class UtilDao {
    public static User.Role parseUserRole(String role) {
        if (role.equalsIgnoreCase(String.valueOf(User.Role.ADMIN))) {
            return User.Role.ADMIN;
        } else {
            return User.Role.USER;
        }
    }

    public static Car.State parseCarState(String state) {
        if (state.equalsIgnoreCase(String.valueOf(Car.State.FREE))) {
            return Car.State.FREE;
        } else {
            return Car.State.BUSY;
        }
    }

    public static Integer parseTypeString(String type) {
        if (type.equals("standard")) {
            return 1;
        } else if (type.equals("comfort")) {
            return 2;
        } else if (type.equals("minibus")) {
            return 3;
        } else {
            return 4;
        }
    }
}
