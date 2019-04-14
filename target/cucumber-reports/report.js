$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("file:src/test/resources/features/testing-booking-process.feature");
formatter.feature({
  "name": "Testing booking process",
  "description": "",
  "keyword": "Feature"
});
formatter.scenario({
  "name": "Testing invalid payment information",
  "description": "",
  "keyword": "Scenario"
});
formatter.step({
  "name": "I navigate to home page and login into test account",
  "keyword": "Given "
});
formatter.match({
  "location": "MyStepdefs.iNavigateToHomePage()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I should be logged in",
  "keyword": "Then "
});
formatter.match({
  "location": "MyStepdefs.iShouldBeLoggedIn()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I perform searching for flight from DUB to SXF on 29-05-2019 for 2 adults and 1 child",
  "keyword": "When "
});
formatter.match({
  "location": "MyStepdefs.iPerformSearchingForFlightFromToOnForAdultsAndChild(String,String,String,int,int)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I choose the following options: flight with standard fare, small bags, random seats",
  "keyword": "And "
});
formatter.match({
  "location": "MyStepdefs.iChooseTheFollowingOptionsFlightWithStandardFareSmallBagsRandomSeats()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I should be on checkout page",
  "keyword": "Then "
});
formatter.match({
  "location": "MyStepdefs.iShouldBeOnCheckoutPage()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I provide personal info and pay for booking with card details 5555 5555 5555 5557, 10/19 and 265",
  "keyword": "When "
});
formatter.match({
  "location": "MyStepdefs.iPayForBookingWithCardDetails(String,String,String,String)"
});
formatter.result({
  "error_message": "org.openqa.selenium.NoSuchElementException: Cannot locate element with text: United Kingdom\nFor documentation on this error, please visit: http://seleniumhq.org/exceptions/no_such_element.html\nBuild info: version: \u00272.53.1\u0027, revision: \u0027a36b8b1cd5757287168e54b817830adce9b0158d\u0027, time: \u00272016-06-30 19:26:09\u0027\nSystem info: host: \u0027DESKTOP-NPT9O2N\u0027, ip: \u0027169.254.179.76\u0027, os.name: \u0027Windows 10\u0027, os.arch: \u0027amd64\u0027, os.version: \u002710.0\u0027, java.version: \u00271.8.0_201\u0027\nDriver info: driver.version: unknown\r\n\tat org.openqa.selenium.support.ui.Select.selectByVisibleText(Select.java:150)\r\n\tat pageobjects.CheckoutPage.providePhoneData(CheckoutPage.java:75)\r\n\tat stepdefs.MyStepdefs.iPayForBookingWithCardDetails(MyStepdefs.java:87)\r\n\tat ✽.I provide personal info and pay for booking with card details 5555 5555 5555 5557, 10/19 and 265(file:src/test/resources/features/testing-booking-process.feature:9)\r\n",
  "status": "failed"
});
formatter.step({
  "name": "I should get payment declined message",
  "keyword": "Then "
});
formatter.match({
  "location": "MyStepdefs.iShouldGetPaymentDeclinedMessage()"
});
formatter.result({
  "status": "skipped"
});
formatter.after({
  "status": "passed"
});
});