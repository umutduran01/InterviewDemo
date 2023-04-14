Feature: Login Functionalities

  Scenario: Valid Admin Login
    #Given open the browser and launch HRMS application
    When user enter valid email and valid password
    And click on login button
    Then user is logged in successfully
    #And close the browser

  @smoke2
  Scenario: Valid Admin Login
    #Given open the browser and launch HRMS application
    When user enter valid "Admin" and valid "Hum@nhrm123"
    And click on login button
    Then user is logged in successfully
    #And close the browser