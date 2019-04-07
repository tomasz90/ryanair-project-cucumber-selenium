package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class TestBase {

    private static WebDriver driver;

    public static WebDriverWait getWait() {
        return wait;
    }

    private static WebDriverWait wait;

    public static Properties getDriverConfig() {
        return driverConfig;
    }

    public static Properties getWebConfig() {
        return webConfig;
    }

    private static Properties driverConfig = new Properties();
    private static Properties webConfig = new Properties();

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

    protected static WebElement findElementQuietly(By by) {
        return findElementQuietly(by, Long.valueOf(driverConfig.getProperty("explicitly_wait")));
    }

    protected static WebElement findElementQuietly(By by, long timeOut) {
        try {
            wait = new WebDriverWait(driver, timeOut);
            WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(by));
            return element;
        } catch (Exception e) {
            return null;
        }
    }

    protected static void waitForBeingReady(){
        new WebDriverWait(getDriver(), 3).until((ExpectedCondition<Boolean>) wd ->
                ((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete"));
    }

    protected WebElement waitForBeingClickable(By by){
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(by));
        return element;
    }
}
