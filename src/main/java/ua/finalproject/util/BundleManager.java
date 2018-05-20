package ua.finalproject.util;

import ua.finalproject.constants.GlobalConstants;

import java.util.Locale;
import java.util.ResourceBundle;

public enum BundleManager {
    INSTANCE;

    private ResourceBundle resourceBundle;

    private final String RESOURCE_BUNDLE_NAME = GlobalConstants.MESSAGES;

    BundleManager() {
        resourceBundle = ResourceBundle.getBundle(RESOURCE_BUNDLE_NAME, Locale.getDefault());
    }

    public void changeLocale(Locale locale){
        if(locale.toString().contains(GlobalConstants.UK)) {
            locale = new Locale(GlobalConstants.UK, GlobalConstants.UA);
        }
        resourceBundle = ResourceBundle.getBundle(RESOURCE_BUNDLE_NAME, locale);
    }

    public String getString(String key){
        return resourceBundle.getString(key);
    }

}
