package ua.finalproject.util;

import ua.finalproject.constants.GlobalConstants;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Bundle manager
 */
public enum BundleManager {
    INSTANCE;

    /**
     * name of resource bundle
     */
    private final String RESOURCE_BUNDLE_NAME = GlobalConstants.MESSAGES;
    /**
     * @see ResourceBundle
     */
    private ResourceBundle resourceBundle;

    BundleManager() {
        resourceBundle = ResourceBundle.getBundle(RESOURCE_BUNDLE_NAME, Locale.getDefault());
    }

    /**
     * change resource bundle locale
     *
     * @param locale locale
     */
    public void changeLocale(Locale locale) {
        if (locale.toString().contains(GlobalConstants.UK)) {
            locale = new Locale(GlobalConstants.UK, GlobalConstants.UA);
        }
        resourceBundle = ResourceBundle.getBundle(RESOURCE_BUNDLE_NAME, locale);
    }

    /**
     * @param key key
     * @return string from resource by key
     */
    public String getString(String key) {
        return resourceBundle.getString(key);
    }

}
