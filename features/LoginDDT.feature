Feature: Login
  Background: Common Steps for all the login scenarios
    Given User launch browser
    And opens URL "https://demo.opencart.com/"
    When user navigate to MyAccount menu
    And Click on login

  Scenario Outline: Login data driven
#    Given User launch browser
#    And opens URL "https://demo.opencart.com/"
#    When user navigate to MyAccount menu
#    And Click on login
    And user enters Email as "<email>" and Password as "<password>"
    And Click on login button
    Then User navigates to MyAccount page
    Examples:
      | email                     | password |
      | pavanoltraining@gmail.com | test123  |
      | pavanol@gmail.com         | test123  |

  Scenario Outline: Login data driven2
#    Given User launch browser
#    And opens URL "https://demo.opencart.com/"
#    When user navigate to MyAccount menu
#    And Click on login
    Then check user navigates to MYAccount page by passing email and password with excel row "<row_index>"
    Examples:
      | row_index |
      |1          |
      |2          |
      |3          |