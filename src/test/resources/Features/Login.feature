Feature: Login Functionalities

  @smoke
  Scenario: Valid Admin Login
    Given open the browser and launch HRMS application
    When user enter valid email and valid password
    And click on login button
    Then user is logged in successfully
    And close the browser