package pageobjects;

import lombok.extern.log4j.Log4j;
import org.openqa.selenium.By;

@Log4j public class MyAccountPage extends TestBase {

    private By accountWelcomeMessage = By.className("info-account");

    @Override public boolean isLoaded() {
        log.info("Check if MyAccount is loaded");
        return isPresent(accountWelcomeMessage);
    }
}
