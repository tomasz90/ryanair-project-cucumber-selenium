package pageobjects;

import lombok.extern.log4j.Log4j;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Log4j
public class ChooseOptionsPage extends TestBase {


    @FindBy(css = ".popup-msg__button.popup-msg__button--cancel")
    private WebElement noThanksButton;
    @FindBy(css = ".trips-basket.trips-cnt")
    private WebElement checkoutButton;
    @FindBy(css = ".confirm-seats-title")
    private WebElement confirmSeatTitle;


    public void clickCheckout() {
        log.info("Click checkout");
        checkoutButton.click();
    }

    public void ifCarHireOptionDisplayedDismiss() {
        log.info("If car hire popup displayed, dismiss");
        if (noThanksButton != null) {
            noThanksButton.click();
        }
    }
}
