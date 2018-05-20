package ua.finalproject.controller.util;

import ua.finalproject.constants.GlobalConstants;
import ua.finalproject.constants.jsp.RequestAttributes;
import ua.finalproject.constants.jsp.RequestParameters;
import ua.finalproject.constants.messages.ExceptionMessages;
import ua.finalproject.constants.messages.ValidationMessages;
import ua.finalproject.constants.regex.RegexContainer;
import ua.finalproject.util.BundleManager;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLIntegrityConstraintViolationException;

public class DataValidation {

    private static BundleManager bundleManager = BundleManager.INSTANCE;

    public static boolean userDataValidation(HttpServletRequest request) {
        String login = request.getParameter(RequestParameters.LOGIN);
        String password = request.getParameter(RequestParameters.PASSWORD);
        String email = request.getParameter(RequestParameters.EMAIL);
        String firstName = request.getParameter(RequestParameters.FIRST_NAME);
        String secondName = request.getParameter(RequestParameters.SECOND_NAME);
        String phoneNumber = request.getParameter(RequestParameters.PHONE_NUMBER);

        if (login == null || !login.matches(bundleManager.getString(RegexContainer.REGEX_LOGIN))) {
            request.setAttribute(RequestAttributes.WRONG_INPUT_MESSAGE, bundleManager.getString(ValidationMessages.WRONG_LOGIN_FORMAT));
            return false;
        } else if (password == null || !password.matches(bundleManager.getString(RegexContainer.REGEX_PASSWORD))) {
            request.setAttribute(RequestAttributes.WRONG_INPUT_MESSAGE, bundleManager.getString(ValidationMessages.WRONG_PASSWORD_FORMAT));
            return false;

        } else if (email == null || !email.matches(bundleManager.getString(RegexContainer.REGEX_EMAIL))) {
            request.setAttribute(RequestAttributes.WRONG_INPUT_MESSAGE, bundleManager.getString(ValidationMessages.WRONG_EMAIL_FORMAT));
            return false;

        } else if (firstName == null || !firstName.matches(bundleManager.getString(RegexContainer.REGEX_FIRST_NAME))) {
            request.setAttribute(RequestAttributes.WRONG_INPUT_MESSAGE, bundleManager.getString(ValidationMessages.WRONG_FIRST_NAME_FORMAT));
            return false;

        } else if (secondName == null || !secondName.matches(bundleManager.getString(RegexContainer.REGEX_SECOND_NAME))) {
            request.setAttribute(RequestAttributes.WRONG_INPUT_MESSAGE, bundleManager.getString(ValidationMessages.WRONG_SECOND_NAME_FORMAT));
            return false;

        } else if (phoneNumber == null || !phoneNumber.matches(bundleManager.getString(RegexContainer.REGEX_PHONE_NUMBER))) {
            request.setAttribute(RequestAttributes.WRONG_INPUT_MESSAGE, bundleManager.getString(ValidationMessages.WRONG_PHONE_NUMBER_FORMAT));
            return false;

        } else if (!password.equals(request.getParameter(RequestParameters.SECOND_PASSWORD))) {
            request.setAttribute(RequestAttributes.WRONG_INPUT_MESSAGE, bundleManager.getString(ValidationMessages.DIFFERENT_PASSWORDS));
            return false;
        } else {
            return true;
        }
    }

    public static String loginOrEmailNotUniqueDetermination(SQLIntegrityConstraintViolationException e) {
        if (e.getMessage().contains(GlobalConstants.LOGIN)) {
            return bundleManager.getString(ExceptionMessages.NOT_UNIQUE_LOGIN);
        } else {
            return bundleManager.getString(ExceptionMessages.NOT_UNIQUE_EMAIL);
        }
    }

    public static boolean validationOfLoginAndPassword(String login, String pass) {
        return login == null || login.equals(GlobalConstants.EMPTY_STRING) || pass == null || pass.equals(GlobalConstants.EMPTY_STRING);
    }

    public static boolean orderDataValidation(String departureStreet, String destinationStreet) {
        if (departureStreet == null || !departureStreet.matches(bundleManager.getString(RegexContainer.REGEX_STREET))) {
            return false;
        } else
            return destinationStreet != null && destinationStreet.matches(bundleManager.getString(RegexContainer.REGEX_STREET));
    }

    public static boolean checkDiscountAmount(Integer discount) {
        return discount < 16 && discount > 0;
    }

    public static boolean carDataValidation(HttpServletRequest request) {
        String model = request.getParameter(RequestParameters.MODEL);
        String number = request.getParameter(RequestParameters.NUMBER);
        String driver = request.getParameter(RequestParameters.DRIVER);
        if (model == null || !model.matches(bundleManager.getString(RegexContainer.REGEX_MODEL))) {
            request.setAttribute(RequestAttributes.INFORMATION_MESSAGE, bundleManager.getString(ValidationMessages.WRONG_MODEL_FORMAT));
            return false;
        } else if (number == null || !number.matches(bundleManager.getString(RegexContainer.REGEX_NUMBER))) {
            request.setAttribute(RequestAttributes.INFORMATION_MESSAGE, bundleManager.getString(ValidationMessages.WRONG_NUMBER_FORMAT));
            return false;
        } else if (driver == null || !driver.matches(bundleManager.getString(RegexContainer.REGEX_DRIVER_NAME))) {
            request.setAttribute(RequestAttributes.INFORMATION_MESSAGE, bundleManager.getString(ValidationMessages.WRONG_DRIVER_NAME_FORMAT));
            return false;
        } else {
            return true;
        }
    }
}
