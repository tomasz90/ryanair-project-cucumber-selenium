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
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

@Log4j
public abstract class TestBase extends PageFactory {

    private static WebDriver driver;
    private static WebDriverWait wait;
    private static Pair<Properties, String> driverConfig = new Pair<>(new Properties(), "src/test/resources/properties/driver-config.properties");
    private static Pair<Properties, String> testConfig = new Pair<>(new Properties(), "src/test/resources/properties/test-data.properties");
    private static Pair<Properties, String> log4j = new Pair<>(new Properties(), "src/test/resources/properties/log4j.properties");
    private static Pair<Properties, String> output = new Pair<>(new Properties(), "src/test/resources/properties/test-output.properties");


    public abstract boolean isLoaded();

    private static Properties getDriverProperties() {
        return driverConfig.getProperties();
    }

    public static Properties getTestProperties() {
        return testConfig.getProperties();
    }
    public static Properties getOutputProperties() {
        return output.getProperties();
    }
    public static WebDriverWait getWait() {
        return wait;
    }

    public static WebDriverWait getCustomWait(int timeOut) {
        return new WebDriverWait(getDriver(), timeOut);
    }


    public static void loadProperties() throws IOException {
        PropertiesManager.load(driverConfig, testConfig, output, log4j);
    }

    public static void loadLogger() {
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

    static WebElement findElementQuietly(By by, long timeOut) {
        try {
            WebElement element = new WebDriverWait(driver, timeOut).until(ExpectedConditions.presenceOfElementLocated(by));
            return element;
        } catch (Exception e) {
            return null;
        }
    }

    static boolean isPresent(By by) {
        return findElementQuietly(by, 2) != null;
    }


    protected static void waitForBeingReady() {
        log.info("Waiting for page ready.");
        getWait().until((ExpectedCondition<Boolean>) wd ->
                ((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete"));
    }

    public static String getRandom(String key) {
        List<String> dataList = new ArrayList<>();
        String data = getTestProperties().getProperty(key);
        while (data.contains(",")) {
            int commaPlace = data.indexOf(",");
            dataList.add(data.substring(0, commaPlace));
            data = data.substring(commaPlace + 1);
        }
        dataList.add(data);
        Collections.shuffle(dataList);
        return dataList.get(0);
    }
}
