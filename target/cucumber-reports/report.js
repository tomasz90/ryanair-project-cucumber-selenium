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
  "name": "I perform searching for flight from DUB to SXF on 25-05-2019 for 2 adults and 1 child",
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
  "status": "passed"
});
formatter.step({
  "name": "I should get payment declined message",
  "keyword": "Then "
});
formatter.match({
  "location": "MyStepdefs.iShouldGetPaymentDeclinedMessage()"
});
formatter.result({
  "status": "passed"
});
formatter.after({
  "status": "passed"
});
});