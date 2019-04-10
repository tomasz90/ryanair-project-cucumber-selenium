package pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.Arrays;

public class ChooseFlightPage extends TestBase {

    @FindBy(css=".base-carousel") private WebElement  calendarCarousel;
    @FindBy(css=".flight-header__min-price .flights-table-price") private WebElement   priceButton;
    @FindBy(css=".flights-table-fares > div > div:nth-child(1) flights-table-price") private WebElement standardFare;
    @FindBy(css=".cart .trips-basket.trips-cnt") private WebElement continueButton;
    @FindBy(css=".basket-message__popup") private WebElement addedToBasketPopUp;
    @FindBy(css=".flights-table-fares .plane-spinner") private WebElement spinningPlane;

    public boolean isLoaded() {
        return calendarCarousel.isDisplayed();
    }

    public void chooseDisplayedFlight() {
        priceButton.click();
    }

    public void chooseStandardFare() {
        getWait().until(ExpectedConditions.invisibilityOfAllElements(Arrays.asList(spinningPlane)));
        WebElement element = standardFare;
        element.click();
    }

    public void clickContinue() {
        getWait().until(ExpectedConditions.visibilityOf(addedToBasketPopUp));
        continueButton.click();
    }
}
