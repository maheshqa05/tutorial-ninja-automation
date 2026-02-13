Feature: Account Registration

  Scenario: Successful registration
    Given user is on registration page
    When user registers with valid details
    Then account should be created successfully

  Scenario: Registration validation errors
    Given user is on registration page
    When user submits registration with invalid data
    Then all registration error messages should be shown