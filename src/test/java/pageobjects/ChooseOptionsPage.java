package pageobjects;

import lombok.extern.log4j.Log4j;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@Log4j
public class ChooseOptionsPage extends TestBase {

    @FindBy(css = ".seat-map-header") private WebElement seatDialog;
    @FindBy(css = ".trips-basket.trips-cnt") private WebElement checkoutButton;
    @FindBy(css = "[class=\"core-btn-primary dialog-overlay-footer__ok-button\"] span:nth-child(2)") private WebElement confirmSeatsButton;
    @FindBy(css = "[class=\"seat-row-seat standard\"]") private List<WebElement> standardSeats;
    @FindBy(css = "[ng-switch-when=\"mandatory-seats\"] button") private WebElement popUpConfirmButton;
    @FindBy(css = "[translate=\"trips.seats.seatmap_review-seats\"]") private WebElement reviewSeatsButton;
    @FindBy(css = ".popup-msg__button.popup-msg__button--cancel") private WebElement noThanksButton;
    @FindBy(css = ".seat-map-prompt-backdrop") private WebElement seatMapPrompt;
    @FindBy(css = ".confirm-seats-title") private WebElement confirmSeatTitle;
    @FindBy(css = ".seat-map-scrolling-body .seat-map") private WebElement scrollingArea;

    public boolean isLoaded() {
        return seatDialog.isDisplayed();
    }

    public void ifPopUpDisplayedDismiss() {
        log.info("If popup displayed, dismiss");
        if (popUpConfirmButton != null) {
            popUpConfirmButton.click();
        }
    }

    public void chooseRandomSeats(HashMap<String, Integer> passengersInfo) {
        log.info("Choose random seats");
        getWait().until(ExpectedConditions.invisibilityOfAllElements(Arrays.asList(seatMapPrompt)));
        int paidSeats = passengersInfo.get("adults");
        int lastSeat = standardSeats.size()-1;
        for (int seat = lastSeat; seat > lastSeat - paidSeats; seat--) {
            standardSeats.get(seat).click();
        }
    }

    public void reviewSeats() {
        log.info("Review seats");
        reviewSeatsButton.click();
    }

    public void confirmSeats() {
        log.info("Confirm seats");
        getWait().until(ExpectedConditions.invisibilityOfAllElements(Arrays.asList(reviewSeatsButton)));
        confirmSeatsButton.click();
    }

    public void clickCheckout() {
        log.info("Click checkout");
        getCustomWait(5).until(ExpectedConditions.invisibilityOfAllElements(Arrays.asList(confirmSeatTitle)));
        checkoutButton.click();
    }

    public void ifCarHireOptionDisplayedDismiss() {
        log.info("If car hire popup displayed, dismiss");
        if (noThanksButton != null) {
            noThanksButton.click();
        }
    }
}
