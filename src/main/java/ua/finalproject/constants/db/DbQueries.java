package ua.finalproject.constants.db;

public interface DbQueries {
    String INSERT_INTO_CARS = "INSERT INTO CARS(model, number, driver, car_type_id) VALUES (?,?,?,?) ";
    String SELECT_FROM_CARS_BY_ID = "SELECT * FROM CARS WHERE id_car = ?";
    String SELECT_FROM_CARS_LEFT_JOIN_CAR_TYPE = "SELECT * FROM CARS LEFT JOIN car_type on car_type_id = id_car_type";
    String DELETE_FROM_CARS_BY_ID = "DELETE FROM CARS WHERE id_car = ?";
    String SELECT_FROM_CARS_LEFT_JOIN_CAR_TYPE_BY_ID = "SELECT * FROM CARS LEFT JOIN " +
            "car_type on car_type_id = id_car_type WHERE car_type_id = ? AND state = 'free'";
    String UPDATE_CAR_STATE_BY_ID = "UPDATE cars set state = ? where id_car = ?";
    String INSERT_INTO_CAR_TYPE = "INSERT INTO car_type(type, starting_price, price_per_km, discount) " +
            "VALUES (?,?,?,?) ";
    String SELECT_FROM_CAR_TYPE_BY_ID = "SELECT * FROM car_type WHERE id_car_type = ?";
    String SELECT_ALL_FROM_CAR_TYPE = "SELECT * FROM car_type";
    String DELETE_FROM_CAR_TYPE_BY_ID = "DELETE FROM car_type WHERE id_car_type = ?";
    String UPDATE_CAR_TYPE_SET_DISCOUNT = "UPDATE car_type" +
            " SET discount = ? WHERE id_car_type = ?";
    String INSERT_INTO_ORDERS = "INSERT INTO orders(departure_street, destination_street, cars_id, users_id, car_type_id, price) " +
            "VALUES (?,?,?,?,?,?)";
    String SELECT_FROM_ORDERS_BY_ID = "SELECT * FROM ORDERS WHERE id_order = ?";
    String SELECT_ALL_FROM_JOIN_ALL_TABLES = "SELECT * FROM ORDERS" +
            " LEFT JOIN USERS on id_user = users_id LEFT JOIN cars on cars_id = id_car LEFT JOIN" +
            " car_type on id_car_type = cars.car_type_id";
    String DELETE_FROM_ORDERS_BY_ID = "DELETE FROM ORDERS WHERE users_id = ?";
    String SELECT_FROM_JOIN_ALL_TABLES_BY_ID = "SELECT * FROM ORDERS" +
            " LEFT JOIN USERS on id_user = users_id LEFT JOIN cars on cars_id = id_car" +
            " LEFT JOIN car_type on id_car_type = cars.car_type_id WHERE login = ?";
    String INSERT_INTO_USERS = "INSERT INTO users(login, password, email, first_name, second_name, phone_number) " +
            "VALUES (?,?,?,?,?,?)";
    String SELECT_FROM_USERS_BY_ID = "SELECT * FROM USERS WHERE id_user = ?";
    String SELECT_ALL_FROM_USERS = "SELECT * FROM USERS";
    String DELETE_FROM_USERS_BY_ID = "DELETE FROM USERS WHERE id_user = ?";
    String SELECT_FROM_USERS_BY_LOGIN = "SELECT * FROM USERS WHERE LOGIN = ?";
    String UPDATE_USER_MONEY_SPENT = "UPDATE USERS SET money_spent = ?" +
            " where id_user = ?";
}
