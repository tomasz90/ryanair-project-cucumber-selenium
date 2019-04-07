package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class TestBase {

    private static WebDriver driver;
    private static WebDriverWait wait;
    private static Properties driverConfig = new Properties();
    protected static Properties webConfig = new Properties();

    static {
        try {
            InputStream driverConfigInput = new FileInputStream("src/test/resources/properties/driver-config.properties");
            InputStream webConfigInput = new FileInputStream("src/test/resources/properties/web-config.properties");
            driverConfig.load(driverConfigInput);
            webConfig.load(webConfigInput);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String getDriverFileByOS() {
        String os = System.getProperty("os.name").toLowerCase();
        if (os.contains("windows")) {
           return driverConfig.getProperty("chromedriver_windows");
        }
        if (os.contains("mac")) {
            return driverConfig.getProperty("chromedriver_mac");
        }
        else {
            return driverConfig.getProperty("chromedriver_linux");
        }
    }

    public static WebDriver getDriver() {
        System.setProperty(driverConfig.getProperty("driver_key"), driverConfig.getProperty("driver_path") + getDriverFileByOS());
        if (driver == null) {
            driver = new ChromeDriver();
            setDriverOptions();
        }
        return driver;
    }

    private static void setDriverOptions() {
            driver.manage().window().maximize();
            driver.manage().timeouts().pageLoadTimeout(Long.valueOf(driverConfig.getProperty("page_load_wait")), TimeUnit.SECONDS);
            driver.manage().timeouts().implicitlyWait(Long.valueOf(driverConfig.getProperty("implicitly_wait")), TimeUnit.SECONDS);
            wait = new WebDriverWait(driver, Long.valueOf(driverConfig.getProperty("explicitly_wait")));
    }
}
