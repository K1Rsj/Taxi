package ua.finalproject.controller.util;

import ua.finalproject.model.entities.impl.Car;
import ua.finalproject.model.entities.impl.User;

import javax.servlet.http.HttpServletRequest;

public class CreateEntityFromRequest {

    public static User getUserFromRequest(HttpServletRequest request) {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String firstName = request.getParameter("first_name");
        String secondName = request.getParameter("second_name");
        String phoneNumber = request.getParameter("phone_number");
        return User.builder()
                .login(login)
                .password(password)
                .email(email)
                .firstName(firstName)
                .secondName(secondName)
                .phoneNumber(phoneNumber)
                .build();
    }

    public static Car getCarFromRequest(HttpServletRequest request) {
        String model = request.getParameter("model");
        String number = request.getParameter("number");
        String driver = request.getParameter("driver");
        return new Car.CarBuilder()
                .setModel(model)
                .setNumber(number)
                .setDriver(driver)
                .build();
    }
}
