Feature: Testing booking process

  Scenario: Testing invalid payment information
    Given I navigate to home page and login into test account
    Then  I should be logged in
    When  I perform searching for flight from DUB to SXF on 18-05-2019 for 2 adults and 1 child
    And   I choose the following options: flight with standard fare, small bags, random seats
    Then  I should be on checkout page
    When  I provide personal info and pay for booking with card details 5555 5555 5555 5557, 10/19 and 265
    Then  I should get payment declined message
