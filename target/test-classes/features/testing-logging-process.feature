Feature: Testing logging process process

  Background: I am on home page, not logged in

  Scenario: Testing login with valid credentials
    When  I try to login with valid data
    Then  I should be logged in

  Scenario: Testing login with invalid credentials
    When  I try to login with invalid data
    Then  I shouldn't be logged in
    And   I should see error message: "Authentication failed."