package ua.finalproject.constants.db;

public interface DbHQLQueries {
    String UPDATE_CAR_TYPE_SET_DISCOUNT = "UPDATE CarType SET discount = :discount WHERE id = :id";
    String FIND_FREE_CAR_BY_TYPE_ID = "FROM Car WHERE state='free' AND car_type_id= :carTypeId";
    String UPDATE_CAR_STATE_BY_ID = "UPDATE Car SET state = :state WHERE id = :id";
    String FIND_USER_BY_LOGIN = "FROM User WHERE login = :login";
    String UPDATE_USER_MONEY_SPENT = "UPDATE User SET money_spent = :moneySpent WHERE id = :id";
    String FIND_ORDERS_BY_USER_ID = "FROM Order WHERE users_id = :userId";
}
