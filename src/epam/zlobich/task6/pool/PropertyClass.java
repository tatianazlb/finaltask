package epam.zlobich.task6.pool;

import java.util.Properties;

public class PropertyClass {
    private static Properties properties;

    static Properties getProperties() {
        return properties;
    }

    public static void setProperties(Properties properties) {
        PropertyClass.properties = properties;
    }
}
