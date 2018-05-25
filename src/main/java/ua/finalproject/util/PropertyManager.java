package ua.finalproject.util;

import ua.finalproject.constants.GlobalConstants;

import java.util.ResourceBundle;

/**
 * Resource manager
 */
public enum PropertyManager {
    CONFIG(GlobalConstants.DB);

    /**
     * @see ResourceBundle
     */
    private ResourceBundle resource;

    PropertyManager(String propertyName) {
        this.resource = ResourceBundle.getBundle(propertyName);
    }

    /**
     *
     * @param key key
     * @return message from resource by key
     */
    public String getString(String key) {
        return resource.getString(key);
    }
}
