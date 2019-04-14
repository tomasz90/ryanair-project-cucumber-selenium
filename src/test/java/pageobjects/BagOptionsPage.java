package pageobjects;

import lombok.extern.log4j.Log4j;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@Log4j
public class BagOptionsPage extends TestBase {

    @FindBy(css = "priority-cabin-bag-card:nth-child(1) .pb-cb-standalone-card")
    private WebElement smallBagOption;
    @FindBy(css = ".cart .trips-basket.trips-cnt")
    private WebElement continueButton;
    @FindBy(css = "[translate=\"trips.booking.priority.same-for-all.yes\"]")
    private WebElement theSameButton;
    @FindBy(css = ".seat-map-header")
    private WebElement seatDialog;
    @FindBy(css = "[class=\"core-btn-primary dialog-overlay-footer__ok-button\"] span:nth-child(2)")
    private WebElement confirmSeatsButton;
    @FindBy(css = "[class=\"seat-row-seat standard\"]")
    private List<WebElement> standardSeats;
    @FindBy(css = "[ng-switch-when=\"mandatory-seats\"] button")
    private WebElement popUpConfirmButton;
    @FindBy(css = "[translate=\"trips.seats.seatmap_review-seats\"]")
    private WebElement reviewSeatsButton;
    @FindBy(css = ".seat-map-prompt-backdrop")
    private WebElement seatMapPrompt;
    @FindBy(css = ".confirm-seats-title")
    private WebElement confirmSeatTitle;


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

    public void chooseSmallBag() {
        log.info("Choose small bag");
        getCustomWait(5).until(ExpectedConditions.invisibilityOfAllElements(Arrays.asList(confirmSeatTitle)));
        smallBagOption.click();
    }

    public void ifPopUpDisplayedChooseTheSameForAll() {
        log.info("If popup displayed choose same bag for all");
        if (theSameButton != null) {
            theSameButton.click();
        }
    }

    public void clickContinue() {
        log.info("Click continue");
        getWait().until(ExpectedConditions.invisibilityOfAllElements(Arrays.asList(theSameButton)));
        continueButton.click();
    }
}
