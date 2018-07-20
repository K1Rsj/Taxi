package ua.finalproject.model.dao.util;

import ua.finalproject.constants.db.TableNames;

/**
 * Query builder
 */
public enum SQLQueryContainer {

    INSTANCE;

    /**
     * @param tableName name of table
     * @return query to find all entries in table
     */
    public String findAllFromTable(String tableName) {
        return "SELECT * FROM " + tableName;
    }

    /**
     * @param tableName name of table
     * @param id        id
     * @return query to find all entries from table by id
     */
    public String findFromTableById(String tableName, Integer id) {
        return "SELECT * FROM " + tableName + " WHERE " + "id_" +
                tableName + " = " + id;
    }

    /**
     * @param tableName name of table
     * @param id        id
     * @return query to delete entry from table
     */
    public String deleteFromTableById(String tableName, Integer id) {
        return "DELETE FROM " + tableName + " WHERE " + "id_" +
                tableName + " = " + id;
    }

    /**
     * @param tableName      name of table
     * @param parameterName  name of parameter
     * @param parameterValue parameter value
     * @return query to delete entry from table by parameter
     */
    public String deleteFromTableByParameter(String tableName, String
            parameterName,
                                             String parameterValue) {
        return "DELETE FROM " + tableName + " WHERE "
                + parameterName + " = \"" + parameterValue + "\";";
    }

    /**
     * @param tableName name of table
     * @param entityId  entity id
     * @param idName    id name
     * @return query to find foreign key id from table
     */
    public String findForeignKeyIdFromTable(String tableName, String
            entityId, String idName) {
        return "SELECT " + idName + " FROM " + tableName + " WHERE " +
                "id_" + tableName + "" +
                " = " + entityId;
    }

    /**
     * @param carType car type
     * @return query to find free car by type
     */
    public String findFreeCarByType(Integer carType) {
        return "SELECT * FROM " + TableNames.CARS +
                " WHERE state=\"free\" AND car_type_id=\"" + carType +
                "\"";
    }

    /**
     * @param login user's login
     * @return query to find user by login
     */
    public String findUserByLogin(String login) {
        return "SELECT * FROM " + TableNames.USERS + " WHERE login =\""
                + login + "\"";
    }

    /**
     * @param id user's id
     * @return query to find all orders by user
     */
    public String findOrdersByUserId(Integer id) {
        return "SELECT * FROM " + TableNames.ORDERS +
                " where users_id =\"" + id + "\"";
    }
}
