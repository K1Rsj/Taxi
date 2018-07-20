package ua.finalproject.model.dao.util;

public enum HQLQueryContainer {

    INSTANCE;

    /**
     * @param entityName name of table
     * @return query to find all entries in table
     */
    public String findAllFromTable(String entityName) {
        return "FROM " + entityName;
    }

    /**
     * @param entityName name of table
     * @param id        id
     * @return query to find all entries from table by id
     */
    public String findFromTableById(String entityName, Integer id) {
        return "FROM " + entityName + " WHERE id = '" + id + "'";
    }

    /**
     * @param entityName name of table
     * @param id        id
     * @return query to delete entry from table
     */
    public String deleteFromTableById(String entityName, Integer id) {
        return "DELETE " + entityName + " WHERE id = '" + id + "'";
    }

    /**
     * @param entityName      name of table
     * @param parameterName  name of parameter
     * @param parameterValue parameter value
     * @return query to delete entry from table by parameter
     */
    public String deleteFromTableByParameter(String entityName, String parameterName, String parameterValue) {
        return "DELETE " + entityName + " WHERE " + parameterName + " = '" + parameterValue + "'";
    }
}
