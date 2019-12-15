import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import pageobjects.TestBase;

import java.io.IOException;

import static pageobjects.TestBase.getDriver;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/features", glue = "stepdefs", plugin = { "pretty",
    "json:target/cucumber-json/cucumber.json", "junit:target/cucumber-reports/Cucumber.xml", "html:target/cucumber-reports" })
public class Runner {

    @BeforeClass public static void setUp() throws IOException {
        TestBase.loadProperties();
        TestBase.loadLogger();
    }

    @AfterClass public static void tearDown() {
        getDriver().quit();
    }
}
