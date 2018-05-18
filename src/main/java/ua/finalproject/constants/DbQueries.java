package ua.finalproject.constants;

public interface DbQueries {
    String INSERT_INTO_CARS = "INSERT INTO CARS(model, number, driver, car_type_id) VALUES (?,?,?,?) ";
    String SELECT_FROM_CARS_BY_ID = "SELECT * FROM CARS WHERE id_car = ?";
    String SELECT_FROM_CARS_LEFT_JOIN_CAR_TYPE = "SELECT * FROM CARS LEFT JOIN car_type on car_type_id = id_car_type";
    String DELETE_FROM_CARS_BY_ID = "DELETE FROM CARS WHERE id_car = ?";
    String SELECT_FROM_CARS_LEFT_JOIN_CAR_TYPE_BY_ID = "SELECT * FROM CARS LEFT JOIN " +
            "car_type on car_type_id = id_car_type WHERE car_type_id = ? AND state = 'free'";
    String UPDATE_CAR_STATE_BY_ID = "UPDATE cars set state = ? where id_car = ?";
}
