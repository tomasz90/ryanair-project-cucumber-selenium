package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class BagOptionsPage extends TestBase {

    private static final By smallBagOption = By.cssSelector("priority-cabin-bag-card:nth-child(1) .pb-cb-standalone-card");
    private static final By continueButton = By.cssSelector(".cart .trips-basket.trips-cnt");
    private static final By theSameButton = By.cssSelector("[translate=\"trips.booking.priority.same-for-all.yes\"]");

    public boolean isLoaded() {
        return getDriver().findElement(smallBagOption).isDisplayed();
    }
    public void chooseSmallBag(){
        getDriver().findElement(smallBagOption).click();
    }

    public void ifPopUpDisplayedChooseTheSameForAll(){
        WebElement element;
        if((element = findElementQuietly(theSameButton))!=null){
            element.click();
        }
    }

    public void clickContinue(){
        getWait().until(ExpectedConditions.invisibilityOfElementLocated(theSameButton));
        getDriver().findElement(continueButton).click();
    }
}
