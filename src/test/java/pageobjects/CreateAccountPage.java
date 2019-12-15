package pageobjects;

import lombok.extern.log4j.Log4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Log4j
public class CreateAccountPage extends TestBase {

    @FindBy(id = "customer_firstname") private WebElement firstNameField;

    @FindBy(id = "customer_lastname") private WebElement lastNameField;

    @FindBy(id = "passwd") private WebElement passField;

    @FindBy(id = "address1") private WebElement addressField;

    @FindBy(id = "city") private WebElement cityField;

    @FindBy(id = "id_state") private WebElement stateDDM;

    @FindBy(id = "postcode") private WebElement zipField;

    @FindBy(id = "id_country") private WebElement countryDDM;

    @FindBy(id = "phone_mobile") private WebElement phoneField;

    @FindBy(id = "submitAccount") private WebElement registerField;

    private By creationForm = By.id("account-creation_form");

    @Override public boolean isLoaded() {
        return isPresent(creationForm);
    }

    public void enterFirstName() {
        log.info("Enter first name");
        firstNameField.sendKeys(getRandom("first_name"));
    }

    public void enterLastName() {
        log.info("Enter last name");
        lastNameField.sendKeys(getRandom("last_name"));
    }

    public void enterPassword() {
        log.info("Enter password");
        passField.sendKeys(getRandom("valid_password"));
    }

    public void enterAddress() {
        log.info("Enter address");
        addressField.sendKeys(getRandom("address"));
    }

    public void enterCity() {
        log.info("Enter city");
        cityField.sendKeys(getRandom("city"));
    }

    public void enterZip() {
        log.info("Enter zip");
        zipField.sendKeys(getRandom("zip"));
    }

    public void chooseState() {
        log.info("Choose state");
        stateDDM.sendKeys(getRandom("state"));
    }

    public void enterPhone() {
        log.info("Enter phone");
        phoneField.sendKeys(getRandom("phone"));
    }

    public void clickRegister() {
        log.info("Click register");
        registerField.click();
    }
}
