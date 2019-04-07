package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPopUp extends TestBase {

    private static final By emailInput = By.cssSelector(".modal-right [type=email]");
    private static final By passwordInput = By.cssSelector(".modal-right [type=password]");
    private static final By submitButton = By.cssSelector(".modal-right [type=submit]");
    private static final By popUp = By.cssSelector(".modal-right");

    public void enterEmail(String email) {
        getDriver().findElement(emailInput).sendKeys(email);
    }

    public void enterPassword(String password) {
        getDriver().findElement(passwordInput).sendKeys(password);
    }

    public void submit() {
        getDriver().findElement(submitButton).click();
        waitForPopUpDisappear();
    }

    private void waitForPopUpDisappear(){
        getWait().until(ExpectedConditions.invisibilityOfElementLocated(popUp));
    }
}
