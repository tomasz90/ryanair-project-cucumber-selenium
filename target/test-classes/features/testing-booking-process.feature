Feature: Testing booking process

  Scenario: Testing invalid payment information
    When I navigate to home page
    And  I log into test account
    And  I perform booking from DUB to SXF on 01/01/2020 for 3 adults and 2 child
