package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class TestBase {

    private static WebDriver driver;
    private static Properties config = new Properties();

    static {
        try {
            InputStream input = new FileInputStream("src/test/resources/properties/config.properties");
            config.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String getDriverFileByOS() {
        String os = System.getProperty("os.name").toLowerCase();
        if (os.contains("windows")) {
           return config.getProperty("chromedriver_windows");
        }
        if (os.contains("mac")) {
            return config.getProperty("chromedriver_mac");
        }
        else {
            return config.getProperty("chromedriver_linux");
        }
    }

    public static WebDriver getDriver() {
        System.setProperty(config.getProperty("driver_key"), config.getProperty("driver_path") + getDriverFileByOS());
        if (driver == null) {
            driver = new ChromeDriver();
        }
        return driver;
    }
}
