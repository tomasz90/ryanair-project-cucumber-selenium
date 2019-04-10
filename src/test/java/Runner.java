import cucumber.api.CucumberOptions;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.junit.Cucumber;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import pageobjects.TestBase;
import utilities.ScreenshotTaker;

import java.io.IOException;


@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/features", glue = "stepdefs")
public class Runner {

    @BeforeClass
    public static void setUp() throws IOException {
        TestBase.loadProperties();
        TestBase.loadLogger();
    }
}
