package ua.finalproject.constants.db;

/**
 * Name for columns in taxi DB
 */
public interface TableColumnNames {

    /**
     * Column names from users table
     */
    String ID_USER = "id_user";
    String LOGIN = "login";
    String PASSWORD = "password";
    String EMAIL = "email";
    String FIRST_NAME = "first_name";
    String SECOND_NAME = "second_name";
    String PHONE_NUMBER = "phone_number";
    String MONEY_SPENT = "money_spent";
    String ROLE = "role";

    /**
     * Column names from orders table
     */
    String ID_ORDER = "id_order";
    String DEPARTURE_STREET = "departure_street";
    String DESTINATION_STREET = "destination_street";
    String PRICE = "price";

    /**
     * Column names from car_type table
     */
    String ID_CAR_TYPE = "id_car_type";
    String TYPE = "type";
    String STARTING_PRICE = "starting_price";
    String PRICE_PER_KM = "price_per_km";
    String DISCOUNT = "discount";

    /**
     * Column names from cars table
     */

    String ID_CAR = "id_car";
    String MODEL = "model";
    String NUMBER = "number";
    String STATE = "state";
    String DRIVER = "driver";
}
