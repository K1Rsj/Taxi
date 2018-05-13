package ua.finalproject.controller.util;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLIntegrityConstraintViolationException;

public class DataValidation {

    public static boolean userDataValidation(HttpServletRequest request) {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String firstName = request.getParameter("first_name");
        String secondName = request.getParameter("second_name");
        String phoneNumber = request.getParameter("phone_number");

        if (login == null || !login.matches("^[a-z0-9_-]{3,16}$")) {
            request.setAttribute("wrongInputMessage", "Wrong login format");
            return false;
        } else if (password == null || !password.matches("^[a-z0-9_-]{6,18}$")) {
            request.setAttribute("wrongInputMessage", "Wrong password format");
            return false;

        } else if (email == null || !email.matches("^([a-z0-9_.-]+)@([\\da-z.-]+)\\.([a-z.]{2,6})$")) {
            request.setAttribute("wrongInputMessage", "Wrong email format");
            return false;

        } else if (firstName == null || !firstName.matches("^[А-Яа-яЁёЇїІіЄєҐґ]{1,3}[А-Яа-яЁёЇїІіЄєҐґ']{1,17}$")) {
            request.setAttribute("wrongInputMessage", "Wrong first name format");
            return false;

        } else if (secondName == null || !secondName.matches("^[А-Яа-яЁёЇїІіЄєҐґ]{1,3}[А-Яа-яЁёЇїІіЄєҐґ'-]{1,17}$")) {
            request.setAttribute("wrongInputMessage", "Wrong second name format");
            return false;

        } else if (phoneNumber == null || !phoneNumber.matches("^0\\d{9}$")) {
            request.setAttribute("wrongInputMessage", "Wrong phone number format");
            return false;

        } else if (!password.equals(request.getParameter("password2"))) {
            request.setAttribute("wrongInputMessage", "Passwords are not the same ");
            return false;
        } else {
            return true;
        }
    }

    public static String loginOrEmailNotUniqueDetermination(SQLIntegrityConstraintViolationException e) {
        if (e.getMessage().contains("login")) {
            return "User with this login is already registered";
        } else {
            return "User with this email is already registered";
        }
    }

    public static boolean validationOfLoginAndPassword(String login, String pass) {
        return login == null || login.equals("") || pass == null || pass.equals("");
    }

    public static boolean orderDataValidation(String departureStreet, String destinationStreet) {
        if (departureStreet == null || !departureStreet.matches("^[А-Яа-яЁёЇїІіЄєҐґ]{1,3}[А-Яа-яЁёЇїІіЄєҐґ'-]{1,17} [\\d]{1,3}$")) {
            return false;
        } else
            return destinationStreet != null && destinationStreet.matches("^[А-Яа-яЁёЇїІіЄєҐґ]{1,3}[А-Яа-яЁёЇїІіЄєҐґ'-]{1,17} [\\d]{1,3}$");
    }

    public static boolean checkDiscountAmount(Integer discount) {
        return discount < 16 && discount > 0;
    }

    public static boolean carDataValidation(HttpServletRequest request) {
        String model = request.getParameter("model");
        String number = request.getParameter("number");
        String driver = request.getParameter("driver");
        if (model == null || !model.matches("^\\S+ ?\\S+$")) {
            request.setAttribute("informationMessage", "Wrong model format");
            return false;
        } else if (number == null || !number.matches("^[А-ЯЁЇІЄҐ]{2}[\\d]{4}[А-ЯЁЇІЄҐ]{2}$")) {
            request.setAttribute("informationMessage", "Wrong number format");
            return false;
        } else if (driver == null || !driver.matches("^[А-Яа-яЁёЇїІіЄєҐґ$]+ [А-Яа-яЁёЇїІіЄєҐґ'-]+")) {
            request.setAttribute("informationMessage", "Wrong driver name format");
            return false;
        } else {
            return true;
        }
    }
}
