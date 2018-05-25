package ua.finalproject.controller.util;

import org.junit.Test;
import ua.finalproject.constants.jsp.RequestParameters;
import ua.finalproject.controller.commands.user.UserRegistrationCommand;
import ua.finalproject.model.services.UserService;

import javax.servlet.http.HttpServletRequest;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.booleanThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DataValidationTest {

    @Test
    public void userDataValidation() {

        HttpServletRequest request = mock(HttpServletRequest.class);
        String login = "zeus";
        String email = "zeus@gmail.com";
        String firstName = "Ivan";
        String secondName = "Shevchenko";
        String phoneNumber = "0664567893";
        String password = "123456";
        String password2 = "123456";

        when(request.getParameter(RequestParameters.LOGIN)).thenReturn(login);
        when(request.getParameter(RequestParameters.EMAIL)).thenReturn(email);
        when(request.getParameter(RequestParameters.FIRST_NAME)).thenReturn(firstName);
        when(request.getParameter(RequestParameters.SECOND_NAME)).thenReturn(secondName);
        when(request.getParameter(RequestParameters.PHONE_NUMBER)).thenReturn(phoneNumber);
        when(request.getParameter(RequestParameters.PASSWORD)).thenReturn(password);
        when(request.getParameter(RequestParameters.SECOND_PASSWORD)).thenReturn(password2);

        boolean result = DataValidation.userDataValidation(request);
        assertTrue(result);
    }

    @Test
    public void validationOfLoginAndPassword() {
        String login = "micro";
        String password = "qwerty";
        boolean result = DataValidation.validationOfLoginAndPassword(login, password);
        assertFalse(result);
    }

    @Test
    public void orderDataValidation() {
        String destinationStreet = "Sherbakova 5";
        String departureStreet = "Avtozavodskaya 10";
        boolean result = DataValidation.orderDataValidation(departureStreet, destinationStreet);
        assertTrue(result);

    }

    @Test
    public void checkDiscountAmount() {
        Integer discount = 20;
        boolean result = DataValidation.checkDiscountAmount(discount);
        assertFalse(result);

    }
}