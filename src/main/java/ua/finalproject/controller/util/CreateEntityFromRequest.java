package ua.finalproject.controller.util;

import ua.finalproject.constants.jsp.RequestParameters;
import ua.finalproject.model.entities.impl.Car;
import ua.finalproject.model.entities.impl.User;

import javax.servlet.http.HttpServletRequest;

public class CreateEntityFromRequest {

    public static User getUserFromRequest(HttpServletRequest request) {
        String login = request.getParameter(RequestParameters.LOGIN);
        String password = request.getParameter(RequestParameters.PASSWORD);
        String email = request.getParameter(RequestParameters.EMAIL);
        String firstName = request.getParameter(RequestParameters.FIRST_NAME);
        String secondName = request.getParameter(RequestParameters.SECOND_NAME);
        String phoneNumber = request.getParameter(RequestParameters.PHONE_NUMBER);
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
        String model = request.getParameter(RequestParameters.MODEL);
        String number = request.getParameter(RequestParameters.NUMBER);
        String driver = request.getParameter(RequestParameters.DRIVER);
        return new Car.CarBuilder()
                .setModel(model)
                .setNumber(number)
                .setDriver(driver)
                .build();
    }
}
