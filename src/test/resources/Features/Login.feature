Feature: Login Functionalities

  Scenario: Valid Admin Login
    #Given open the browser and launch HRMS application
    When user enter valid email and valid password
    And click on login button
    Then user is logged in successfully
    #And close the browser


  Scenario: Valid Admin Login
    #Given open the browser and launch HRMS application
    When user enter valid "Admin" and valid "Hum@nhrm123"
    And click on login button
    Then user is logged in successfully
    #And close the browser

  #Parameterization / Data Driven--------------------------------------------

  @scnearioOutline
  Scenario Outline: Login with multiple credentials using Scenario Outline
    #Given open the browser and launch HRMS application
    When user enter valid "<username>" and valid "<password>"
    And click on login button
    Then user is logged in successfully
    #And close the browser
    Examples:
      | username | password    |
      | admin    | Hum@nhrm123 |
      | ADMIN    | Hum@nhrm123 |
      | Umut     | Hum@nhrm123 |


  #Data Table----------------------------------------------------------------

  @dataTable
  Scenario: Login with multiple credentials using data table
    When user enters username and password than verifies login
      | username | password    |
      | admin    | Hum@nhrm123 |
      | ADMIN    | Hum@nhrm123 |
      | Umut     | Hum@nhrm123 |