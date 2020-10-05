package util;

import java.util.ResourceBundle;

public class QueryManager {

    private QueryManager() {}

    private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle("sql_query");

    public static String getString(String key) {
        return RESOURCE_BUNDLE.getString(key);
    }

}
