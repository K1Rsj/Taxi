package ua.finalproject.util;

import ua.finalproject.constants.GlobalConstants;

import java.util.ResourceBundle;

public enum PropertyManager {
    CONFIG(GlobalConstants.DB);

    private ResourceBundle resource;

    PropertyManager(String propertyName) {
        this.resource = ResourceBundle.getBundle(propertyName);
    }

    public String getString(String key) {
        return resource.getString(key);
    }
}
