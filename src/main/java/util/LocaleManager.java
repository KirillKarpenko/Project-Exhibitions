package util;

import java.util.ResourceBundle;

public class LocaleManager {

    private LocaleManager() {}

    private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle("locale");

    public static String getString(String key) {
        return RESOURCE_BUNDLE.getString(key);
    }

}
