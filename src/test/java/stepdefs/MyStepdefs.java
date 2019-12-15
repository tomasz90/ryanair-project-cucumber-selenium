package stepdefs;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.support.PageFactory;
import pageobjects.CartPage;
import pageobjects.CreateAccountPage;
import pageobjects.HomePage;
import pageobjects.MyAccountPage;
import pageobjects.ProductsPage;
import pageobjects.SignInPage;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static pageobjects.TestBase.getDriver;
import static pageobjects.TestBase.getTestProperties;

public class MyStepdefs {

    private HomePage homePage = PageFactory.initElements(getDriver(), HomePage.class);

    private SignInPage signInPage = PageFactory.initElements(getDriver(), SignInPage.class);

    private CreateAccountPage createAccountPage = PageFactory.initElements(getDriver(), CreateAccountPage.class);

    private MyAccountPage myAccountPage = PageFactory.initElements(getDriver(), MyAccountPage.class);

    private ProductsPage productsPage = PageFactory.initElements(getDriver(), ProductsPage.class);

    private CartPage cartPage = PageFactory.initElements(getDriver(), CartPage.class);

    private String validEmail = getTestProperties().getProperty("valid_email");

    private String inValidPassword = getTestProperties().getProperty("invalid_password");

    private String validPassword = getTestProperties().getProperty("valid_password");

    @When("^(I am logged in|I try to login with valid data)$") public void iTryToLoginWithValidData(String s) {
        login(validEmail, validPassword);
    }

    @When("I try to login with invalid data") public void iTryToLoginWithInvalidData() {
        login(validEmail, inValidPassword);
    }

    @Then("^I (should|shouldn't) be logged in$") public void iShouldBeLoggedIn(String should) {
        assertShould(should, homePage.isLoggedIn());
    }

    private void login(String email, String password) {
        homePage.clickSignIn();
        assertTrue(signInPage.isLoaded());
        signInPage.enterEmail(email);
        signInPage.enterPassword(password);
        signInPage.submit();
    }

    @And("I should see error message: {string}") public void iShouldSeeErrorMessage(String message) {
        assertEquals(message, signInPage.getErrorMessage());
    }

    @When("I start creating account") public void iStartCreatingAccount() {
        homePage.clickSignIn();
        assertTrue(signInPage.isLoaded());
        signInPage.enterNewEmail();
        signInPage.createAccount();
    }

    @And("I register user using valid data") public void iRegisterUserUsingValidData() {
        assertTrue(createAccountPage.isLoaded());
        createAccountPage.enterFirstName();
        createAccountPage.enterLastName();
        createAccountPage.enterPassword();
        createAccountPage.enterAddress();
        createAccountPage.enterCity();
        createAccountPage.chooseState();
        createAccountPage.enterZip();
        createAccountPage.enterPhone();
        createAccountPage.clickRegister();
    }

    @Then("I should have registered account") public void iShouldHaveRegisteredAccount() {
        assertTrue(myAccountPage.isLoaded());
    }

    @When("^I add any product from (women|dresses|t-shirts) section$") public void iAddAnyProductFormWomanSection(String section) {
        homePage.goToSection(section);
        assertTrue(productsPage.isLoaded());
        productsPage.addToCart();
    }

    @And("I start checkout process") public void iStartCheckoutProcess() {
        productsPage.proceedCheckout();
        assertTrue(cartPage.isLoaded());
        cartPage.proceedCheckout();
        cartPage.proceedCheckout();
        cartPage.agreeTermsOfService();
        cartPage.proceedCheckout();
    }

    @And("^I chose payment as (bankwire|cheque)$") public void iChosePaymentAsCheck(String payment) {
        cartPage.chosePayment(payment);
        cartPage.proceedCheckout();
    }

    @Then("I should complete order") public void iShouldCompleteOrder() {
        assertTrue(cartPage.isOrderComplete());
    }

    private void assertShould(String should, boolean condition) {
        switch (should) {
            case "should":
                assertTrue("Condition should be fulfilled, but it wasn't", condition );
                return;
            case "shouldn't":
                assertFalse("Condition shouldn't be fulfilled, but it was", condition);
                return;
        }
        throw new IllegalArgumentException("Given parameter was invalid. Method should pass only should/shouldn't keywords.");
    }
}
