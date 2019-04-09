package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.HashMap;

public class HomePage extends TestBase {

    private static final By searchContainer = By.cssSelector(".smart-search.container");
    private static final By oneWayRadio = By.cssSelector("form [value=one-way]");
    private static final By fromInput = By.cssSelector(".route-selector-departure .disabled-wrap input");
    private static final By toInput = By.cssSelector(".route-selector-destination .disabled-wrap input");
    private static final By passengerDDM = By.cssSelector("[name=passengers] .dropdown-handle");
    private static final By nextMonthButton = By.cssSelector(".start-date .arrow.right");
    private static final By loginButton = By.cssSelector("#myryanair-auth-login .username");
    private static final By adultInput = By.cssSelector("[value=\"paxInput.adults\"] input");
    private static final By childInput = By.cssSelector("[value=\"paxInput.children\"] input");
    private static final By termsOfUseCheckbox = By.cssSelector(".terms-conditions-checkbox-span");
    private static final By letsFlyButton = By.cssSelector(".col-flight-search-right button:nth-child(2)");
    private static final By profileIcon = By.cssSelector(".avatar-user");
    private static final By cookiePopUp = By.id("cookie-popup");
    private static final By closePopUpButton = By.cssSelector(".cookie-popup__close");
    private static String dateID = "[data-id=\"DATE\"] span";
    private int adults;
    private int teens;
    private int children;
    private int infants;

    public void navigate() {
        getDriver().navigate().to(getTestProperties().getProperty("test_url"));
    }

    public boolean isLoaded() {
        return getDriver().findElement(searchContainer).isDisplayed();
    }

    public void clickOneWay() {
        getDriver().findElement(oneWayRadio).click();
    }

    public void setFrom(String from) {
        getDriver().findElement(fromInput).clear();
        getDriver().findElement(fromInput).sendKeys(from);
        getDriver().findElement(fromInput).sendKeys(Keys.ENTER);
    }

    public void setTo(String to) {
        getDriver().findElement(toInput).clear();
        getDriver().findElement(toInput).sendKeys(to);
        getDriver().findElement(toInput).sendKeys(Keys.ENTER);
    }

    public void setDate(String date) throws Exception {
        dateID = dateID.replace("DATE", date);
        WebElement nextMonth = getDriver().findElement(nextMonthButton);
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
        getDriver().findElement(passengerDDM).click();
    }

    public void setAdults(String adults) {
        WebElement element = getWait().until(ExpectedConditions.visibilityOfElementLocated(adultInput));
        element.clear();
        element.sendKeys(adults);
        getWait().until(ExpectedConditions.attributeToBe(adultInput, "value", adults));
    }

    public void acceptTermsOfUse() {
        getDriver().findElement(termsOfUseCheckbox).click();
    }

    public void clickLetsFly() {
        getDriver().findElement(letsFlyButton).click();
    }

    public void setChild(String child) {
        getDriver().findElement(childInput).sendKeys(child);
        getWait().until(ExpectedConditions.attributeToBe(childInput, "value", child));

    }

    public void clickLogin() {
        getDriver().findElement(loginButton).click();
    }

    public boolean isLoggedIn() {
        return getDriver().findElement(profileIcon).isDisplayed();
    }

    public void ifCookiePopUpDisplayedDismissIt() {
        if ((findElementQuietly(cookiePopUp) != null)) {
            getDriver().findElement(closePopUpButton).click();
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
