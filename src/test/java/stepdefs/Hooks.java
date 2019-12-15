package stepdefs;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import lombok.extern.log4j.Log4j;
import org.openqa.selenium.support.PageFactory;
import pageobjects.HomePage;
import utilities.ScreenshotTaker;

import java.io.IOException;

import static pageobjects.TestBase.getDriver;
@Log4j
public class Hooks {

    @Before
    public static void before() {
        log.info("\n\n********** NEXT TEST **********\n");
        HomePage homePage = PageFactory.initElements(getDriver(), HomePage.class);
        homePage.navigate();
        homePage.ifLoggedSignOut();
    }

    @After
    public void tearDown(Scenario scenario) throws IOException {
        ScreenshotTaker.takeIfFailed(scenario);
    }
}
