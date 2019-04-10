package pageobjects;

import lombok.extern.log4j.Log4j;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.Arrays;

@Log4j
public class LoginPopUp extends TestBase {

    @FindBy(css = ".modal-right [type=email]")
    private WebElement emailinput;
    @FindBy(css = ".modal-right [type=password]")
    private WebElement passwordinput;
    @FindBy(css = ".modal-right [type=submit]")
    private WebElement submitbutton;
    @FindBy(css = ".modal-right")
    private WebElement popup;

    public void enterEmail(String email) {
        log.info("Enter email: " + email);
        emailinput.sendKeys(email);
    }

    public void enterPassword(String password) {
        log.info("Enter password: " + password);
        passwordinput.sendKeys(password);
    }

    public void submit() {
        log.info("Submit");
        submitbutton.click();
        waitForPopUpDisappear();
    }

    private void waitForPopUpDisappear() {
        log.info("Wait for popup disappear");
        getWait().until(ExpectedConditions.invisibilityOfAllElements(Arrays.asList(popup)));
    }
}
