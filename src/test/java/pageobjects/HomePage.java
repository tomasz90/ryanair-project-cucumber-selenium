package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.HashMap;

public class HomePage extends TestBase {

    @FindBy(css=".smart-search.container") private WebElement searchContainer;
    @FindBy(css="form [value=one-way]") private WebElement oneWayRadio;
    @FindBy(css=".route-selector-departure .disabled-wrap input") private WebElement fromInput;
    @FindBy(css=".route-selector-destination .disabled-wrap input") private WebElement toInput;
    @FindBy(css="[name=passengers] .dropdown-handle") private WebElement passengerDDM;
    @FindBy(css=".start-date .arrow.right") private WebElement nextMonthButton;
    @FindBy(css="#myryanair-auth-login .username") private WebElement loginButton;
    @FindBy(css="[value=\"paxInput.adults\"] input") private WebElement adultInput;
    @FindBy(css="[value=\"paxInput.children\"] input") private WebElement childInput;
    @FindBy(css=".terms-conditions-checkbox-span") private WebElement termsOfUseCheckbox;
    @FindBy(css=".col-flight-search-right button:nth-child(2)") private WebElement letsFlyButton;
    @FindBy(css=".avatar-user") private WebElement profileIcon;
    @FindBy(css="#cookie-popup") private WebElement cookiePopUp;
    @FindBy(css=".cookie-popup__close") private WebElement closePopUpButton;

    private static String dateID = "[data-id=\"DATE\"] span";
    private int adults;
    private int teens;
    private int children;
    private int infants;

    public void navigate() {
        getDriver().navigate().to(getTestProperties().getProperty("test_url"));
    }
    public boolean isLoaded() {
        return searchContainer.isDisplayed();
    }

    public void clickOneWay() {
        oneWayRadio.click();
    }

    public void setFrom(String from) {
        fromInput.clear();
        fromInput.sendKeys(from);
        fromInput.sendKeys(Keys.ENTER);
    }

    public void setTo(String to) {
        toInput.clear();
        toInput.sendKeys(to);
        toInput.sendKeys(Keys.ENTER);
    }

    public void setDate(String date) throws Exception {
        dateID = dateID.replace("DATE", date);
        WebElement nextMonth = nextMonthButton;
        WebElement myData;
        for (int months = 0; months < 12; months++) {
            if ((myData = findElementQuietly(By.cssSelector(dateID), 3)) != null) {
                waitForBeingReady();
                myData.click();
                return;
            }
            nextMonth.click();
        }
        throw new Exception("Couldn't chose specified date. Probably date out of range.");
    }

    public void clickPassengerDDM() {
        passengerDDM.click();
    }

    public void setAdults(String adults) {
        WebElement element = getWait().until(ExpectedConditions.visibilityOf(adultInput));
        element.clear();
        element.sendKeys(adults);
        getWait().until(ExpectedConditions.attributeToBe(adultInput, "value", adults));
    }

    public void acceptTermsOfUse() {
        termsOfUseCheckbox.click();
    }

    public void clickLetsFly() {
        letsFlyButton.click();
    }

    public void setChild(String child) {
        childInput.sendKeys(child);
        getWait().until(ExpectedConditions.attributeToBe(childInput, "value", child));

    }

    public void clickLogin() {
        loginButton.click();
    }

    public boolean isLoggedIn() {
        return profileIcon.isDisplayed();
    }

    public void ifCookiePopUpDisplayedDismissIt() {
        if (cookiePopUp != null) {
            closePopUpButton.click();
        }
    }

    public void savePassengerInfo(int adults, int teens, int children, int infants) {
        this.adults = adults;
        this.teens = teens;
        this.children = children;
        this.infants = infants;
    }

    public HashMap<String, Integer> getPassengerInfo() {
        HashMap<String, Integer> map = new HashMap<>();
        map.put("adults", adults);
        map.put("teens", teens);
        map.put("children", children);
        map.put("infants", infants);
        return map;
    }
}
