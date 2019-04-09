package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.DriverManager;
import utilities.Pair;
import utilities.PropertiesManager;

import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class TestBase {

    private static WebDriver driver;
    private static WebDriverWait wait;
    private static Pair<Properties, String> driverConfig = new Pair<>(new Properties(), "src/test/resources/properties/driver-config.properties");
    private static Pair<Properties, String> testConfig = new Pair<>(new Properties(), "src/test/resources/properties/test-data.properties");

    private static Properties getDriverProperties() {
        return driverConfig.getProperties();
    }
    public static Properties getTestProperties() {
        return testConfig.getProperties();
    }
    public static WebDriverWait getWait() {
        return wait;
    }
    public static WebDriverWait getCustomWait(int timeOut){
        return new WebDriverWait(getDriver(), timeOut);
    }

    public static void loadProperties() throws IOException {
        PropertiesManager.load(driverConfig, testConfig);
    }

    public static WebDriver getDriver() {
        DriverManager.setDriverPath(getDriverProperties());
        if (driver == null) {
            driver = new ChromeDriver();
            setDriverOptions();
        }
        return driver;
    }

    private static void setDriverOptions() {
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(Long.valueOf(getDriverProperties().getProperty("page_load_wait")), TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(Long.valueOf(getDriverProperties().getProperty("implicitly_wait")), TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, Long.valueOf(getDriverProperties().getProperty("explicitly_wait")));
    }

    protected static WebElement findElementQuietly(By by) {
        return findElementQuietly(by, Long.valueOf(getDriverProperties().getProperty("explicitly_wait")));
    }

    protected static WebElement findElementQuietly(By by, long timeOut) {
        try {
            WebElement element = new WebDriverWait(driver, timeOut).until(ExpectedConditions.presenceOfElementLocated(by));
            return element;
        } catch (Exception e) {
            return null;
        }
    }

    protected static void waitForBeingReady() {
        getWait().until((ExpectedCondition<Boolean>) wd ->
                ((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete"));
    }

    protected WebElement waitForBeingClickable(By by) {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(by));
        return element;
    }

    protected WebElement findDynamicElement(By by) {
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    protected List<WebElement> findDynamicElements(By by) {
        return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
    }
}
