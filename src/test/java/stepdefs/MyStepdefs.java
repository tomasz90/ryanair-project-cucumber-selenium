package stepdefs;

import cucumber.api.java.en.And;
import cucumber.api.java.en.When;
import pageobjects.HomePage;
import pageobjects.LoginPopUp;

import static pageobjects.TestBase.getWebConfig;

public class MyStepdefs {

    private HomePage homePage = new HomePage();
    private LoginPopUp loginPopUp = new LoginPopUp();

    @When("I navigate to home page")
    public void iNavigateToHomePage() {
        homePage.navigate();
    }

    @And("I log into test account")
    public void iLogIn() {
        homePage.clickLogin();
        loginPopUp.enterEmail(getWebConfig().getProperty("email"));
        loginPopUp.enterPassword(getWebConfig().getProperty("password"));
        loginPopUp.submit();
    }

    @And("I perform searching for flight from (.+) to (.+) on (.+) for (\\d) adults and (\\d) child")
    public void iPerformSearchingForFlightFromToOnForAdultsAndChild(String from, String to, String on, int adults, int child) throws Exception {
        homePage.clickOneWay();
        homePage.setFrom(from);
        homePage.setTo(to);
        homePage.setDate(on);
        homePage.clickPassengerDDM();
        homePage.setAdults(String.valueOf(adults));
        homePage.setChild(String.valueOf(child));
        homePage.acceptTermsOfUse();
        homePage.clickLetsFly();
    }
}
