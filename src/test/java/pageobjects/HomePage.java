package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

public class HomePage extends TestBase {

    private static final By oneWayRadio = By.cssSelector("form [value=one-way]");
    private static final By fromInput = By.cssSelector(".route-selector-departure .disabled-wrap input");
    private static final By toInput = By.cssSelector(".route-selector-destination .disabled-wrap input");
    private static String dateID = "[data-id=\"DATE\"] span";
    private static final By passengerDDM = By.cssSelector("[name=passengers] .dropdown-handle");
    private static final By nextMonthButton = By.cssSelector(".start-date .arrow.right");
    private static final By loginButton = By.cssSelector("#myryanair-auth-login .username");
    private static final By adultIncreaseButton = By.cssSelector("[value=paxInput.adults] [ng-click=$ctrl.increment()]");
    private static final By adultInput = By.cssSelector("[value=\"paxInput.adults\"] input");
    private static final By childIncreaseButton = By.cssSelector("[value=paxInput.children] [ng-click=$ctrl.increment()]");
    private static final By childInput = By.cssSelector("[value=\"paxInput.children\"] input");
    private static final By termsOfUseCheckbox = By.cssSelector(".terms-conditions-checkbox-span");
    private static final By letsFlyButton = By.cssSelector(".col-flight-search-right button:nth-child(2)");

    public void navigate() {
        getDriver().navigate().to(getWebConfig().getProperty("test_url"));
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
        dateID = dateID.replace("DATE", "05-05-2019");
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
        WebElement element = getDriver().findElement(adultInput);
        element.clear();
        element.sendKeys(adults);
    }

    public void acceptTermsOfUse(){
        getDriver().findElement(termsOfUseCheckbox).click();
    }

    public void clickLetsFly(){
        getDriver().findElement(letsFlyButton).click();
    }

    public void setChild(String child) {
        getDriver().findElement(childInput).sendKeys(child);
    }

    public void clickLogin() {
        getDriver().findElement(loginButton).click();
    }
}
