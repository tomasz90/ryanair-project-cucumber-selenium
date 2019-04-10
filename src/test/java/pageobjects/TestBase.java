package pageobjects;

import lombok.extern.log4j.Log4j;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.DriverManager;
import utilities.Pair;
import utilities.PropertiesManager;

import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

@Log4j
public class TestBase extends PageFactory {

    private static WebDriver driver;
    private static WebDriverWait wait;
    private static Pair<Properties, String> driverConfig = new Pair<>(new Properties(), "src/test/resources/properties/driver-config.properties");
    private static Pair<Properties, String> testConfig = new Pair<>(new Properties(), "src/test/resources/properties/test-data.properties");
    private static Pair<Properties, String> log4j = new Pair<>(new Properties(), "src/test/resources/properties/log4j.properties");

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
        PropertiesManager.load(driverConfig, testConfig, log4j);
    }

    public static void loadLogger(){
        PropertyConfigurator.configure(log4j.getProperties());
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

    protected static WebElement findElementQuietly(By by, long timeOut) {
        try {
            WebElement element = new WebDriverWait(driver, timeOut).until(ExpectedConditions.presenceOfElementLocated(by));
            return element;
        } catch (Exception e) {
            return null;
        }
    }

    protected static void waitForBeingReady() {
        log.info("Waiting for page ready.");
        getWait().until((ExpectedCondition<Boolean>) wd ->
                ((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete"));
    }
}
