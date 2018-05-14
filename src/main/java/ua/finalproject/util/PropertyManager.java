package ua.finalproject.util;

import java.util.ResourceBundle;

public enum PropertyManager {
    CONFIG("db");

    private ResourceBundle resource;

    PropertyManager(String propertyName) {
        this.resource = ResourceBundle.getBundle(propertyName);
    }

    public String getString(String key) {
        return resource.getString(key);
    }
}
