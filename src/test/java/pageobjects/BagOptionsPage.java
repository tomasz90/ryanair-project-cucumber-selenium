package pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.Arrays;

public class BagOptionsPage extends TestBase {

    @FindBy(css="priority-cabin-bag-card:nth-child(1) .pb-cb-standalone-card") private WebElement smallBagOption;
    @FindBy(css=".cart .trips-basket.trips-cnt") private WebElement continueButton;
    @FindBy(css="[translate=\"trips.booking.priority.same-for-all.yes\"]") private WebElement theSameButton;

    public boolean isLoaded() {
        return smallBagOption.isDisplayed();
    }
    public void chooseSmallBag(){
        smallBagOption.click();
    }

    public void ifPopUpDisplayedChooseTheSameForAll(){
        if(theSameButton!=null){
           theSameButton.click();
        }
    }

    public void clickContinue(){
        getWait().until(ExpectedConditions.invisibilityOfAllElements(Arrays.asList(theSameButton)));
        continueButton.click();
    }
}
