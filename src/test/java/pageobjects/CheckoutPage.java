package pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.util.*;

public class CheckoutPage extends TestBase {

    @FindBy(css=".passengers-form .body") private WebElement passengerForm;
    @FindBy(css=".payment-passenger-title .core-select select") private List<WebElement> titleDDMs;
    @FindBy(css=".payment-passenger-first-name input") private List<WebElement> firstNames;
    @FindBy(css=".payment-passenger-last-name input") private List<WebElement> lastNames;
    @FindBy(css="[name=\"phoneNumberCountry\"]") private WebElement countryPhoneDDM;
    @FindBy(css=".phone-number input") private WebElement phoneInput;

    @FindBy(css="payment-method-card > div > input:nth-child(2)")private WebElement ccNoInput;
    @FindBy(css=".expiry-month-select")private WebElement expiryMonthDDM;
    @FindBy(css=".expiry-year-select")private WebElement expiryYearDDM;
    @FindBy(css=".card-security-code input")private WebElement cvvInput;
    @FindBy(css=".cardholders-name input")private WebElement cardholderInput;

    @FindBy(id="billingAddressAddressLine1")private WebElement address1Input;
    @FindBy(id="billingAddressCity")private WebElement cityInput;
    @FindBy(id="billingAddressPostcode")private WebElement zipInput;
    @FindBy(id="billingAddressCountry")private WebElement countryDDM;
    @FindBy(css=".terms core-icon")private WebElement termsAndConditionsCheckbox;
    @FindBy(css=".core-btn-primary.core-btn-medium")private WebElement payNowButton;
    @FindBy(css="[text-title=\"common.components.payment_forms.error_title\"] .info-text")private WebElement errorMessageText;
    @FindBy(css=".box .plane-spinner")private WebElement processingAnimation;

    public boolean isLoaded() {
        return passengerForm.isDisplayed();
    }

    public void providePersonalData(HashMap<String, Integer> map) {

        for (int i = 0; i < firstNames.size(); i++) {
            firstNames.get(i).sendKeys(getRandomData("passenger_first_names"));
            lastNames.get(i).sendKeys(getRandomData("passenger_last_names"));
        }

        for (int i = 0; i < titleDDMs.size(); i++) {
            Select titleDDM = new Select(titleDDMs.get(i));
            titleDDM.selectByVisibleText("Mrs");
        }
    }

    public void providePhoneData() {
        Select ddm = new Select(countryPhoneDDM);
        ddm.selectByVisibleText(getRandomData("phone_country"));
        phoneInput.sendKeys(getRandomData("phone_no"));
    }

    public void provideAddressData() {
        address1Input.sendKeys(getRandomData("address_1"));
        cityInput.sendKeys(getRandomData("city"));
        zipInput.sendKeys(getRandomData("zip_code"));
        Select ddm = new Select(countryDDM);
        ddm.selectByVisibleText(getRandomData("country"));

    }

    public void provideCCData(String ccNo, String expiryMonth, String expiryYear, String cvv) {
        ccNoInput.sendKeys(ccNo.replace(" ", ""));
        Select select = new Select(expiryMonthDDM);
        select.selectByVisibleText(String.valueOf(Integer.valueOf(expiryMonth)));
        select = new Select(expiryYearDDM);
        select.selectByVisibleText("20" + expiryYear);
        cvvInput.sendKeys(cvv);
        cardholderInput.sendKeys("test name");

    }

    public void acceptTerms() {
        termsAndConditionsCheckbox.click();
    }

    public String getErrorMessage() {
        getCustomWait(10).until(ExpectedConditions.invisibilityOfAllElements(Arrays.asList(processingAnimation)));
        return errorMessageText.getText();
    }

    public void clickPayNow() {
        payNowButton.click();
    }

    private String getRandomData(String key) {
        List<String> dataList = new ArrayList<>();
        String data = getTestProperties().getProperty(key);
        while (data.contains(",")) {
            int commaPlace = data.indexOf(",");
            dataList.add(data.substring(0, commaPlace));
            data = data.substring(commaPlace+1);
        }
        dataList.add(data);
        Collections.shuffle(dataList);
        return dataList.get(0);
    }
}
