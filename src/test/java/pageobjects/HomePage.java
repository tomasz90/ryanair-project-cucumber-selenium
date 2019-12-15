package pageobjects;

import lombok.extern.log4j.Log4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Log4j public class HomePage extends TestBase {

    @FindBy(className = "login") private WebElement signInButton;

    private By accountButton = By.className("account");

    @FindBy(css = ".sf-menu li > [title=Women]") private WebElement womenButton;

    @FindBy(css = ".sf-menu > li:nth-child(2) [title=Dresses]") private WebElement dressesButton;

    @FindBy(className = ".sf-menu > li:nth-child(3) [title=T-shirts]") private WebElement tshirtButton;

    @FindBy(className = "logout") private WebElement singOutButton;

    private By logo = By.id("header_logo");

    @Override public boolean isLoaded() {
        return isPresent(logo);
    }

    public void ifLoggedSignOut() {
        if (isLoggedIn()) {
            log.info("Click sign out");
            singOutButton.click();
        }
    }

    public void navigate() {
        log.info("Navigate to test URL");
        getDriver().navigate().to(getTestProperties().getProperty("test_url"));
    }

    public void clickSignIn() {
        log.info("Click sign in");
        signInButton.click();
    }

    public boolean isLoggedIn() {
        log.info("Check is logged in");
        return isPresent(accountButton);
    }

    public void goToSection(String section) {
        log.info("Go to section: " + section);
        switch (section) {
            case "women":
                womenButton.click();
                break;
            case "dresses":
                dressesButton.click();
                break;
            case "t-shirt":
                tshirtButton.click();
                break;
        }
    }
}
