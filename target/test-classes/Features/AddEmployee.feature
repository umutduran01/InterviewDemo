Feature: Adding Employee

  Background:
    When user enter valid email and valid password
    And click on login button
    When user clicks on PIM option
    And user clicks on add employee button

  @newTest
  Scenario: Adding a new employee
#    Given open the browser and launch HRMS application
#    When user enter valid email and valid password
#    And click on login button
#    When user clicks on PIM option
#    And user clicks on add employee button
    And user enters first name, middle name and last name
    Then user clicks on save button
#    And close the browser

  @database
  Scenario: Adding employee from front
#    When user enter valid email and valid password
#    And click on login button
#    When user clicks on PIM option
#    And user clicks on add employee button
    And user enters "nesha" and "sania" and "standart"
    And user captures employee id
    And user clicks on save button
    And query the information in backend
    Then verify the results from frontend and backend