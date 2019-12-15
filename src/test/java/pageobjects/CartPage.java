package pageobjects;

import lombok.extern.log4j.Log4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Log4j public class CartPage extends TestBase {

    @FindBy(css = ".cart_navigation.clearfix .button") private WebElement proceedButton;

    @FindBy(id = "cgv") private WebElement agreeTermsCheckbox;

    @FindBy(className = "bankwire") private WebElement bankWireButton;

    @FindBy(className = "cheque") private WebElement chequeButton;

    @FindBy(id = "order-confirmation") private WebElement confirmationLabel;

    private By shoppingCartLabel = By.id("cart_title");

    @Override public boolean isLoaded() {
        return isPresent(shoppingCartLabel);
    }

    public void proceedCheckout() {
        log.info("Proceed checkout");
        proceedButton.click();
    }

    public void agreeTermsOfService() {
        log.info("Agree terms of service");
        agreeTermsCheckbox.click();
    }

    public void chosePayment(String payment) {
        log.info("Chose payment: " + payment);
        switch (payment) {
            case "bankwire":
                bankWireButton.click();
                break;
            case "cheque":
                chequeButton.click();
                break;
        }
    }

    public boolean isOrderComplete() {
        log.info("Check if order is complete");
        return confirmationLabel.isDisplayed();
    }
}
