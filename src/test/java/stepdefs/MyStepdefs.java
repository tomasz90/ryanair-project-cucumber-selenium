package stepdefs;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.support.PageFactory;
import pageobjects.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static pageobjects.TestBase.getDriver;
import static pageobjects.TestBase.getTestProperties;

public class MyStepdefs {

    private HomePage homePage = PageFactory.initElements(getDriver(), HomePage.class);
    private LoginPopUp loginPopUp = PageFactory.initElements(getDriver(), LoginPopUp.class);
    private ChooseFlightPage chooseFlightPage = PageFactory.initElements(getDriver(), ChooseFlightPage.class);
    private BagOptionsPage bagOptionsPage = PageFactory.initElements(getDriver(), BagOptionsPage.class);
    private ChooseOptionsPage chooseOptionsPage = PageFactory.initElements(getDriver(), ChooseOptionsPage.class);
    private CheckoutPage checkoutPage = PageFactory.initElements(getDriver(), CheckoutPage.class);

    @When("I navigate to home page and login into test account")
    public void iNavigateToHomePage() {
        homePage.navigate();
        homePage.ifCookiePopUpDisplayedDismissIt();
        homePage.clickLogin();
        loginPopUp.enterEmail(getTestProperties().getProperty("email"));
        loginPopUp.enterPassword(getTestProperties().getProperty("password"));
        loginPopUp.submit();
    }

    @Then("I should be logged in")
    public void iShouldBeLoggedIn() {
        assertTrue("User wasn't logged in", homePage.isLoggedIn());
    }

    @And("I perform searching for flight from (.+) to (.+) on ([0,1,2,3]\\d-[0-1]\\d-[2][0][1,2][9,0,1]) for (\\d) adults and (\\d) child")
    public void iPerformSearchingForFlightFromToOnForAdultsAndChild(String from, String to, String on, int adults, int children) throws Exception {
        homePage.savePassengerInfo(adults, 0, children, 0);
        homePage.clickOneWay();
        homePage.setFrom(from);
        homePage.setTo(to);
        homePage.setDate(on);
        homePage.clickPassengerDDM();
        homePage.setAdults(String.valueOf(adults));
        homePage.setChild(String.valueOf(children));
        homePage.clickPassengerDDM();
        homePage.acceptTermsOfUse();
        homePage.clickLetsFly();
    }

    @And("I choose the following options: flight with standard fare, small bags, random seats")
    public void iChooseTheFollowingOptionsFlightWithStandardFareSmallBagsRandomSeats() {
        chooseFlightPage.chooseDisplayedFlight();
        chooseFlightPage.chooseStandardFare();
        chooseFlightPage.clickContinue();
        chooseOptionsPage.ifPopUpDisplayedDismiss();
        chooseOptionsPage.chooseRandomSeats(homePage.getPassengerInfo());
        chooseOptionsPage.reviewSeats();
        chooseOptionsPage.confirmSeats();
        chooseOptionsPage.clickCheckout();
        bagOptionsPage.chooseSmallBag();
        bagOptionsPage.ifPopUpDisplayedChooseTheSameForAll();
        bagOptionsPage.clickContinue();
        chooseOptionsPage.clickCheckout();
        chooseOptionsPage.ifCarHireOptionDisplayedDismiss();
    }

    @Then("I should be on checkout page")
    public void iShouldBeOnCheckoutPage() {
        assertTrue("Checkout page wasn't loaded", checkoutPage.isLoaded());
    }

    @And("I provide personal info and pay for booking with card details ([0-9]+ [0-9]+ [0-9]+ [0-9]+), (\\d\\d)/(\\d\\d) and (\\d\\d\\d)")
    public void iPayForBookingWithCardDetails(String ccNo, String expiryMonth, String expiryYear, String cvv) {
        checkoutPage.providePersonalData(homePage.getPassengerInfo());
        checkoutPage.providePhoneData();
        checkoutPage.provideCCData(ccNo, expiryMonth, expiryYear, cvv);
        checkoutPage.provideAddressData();
        checkoutPage.acceptTerms();
        checkoutPage.clickPayNow();
    }

    @Then("I should get payment declined message")
    public void iShouldGetPaymentDeclinedMessage() {
        assertEquals("Payment error message was different than expected.", getTestProperties().getProperty("payment_error_message"), checkoutPage.getErrorMessage());
    }
}
