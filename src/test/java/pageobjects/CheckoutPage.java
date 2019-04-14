package pageobjects;

import lombok.extern.log4j.Log4j;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import utilities.Data;

import java.util.*;

@Log4j
public class CheckoutPage extends TestBase {

    @FindBy(css = ".passengers-form .body")
    private WebElement passengerForm;
    @FindBy(css = ".payment-passenger-title .core-select select")
    private List<WebElement> titleDDMs;
    @FindBy(css = ".payment-passenger-first-name input")
    private List<WebElement> firstNames;
    @FindBy(css = ".payment-passenger-last-name input")
    private List<WebElement> lastNames;
    @FindBy(css = "[name=\"phoneNumberCountry\"]")
    private WebElement countryPhoneDDM;
    @FindBy(css = ".phone-number input")
    private WebElement phoneInput;

    @FindBy(css = "payment-method-card > div > input:nth-child(2)")
    private WebElement ccNoInput;
    @FindBy(css = ".expiry-month-select")
    private WebElement expiryMonthDDM;
    @FindBy(css = ".expiry-year-select")
    private WebElement expiryYearDDM;
    @FindBy(css = ".card-security-code input")
    private WebElement cvvInput;
    @FindBy(css = ".cardholders-name input")
    private WebElement cardholderInput;

    @FindBy(id = "billingAddressAddressLine1")
    private WebElement address1Input;
    @FindBy(id = "billingAddressCity")
    private WebElement cityInput;
    @FindBy(id = "billingAddressPostcode")
    private WebElement zipInput;
    @FindBy(id = "billingAddressCountry")
    private WebElement countryDDM;
    @FindBy(css = ".terms core-icon")
    private WebElement termsAndConditionsCheckbox;
    @FindBy(css = ".core-btn-primary.core-btn-medium")
    private WebElement payNowButton;
    @FindBy(css = "[text-title=\"common.components.payment_forms.error_title\"] .info-text")
    private WebElement errorMessageText;
    @FindBy(css = ".box .plane-spinner")
    private WebElement processingAnimation;

    public boolean isLoaded() {
        return passengerForm.isDisplayed();
    }

    public void providePersonalData(Data firstName, Data lastName) {
        log.info("Provide personal data");
        for (int i = 0; i < firstNames.size(); i++) {
            firstNames.get(i).sendKeys(firstName.getData());
            lastNames.get(i).sendKeys(lastName.getData());
        }
        for (int i = 0; i < titleDDMs.size(); i++) {
            Select titleDDM = new Select(titleDDMs.get(i));
            titleDDM.selectByVisibleText("Mrs");
        }
    }

    public void providePhoneData(Data phoneCountry, Data phoneNo) {
        log.info("Provide phone data");
        Select ddm = new Select(countryPhoneDDM);
        ddm.selectByVisibleText(phoneCountry.getData());
        phoneInput.sendKeys(phoneNo.getData());
    }

    public void provideAddressData(Data address1, Data city, Data zipCode, Data country) {
        log.info("Provide address data");
        address1Input.sendKeys(address1.getData());
        cityInput.sendKeys(city.getData());
        zipInput.sendKeys(zipCode.getData());
        Select ddm = new Select(countryDDM);
        ddm.selectByVisibleText(country.getData());

    }

    public void provideCCData(String ccNo, String expiryMonth, String expiryYear, String cvv) {
        log.info("Provide CC data");
        ccNoInput.sendKeys(ccNo.replace(" ", ""));
        Select select = new Select(expiryMonthDDM);
        select.selectByVisibleText(String.valueOf(Integer.valueOf(expiryMonth)));
        select = new Select(expiryYearDDM);
        select.selectByVisibleText("20" + expiryYear);
        cvvInput.sendKeys(cvv);
        String firstName = getRandomData("passenger_first_names");
        String lastName = getRandomData("passenger_last_names");
        cardholderInput.sendKeys(firstName + " " + lastName);

    }

    public void acceptTerms() {
        log.info("Accept terms");
        termsAndConditionsCheckbox.click();
    }

    public void clickPayNow() {
        log.info("Click pay now");
        payNowButton.click();
    }

    public String getErrorMessage() {
        log.info("Get error message");
        getCustomWait(10).until(ExpectedConditions.invisibilityOfAllElements(Arrays.asList(processingAnimation)));
        return errorMessageText.getText();
    }
}
