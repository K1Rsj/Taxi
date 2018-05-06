package ua.finalproject.controller.util;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLIntegrityConstraintViolationException;

public class DataValidation {

//    public static String validateClientData(HttpServletRequest request){
//        return userDataValidation(request, "/user_registration_page.jsp");
//    }

    public static boolean userDataValidation(HttpServletRequest request) {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String firstName = request.getParameter("first_name");
        String secondName = request.getParameter("second_name");
        String phoneNumber = request.getParameter("phone_number");

        if (login == null || !login.matches("^[a-z0-9_-]{3,16}$")) {
            request.getSession().setAttribute("wrongInputMessage", "wrong login format");
            return false;
        } else if (password == null || !password.matches("^[a-z0-9_-]{6,18}$")) {
            request.getSession().setAttribute("wrongInputMessage", "wrong password format");
            return false;

        } else if (email == null || !email.matches("^([a-z0-9_.-]+)@([\\da-z.-]+)\\.([a-z.]{2,6})$")) {
            request.getSession().setAttribute("wrongInputMessage", "wrong email format");
            return false;

        } else if (firstName == null || !firstName.matches("^[А-Яа-яЁёЇїІіЄєҐґ]{1,3}[А-Яа-яЁёЇїІіЄєҐґ']{1,17}$")) {
            request.getSession().setAttribute("wrongInputMessage", "wrong first name format");
            return false;

        } else if (secondName == null || !secondName.matches("^[А-Яа-яЁёЇїІіЄєҐґ]{1,3}[А-Яа-яЁёЇїІіЄєҐґ'-]{1,17}$")) {
            request.getSession().setAttribute("wrongInputMessage", "wrong second name format");
            return false;

        } else if (phoneNumber == null || !phoneNumber.matches("^0\\d{9}$")) {
            request.getSession().setAttribute("wrongInputMessage", "wrong phone number format");
            return false;

        } else if (!password.equals(request.getParameter("password2"))) {
            request.getSession().setAttribute("wrongInputMessage", "passwords are not the same ");
            return false;
        }
        else {
            return true;
        }
    }

    public static String loginOrEmailNotUniqueDetermination(SQLIntegrityConstraintViolationException e) {
        if (e.getMessage().contains("login")) {
            return "User with this login is already registered";
        }
        else {
            return "User with this email is already registered";
        }
    }

    public static boolean validationOfLoginAndPassword(HttpServletRequest request, String login, String pass) {
        if (login == null || login.equals("") || pass == null || pass.equals("")) {
            request.getSession().setAttribute("informationMessage", "Login or password is missed");
            return true;
        }
        return false;
    }
}
