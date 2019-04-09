package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class CheckoutPage extends TestBase {

    private static final By passengerForm = By.cssSelector(".passengers-form .body");
    private static final By titleDDMs = By.cssSelector(".payment-passenger-title .core-select select");
    private static final By firstName = By.cssSelector(".payment-passenger-first-name input");
    private static final By lastName = By.cssSelector(".payment-passenger-last-name input");
    private static final By countryPhoneDDM = By.cssSelector("[name=\"phoneNumberCountry\"]");
    private static final By phoneInput = By.cssSelector(".phone-number input");

    private static final By ccNoInput = By.cssSelector("payment-method-card > div > input:nth-child(2)");
    private static final By expiryMonthDDM = By.cssSelector(".expiry-month-select");
    private static final By expiryYearDDM = By.cssSelector(".expiry-year-select");

    private static final By cvvInput = By.cssSelector(".card-security-code input");
    private static final By cardholderInput = By.cssSelector(".cardholders-name input");


    private static final By address1Input = By.id("billingAddressAddressLine1");
    private static final By cityInput = By.id("billingAddressCity");
    private static final By zipInput = By.id("billingAddressPostcode");
    private static final By countryDDM = By.id("billingAddressCountry");
    private static final By termsAndConditionsCheckbox = By.cssSelector(".terms core-icon");
    private static final By payNowButton = By.cssSelector(".core-btn-primary.core-btn-medium");
    private static final By errorMessageText = By.cssSelector("[text-title=\"common.components.payment_forms.error_title\"] .info-text");
    private static final By processingAnimation = By.cssSelector(".box .plane-spinner");

    public boolean isLoaded() {
        return getDriver().findElement(passengerForm).isDisplayed();
    }

    public void providePersonalData(HashMap<String, Integer> map) {

        List<WebElement> ddms = getDriver().findElements(titleDDMs);
        List<WebElement> firstNames = getDriver().findElements(firstName);
        List<WebElement> lastNames = getDriver().findElements(lastName);

        for (int i = 0; i < firstNames.size(); i++) {
            firstNames.get(i).sendKeys(getRandomData("passenger_first_names"));
            lastNames.get(i).sendKeys(getRandomData("passenger_last_names"));
        }

        for (int i = 0; i < ddms.size(); i++) {
            Select titleDDM = new Select(ddms.get(i));
            titleDDM.selectByVisibleText("Mrs");
        }
    }

    public void providePhoneData() {
        Select ddm = new Select(getDriver().findElement(countryPhoneDDM));
        ddm.selectByVisibleText(getRandomData("phone_country"));
        getDriver().findElement(phoneInput).sendKeys(getRandomData("phone_no"));
    }

    public void provideAddressData() {
        getDriver().findElement(address1Input).sendKeys(getRandomData("address_1"));
        getDriver().findElement(cityInput).sendKeys(getRandomData("city"));
        getDriver().findElement(zipInput).sendKeys(getRandomData("zip_code"));
        Select ddm = new Select(getDriver().findElement(countryDDM));
        ddm.selectByVisibleText(getRandomData("country"));

    }

    public void provideCCData(String ccNo, String expiryMonth, String expiryYear, String cvv) {
        getDriver().findElement(ccNoInput).sendKeys(ccNo.replace(" ", ""));
        Select select = new Select(getDriver().findElement(expiryMonthDDM));
        select.selectByVisibleText(String.valueOf(Integer.valueOf(expiryMonth)));
        select = new Select(getDriver().findElement(expiryYearDDM));
        select.selectByVisibleText("20" + expiryYear);
        getDriver().findElement(cvvInput).sendKeys(cvv);
        getDriver().findElement(cardholderInput).sendKeys("test name");

    }

    public void acceptTerms() {
        getDriver().findElement(termsAndConditionsCheckbox).click();
    }

    public String getErrorMessage() {
        getCustomWait(10).until(ExpectedConditions.invisibilityOfElementLocated(processingAnimation));
        return getDriver().findElement(errorMessageText).getText();
    }

    public void clickPayNow() {
        getDriver().findElement(payNowButton).click();
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
