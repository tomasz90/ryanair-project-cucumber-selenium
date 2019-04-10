package pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.Arrays;

public class LoginPopUp extends TestBase {

    @FindBy(css=".modal-right [type=email]") private WebElement emailinput;
    @FindBy(css=".modal-right [type=password]") private WebElement passwordinput;
    @FindBy(css=".modal-right [type=submit]") private WebElement submitbutton;
    @FindBy(css=".modal-right") private WebElement popup;

    public void enterEmail(String email) {
        emailinput.sendKeys(email);
    }

    public void enterPassword(String password) {
        passwordinput.sendKeys(password);
    }

    public void submit() {
        submitbutton.click();
        waitForPopUpDisappear();
    }

    private void waitForPopUpDisappear(){
        getWait().until(ExpectedConditions.invisibilityOfAllElements(Arrays.asList(popup)));
    }
}
