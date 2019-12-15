Feature: Testing creating account

  Background: I am on home page, not logged in

  Scenario: Testing creating account
    When  I start creating account
    And   I register user using valid data
    Then  I should have registered account
