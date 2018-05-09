package ua.finalproject.controller.util;

import ua.finalproject.dao.util.UtilDao;
import ua.finalproject.model.entities.impl.Car;
import ua.finalproject.model.entities.impl.Order;
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

    public static Order getOrderFromRequest(HttpServletRequest request) {
        String departureStreet = request.getParameter("departure");
        String destinationStreet = request.getParameter("destination");
        Car.Type type = UtilDao.parseCarType(request.getParameter("type"));
        return new Order.OrderBuilder()
                .setDepartureStreet(departureStreet)
                .setDestinationStreet(destinationStreet)
                .setCarType(type)
                .build();
    }
}
