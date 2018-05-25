package ua.finalproject.constants.messages;

/**
 * Messages for logger
 */
public interface LogMessages {

    String GET_FREE_CAR_BY_TYPE_ERROR = "Get free car by type error";
    String UPDATE_CAR_STATE_ERROR = "Update car state error";
    String CONNECTION_CLOSE_ERROR = "Connection close error";
    String UPDATE_DISCOUNT_ERROR = "Update discount error";
    String FIND_USER_ORDERS_ERROR = "Find orders by user login error";
    String FIND_BY_LOGIN_ERROR = "Find by login error";
    String UPDATE_USER_MONEY_SPENT_ERROR = "Update money spent error";
    String AUTO_CLOSABLE_RESOURCE_ERROR_IN_SHOW_ALL_CARS = "Auto-closable resource error in show all cars";
    String AUTO_CLOSABLE_RESOURCE_ERROR_IN_CHANGE_CAR_STATE = "Auto-closable resource error in change car state from busy to free";
    String ADD_CAR_ERROR = "Add car error";
    String AUTO_CLOSABLE_RESOURCE_ERROR_IN_ADD_CAR = "Auto-closable resource error in add car";
    String ADD_CAR_CONNECTION_ROLLBACK_ERROR = "Add car connection rollback error";
    String MAKE_ORDER_ERROR = "Make order error";
    String AUTO_CLOSABLE_RESOURCE_ERROR_IN_MAKE_ORDER = "Auto-closable resource error in make order";
    String CONFIRM_ORDER_ERROR = "Confirm order error";
    String AUTO_CLOSABLE_RESOURCE_ERROR_IN_CONFIRM_ORDER = "Auto-closable resource error in confirm order";
    String AUTO_CLOSABLE_RESOURCE_ERROR_IN_CANCEL_ORDER = "Auto-closable resource error in cancel order";
    String AUTO_CLOSABLE_RESOURCE_ERROR_IN_GET_ALL_USERS_ORDERS = "Auto-closable resource error in get all user's orders";
    String ODDER_CONNECTION_ROLLBACK_ERROR = "Order connection rollback error";
    String AUTO_CLOSABLE_RESOURCE_ERROR_IN_REGISTER_USER = "Auto-closable resource error in register user";
    String AUTO_CLOSABLE_RESOURCE_ERROR_IN_FIND_USER_BY_LOGIN = "Auto-closable resource error in find user by login";
    String AUTO_CLOSABLE_RESOURCE_ERROR_IN_GET_USER_DISCOUNT = "Auto-closable resource error in get user discount";
    String AUTO_CLOSABLE_RESOURCE_ERROR_IN_SHOW_ALL_USERS = "Auto-closable resource error in show all users";
    String DELETE_USER_BY_ID_ERROR = "Delete user by id error";
    String AUTO_CLOSABLE_RESOURCE_ERROR_IN_DELETE_USER_BY_ID = "Auto-closable resource error in delete user by id";
    String DELETE_USER_BY_ID_CONNECTION_ROLLBACK_ERROR = "Delete user by id connection rollback error";
    String DB_CONNECTION_ERROR = "Connection error";
    String LOAD_DRIVER_CLASS_ERROR = "Load driver class error";
    String NO_FREE_CAR_SUCH_TYPE = "No free car with such type";
}
