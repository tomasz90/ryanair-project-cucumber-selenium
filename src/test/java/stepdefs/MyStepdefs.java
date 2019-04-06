package stepdefs;

import cucumber.api.java.en.When;

import static pageobjects.TestBase.getDriver;

public class MyStepdefs {

    @When("I go to")
    public void iGoTo() {
        getDriver().navigate().to("https://github.com/");
    }
}
