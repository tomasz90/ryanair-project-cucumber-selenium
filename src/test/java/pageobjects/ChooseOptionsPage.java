package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class ChooseOptionsPage extends TestBase {
    private static final By seatDialog = By.cssSelector(".seat-map-header");
    private static final By randomSeatButton = By.cssSelector("[translate=\"trips.seats.modal.skip\"]");
    private static final By checkoutButton = By.cssSelector(".trips-basket.trips-cnt");
    private static final By rightPane = By.id("dialog-body-slot");
    private static final By confirmSeatsButton = By.cssSelector("[class=\"core-btn-primary dialog-overlay-footer__ok-button\"] span:nth-child(2)");
    private static final By standardSeats = By.cssSelector("[class=\"seat-row-seat standard\"]");
    private static final By passengers = By.cssSelector("core-carousel-slide");
    private static final By popUpConfirmButton = By.cssSelector("[ng-switch-when=\"mandatory-seats\"] button");
    private static final By reviewSeatsButton = By.cssSelector("[translate=\"trips.seats.seatmap_review-seats\"]");
    private static final By noThanksButton = By.cssSelector(".popup-msg__button.popup-msg__button--cancel");
    private static final By seatsLegend = By.cssSelector(".sm-legend");
    private static final By seatMapPrompt = By.cssSelector(".seat-map-prompt-backdrop");
    private static final By confirmSeatsTitle = By.cssSelector(".confirm-seats-title");

    public boolean isLoaded() {
        return getDriver().findElement(seatDialog).isDisplayed();
    }

    public void ifPopUpDisplayedDismiss() {
        WebElement element;
        if ((element = findElementQuietly(popUpConfirmButton)) != null) {
            element.click();
        }
    }

    public void chooseRandomSeats(HashMap<String, Integer> map) {
        List<WebElement> standardSeatsElements = getDriver().findElements(standardSeats);
        Collections.reverse(standardSeatsElements);
        getWait().until(ExpectedConditions.invisibilityOfElementLocated(seatMapPrompt));
        int paidSeats = map.get("adults");
        for (int seat = 0; seat < paidSeats; seat++) {
            WebElement element = standardSeatsElements.get(seat);
            element.click();
        }
    }

    public void reviewSeats(){
        getDriver().findElement(reviewSeatsButton).click();
    }

    public void confirmSeats() {
        getWait().until(ExpectedConditions.invisibilityOfElementLocated(reviewSeatsButton));
        getDriver().findElement(confirmSeatsButton).click();
    }

    public void clickCheckout()  {
        getWait().until(ExpectedConditions.invisibilityOfElementLocated(confirmSeatsTitle));
        getDriver().findElement(checkoutButton).click();
    }

    public void ifCarHireOptionDisplayedDismiss(){
        WebElement element;
        if((element = findElementQuietly(noThanksButton))!=null)
        element.click();
    }
}
