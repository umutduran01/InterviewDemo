Feature: API workflow for HRMS

  Background:
    Given a JWT is generated

  @api
  Scenario: create an employee using API call
    Given a request is prepared to create an employee
    When a POST call is made to create an employee
    Then the status code for creating an employee is 201
    Then the employee contains key "Message" and value "Employee Created"
    Then the employee id "Employee.employee_id" is stored as a global variable to be used in other calls

  @apijsonworkflow
  Scenario: create an employee using API call and JSON payload
    Given a request is prepared to create an employee with json payload
    When a POST call is made to create an employee
    Then the status code for creating an employee is 201
    Then the employee contains key "Message" and value "Employee Created"
    Then the employee id "Employee.employee_id" is stored as a global variable to be used in other calls

  @apijsonworkflow
  Scenario: retrieve an employee using API call
    Given a request is prepared to get the created employee
    When a GET call is made to get the employee
    Then the status code for this employee is 200
    Then the employee data we get having id "employee.employee_id" must match with globally stored employee_id
    Then retrieved data at "employee" object matches with the data of created employee
      | emp_firstname | emp_lastname | emp_middle_name | emp_gender | emp_birthday | emp_status | emp_job_title |
      | Elon          | Musk         | von             | Male       | 2000-10-10   | Confirmed  | Engineer      |

  @apipayloadmoredynamic
  Scenario: create an employee using API call
    Given a request is prepared to create an employee with dynamic data such as "Bill", "Gates", "", "M", "1960-02-09", "Confirmed", "CEO"
    When a POST call is made to create an employee
    Then the status code for creating an employee is 201
    Then the employee contains key "Message" and value "Employee Created"
    Then the employee id "Employee.employee_id" is stored as a global variable to be used in other calls

  @updateemployee
  Scenario: updating the employee data
    Given a request is prepared the update an employee
    When a PUT call is made to update the employee
    Then status code of the updated employee is 200

  @datachaining
  Scenario: data chaining in API
    Given a request is prepared to create an employee with datatable
      | emp_firstname | emp_lastname | emp_middle_name | emp_gender | emp_birthday | emp_status | emp_job_title |
      | Umut          | Duran        | von             | M          | 1995-03-14   | Pending    | Engineer      |
    When a POST call is made to create an employee
    And "Employee.employee_id" stored in global value
    When a request is prepared to get the created employee
    And a GET call is made to get the employee
    Then retrieved data at "employee" object matches with the data of created employee
      | emp_firstname | emp_middle_name | emp_lastname | emp_gender | emp_birthday | emp_status | emp_job_title |
      | Umut          | von             | Duran        | Male       | 1995-03-14   | Pending    | Engineer      |