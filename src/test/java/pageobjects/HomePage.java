package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import utilities.Utilities;

public class HomePage extends TestBase {

    private static final By oneWayRadio = By.cssSelector("form [value=one-way]");
    private static final By fromInput = By.cssSelector(".route-selector-departure .disabled-wrap input");
    private static final By toInput = By.cssSelector(".route-selector-destination .disabled-wrap input");
    private static final By onDayInput = By.cssSelector(".container-from .disabled-wrap.date-input [name=dateInput0]");
    private static final By onMonthInput = By.cssSelector(".container-from .disabled-wrap.date-input [name=dateInput1]");
    private static final By onYearInput = By.cssSelector(".container-from .disabled-wrap.date-input [name=dateInput2]");
    private static final By passengerDDM = By.cssSelector("[name=passengers] .dropdown-handle");


    public void navigate() {
        getDriver().navigate().to(webConfig.getProperty("test_url"));
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

    public void setDate(String date) {
        overrideDate(onYearInput, "2019");
        overrideDate(onMonthInput, "08");
        overrideDate(onDayInput, "20");
    }

    public void clickPassengerDDM(){
        getDriver().findElement(passengerDDM);
    }

    public void setAdults(int adults) {


    }

    public void setChild(int child) {

    }

    private void overrideDate(By dateInput, String partDate){
        WebElement dateElement = getDriver().findElement(dateInput);
        dateElement.click();
        dateElement.sendKeys(Keys.DELETE);
        dateElement.sendKeys(Keys.DELETE);
        dateElement.sendKeys(partDate);
        dateElement.sendKeys(Keys.ENTER);
    }
}
