package pageobjects;

import lombok.extern.log4j.Log4j;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.Arrays;

@Log4j
public class BagOptionsPage extends TestBase {

    @FindBy(css = "priority-cabin-bag-card:nth-child(1) .pb-cb-standalone-card")
    private WebElement smallBagOption;
    @FindBy(css = ".cart .trips-basket.trips-cnt")
    private WebElement continueButton;
    @FindBy(css = "[translate=\"trips.booking.priority.same-for-all.yes\"]")
    private WebElement theSameButton;

    public boolean isLoaded() {
        return smallBagOption.isDisplayed();
    }

    public void chooseSmallBag() {
        log.info("Choose small bag");
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
