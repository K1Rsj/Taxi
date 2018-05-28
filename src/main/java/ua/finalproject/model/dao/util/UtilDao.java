package ua.finalproject.model.dao.util;

import ua.finalproject.model.entities.full.Car;
import ua.finalproject.model.entities.full.User;

public class UtilDao {

    /**
     * Parses user's role from string to enum value
     *
     * @param role user's role
     * @return user role from enum
     */
    public static User.Role parseUserRole(String role) {
        if (role.equalsIgnoreCase(String.valueOf(User.Role.ADMIN))) {
            return User.Role.ADMIN;
        } else {
            return User.Role.USER;
        }
    }

    /**
     * Parses car's state from string to enum value
     *
     * @param state car's state
     * @return car state from enum
     */
    public static Car.State parseCarState(String state) {
        if (state.equalsIgnoreCase(String.valueOf(Car.State.FREE))) {
            return Car.State.FREE;
        } else {
            return Car.State.BUSY;
        }
    }

    /**
     * Parses car's type name from string to id
     *
     * @param type car's type
     * @return id of car's type
     */
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
