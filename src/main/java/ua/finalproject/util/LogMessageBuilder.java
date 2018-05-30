package ua.finalproject.util;

/**
 * Message builder for logger
 */
public enum LogMessageBuilder {

    INSTANCE;

    public String userLogInInfo(String login) {
        return "User " + login + " logged successfully";
    }

    public String userAlreadyLoggedInfo(String login) {
        return "User " + login + " tried to log in to an account that is" +
                " already in use";
    }

    public String invalidAttemptOfLogInInfo(String login) {
        return "Invalid attempt of login user:'" + login + "'";
    }

    public String duplicateCarNumberInfo(String number) {
        return "Duplicate car number:'" + number + "'";
    }

    public String newCarInfo(String number) {
        return "Added new car:'" + number + "'";
    }

    public String newDiscountInfo(String type, Integer discount) {
        return "Added new discount:'" + discount + "'% for car type: " +
                type;
    }

    public String cancelOrderInfo(String login) {
        return "User: " + login + " cancelled order";
    }

    public String confirmOrderInfo(String login) {
        return "User: " + login + " confirmed order";
    }

    public String makeOrderInfo(String name, String secondName, String
            carNumber) {
        return "User:'" + name + " " + secondName + " make order for car" +
                " " + carNumber;
    }

    public String duplicateUserInfo(String login) {
        return "User " + login + " is already registered";
    }

    public String userRegistrationInfo(String login) {
        return "New user " + login + " is registered";
    }

    public String createEntryError(String tableName) {
        return "Create entry in table " + tableName + " error";
    }

    public String deleteEntryError(String tableName, Integer id) {
        return "Delete entry from " + tableName + " with " + id + " error";
    }

    public String deleteEntryByParameterError(String tableName, String
            parameterName) {
        return "Delete entry from " + tableName + " with " +
                parameterName + " error";
    }
}

