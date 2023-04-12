Feature: Adding Employee
  Scenario: Adding a new employee
    Given open the browser and launch HRMS application
    When user enter valid email and valid password
    And click on login button
    When user clicks on PIM option
    And user clicks on add employee button
    And user enters first name, middle name and last name
    Then user clicks on save button
