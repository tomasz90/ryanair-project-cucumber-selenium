package pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SignInPage extends TestBase {

    @FindBy(id = "email") private WebElement emailField;

    @FindBy(id = "passwd") private WebElement passwordField;

    @FindBy(id = "SubmitLogin") private WebElement submitButton;

    @FindBy(css = "#center_column > .alert.alert-danger li") private WebElement errorMessage;

    @FindBy(id = "SubmitCreate") private WebElement createAccountButton;

    @FindBy(id = "email_create") private WebElement createEmailField;

    @FindBy(css = "#center_column .page-heading") private WebElement authenticationLabel;

    @Override public boolean isLoaded() {
        return authenticationLabel.getText().equals("AUTHENTICATION");
    }

    public void enterEmail(String s) {
        emailField.sendKeys(s);
    }

    public void enterPassword(String s) {
        passwordField.sendKeys(s);
    }

    public void submit() {
        submitButton.click();
    }

    public String getErrorMessage() {
        return errorMessage.getText();
    }

    public void createAccount() {
        createAccountButton.click();
    }

    public void enterNewEmail() {
        createEmailField.sendKeys(getRandom() + "@test.pl");
    }

    private double getRandom() {
        int min = 0;
        int max = 100000;
        return (Math.random() * ((max - min) + 1)) + min;
    }
}
