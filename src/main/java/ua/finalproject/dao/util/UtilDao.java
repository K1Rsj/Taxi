package ua.finalproject.dao.util;

import ua.finalproject.model.entities.impl.User;

public class UtilDao {
    public static User.Role parseUserRole(String role){
        if(role.equalsIgnoreCase(String.valueOf(User.Role.ADMIN))){
            return User.Role.ADMIN;
        }else if(role.equalsIgnoreCase(String.valueOf(User.Role.USER))){
            return User.Role.USER;
        }else {
            throw new RuntimeException("Incorrect user role");
        }
    }
}
