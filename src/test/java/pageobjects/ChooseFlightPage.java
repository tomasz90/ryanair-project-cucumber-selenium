package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ChooseFlightPage extends TestBase {

    private static final By calendarCarousel = By.cssSelector(".base-carousel");
    private static final By priceButton = By.cssSelector(".flight-header__min-price .flights-table-price");
    private static final By standardFare = By.cssSelector(".flights-table-fares > div > div:nth-child(1) flights-table-price");
    private static final By continueButton = By.cssSelector(".cart .trips-basket.trips-cnt");
    private static final By addedToBasketPopUp = By.cssSelector(".basket-message__popup");
    private static final By spinningPlane = By.cssSelector(".flights-table-fares .plane-spinner");

    public boolean isLoaded() {
        return getDriver().findElement(calendarCarousel).isDisplayed();
    }

    public void chooseDisplayedFlight() {
        getDriver().findElement(priceButton).click();
    }

    public void chooseStandardFare() {
        //getWait().until(ExpectedConditions.invisibilityOfElementLocated(priceButton));
        getWait().until(ExpectedConditions.invisibilityOfElementLocated(spinningPlane));
        WebElement element = getDriver().findElement(standardFare);
        element.click();
    }

    public void clickContinue() {
        getWait().until(ExpectedConditions.visibilityOfElementLocated(addedToBasketPopUp));
        getDriver().findElement(continueButton).click();
    }
}
