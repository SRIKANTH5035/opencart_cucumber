Feature: Login
  Scenario: Successful Login With Valid Credentials
    Given User launch browser
    And opens URL "https://demo.opencart.com/"
    When user navigate to MyAccount menu
    And Click on login
    And user enters Email as "pavanoltraining@gmail.com" and Password as "test123"
    And Click on login button
    Then User navigates to MyAccount page
