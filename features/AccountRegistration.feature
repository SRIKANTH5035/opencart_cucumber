Feature: Account Registration
  Scenario: Suceessful Acoount registration with valid user
    Given User launch browser
    And opens URL "https://demo.opencart.com/"
    When user navigate to MyAccount menu
    And click on Register
    Then user navigates to register account page
    When user provide valid details
    And click on continue
    Then user should see "your account has been created!" message