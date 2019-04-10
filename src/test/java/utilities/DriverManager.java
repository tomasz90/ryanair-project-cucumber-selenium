package utilities;

import java.util.Properties;

public class DriverManager {

    public static void setDriverPath(Properties properties) {
        System.setProperty(properties.getProperty("chrome_driver_key"), properties.getProperty("chrome_driver_path") + getDriverFileByOS(properties));
    }

    private static String getDriverFileByOS(Properties properties) {
        String os = System.getProperty("os.name").toLowerCase();
        if (os.contains("windows")) {
            return properties.getProperty("driver_windows");
        }
        if (os.contains("mac")) {
            return properties.getProperty("driver_mac");
        } else {
            return properties.getProperty("driver_linux");
        }
    }
}
