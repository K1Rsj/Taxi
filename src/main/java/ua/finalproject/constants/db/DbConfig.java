package ua.finalproject.constants.db;

/**
 * Keys for db.properties
 */
public interface DbConfig {

    String DB_URL = "db.url";
    String DB_DRIVER = "db.driver";
    String DB_USER = "db.user";
    String DB_PASSWORD = "db.password";
    String DB_MIN_IDLE = "db.min.idle";
    String DB_MAX_IDLE = "db.max.idle";
    String DB_MAX_OPEN_PS = "db.max.open.prepare.statements";
    String DB_DLL_VALIDATE = "db.dll.validate";
}
