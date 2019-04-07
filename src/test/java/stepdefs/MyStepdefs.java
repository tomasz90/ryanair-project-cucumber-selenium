package stepdefs;

import cucumber.api.java.en.And;
import cucumber.api.java.en.When;
import pageobjects.HomePage;

public class MyStepdefs {

    private HomePage homePage = new HomePage();

    @When("I navigate to home page")
    public void iNavigateToHomePage() {
        homePage.navigate();
    }

    @And("I perform booking from (.+) to (.+) on (.+) for (\\d) adults and (\\d) child")
    public void iPerformBookingFromToOnForAdultsAndChild(String from, String to, String on, int adults, int child) {
        homePage.clickOneWay();
        homePage.setFrom(from);
        homePage.setTo(to);
        homePage.setDate(on);
        homePage.clickPassengerDDM();
        homePage.setAdults(adults);
        homePage.setChild(child);
    }
}
