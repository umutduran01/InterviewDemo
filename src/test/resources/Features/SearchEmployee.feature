Feature: Searching Employee

  Background:
    When user enter valid email and valid password
    And click on login button
    When user clicks on PIM option
@test1
  Scenario: Searching by ID
#    Given open the browser and launch HRMS application
#    When user enter valid email and valid password
#    And click on login button
#    When user clicks on PIM option
    When user enters valid employee id
    And click on search button
    Then user sees employee information is displayed
    #And close the browser


  Scenario: Choosing Job Title from Dropdown
#    Given open the browser and launch HRMS application
#    When user enter valid email and valid password
#    And click on login button
#    When user clicks on PIM option
    When user selects job title
    And click on search button
    Then user sees employee information by job title is displayed
    #And close the browser