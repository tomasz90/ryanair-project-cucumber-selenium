Feature: Testing checkout process

  Background: I am on home page, not logged in

  Scenario: Testing checkout process
    When  I am logged in
    And   I add any product from dresses section
    And   I start checkout process
    And   I chose payment as bankwire
    Then  I should complete order

