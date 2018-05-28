package ua.finalproject.constants.db;

/**
 * DB queries for taxi DB
 */
public interface DbQueries {

    String INSERT_INTO_CARS = "INSERT INTO CARS(model, number, driver, car_type_id) VALUES " +
            "(?,?,?,?) ";
    String UPDATE_CAR_STATE_BY_ID = "UPDATE cars set state = ? where id_cars = ?";
    String INSERT_INTO_CAR_TYPE = "INSERT INTO car_type(type, starting_price, price_per_km, " +
            "discount) " +
            "VALUES (?,?,?,?) ";
    String UPDATE_CAR_TYPE_SET_DISCOUNT = "UPDATE car_type" +
            " SET discount = ? WHERE id_car_type = ?";
    String INSERT_INTO_ORDERS = "INSERT INTO orders(departure_street, destination_street, " +
            "cars_id, users_id, car_type_id, price) " +
            "VALUES (?,?,?,?,?,?)";
    String INSERT_INTO_USERS = "INSERT INTO users(login, password, email, first_name, " +
            "second_name, phone_number) " +
            "VALUES (?,?,?,?,?,?)";
    String UPDATE_USER_MONEY_SPENT = "UPDATE USERS SET money_spent = ?" +
            " where id_users = ?";
}
