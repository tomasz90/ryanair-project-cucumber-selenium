package stepdefs;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import utilities.ScreenshotTaker;

import java.io.IOException;

public class Hooks {

    @After
    public void tearDown(Scenario scenario) throws IOException {
        ScreenshotTaker.takeIfFailed(scenario);
    }
}
