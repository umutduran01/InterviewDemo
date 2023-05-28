package APIStepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class APIWorkflowSteps {
    @Given("a request is prepared to create an employee")
    public void a_request_is_prepared_to_create_an_employee() {
        RequestSpecification preparedRequest = given().header("Content-Type", "application/json").header("Authorization", GenerateToken.token).body("{\n" +
                "    \"emp_firstname\": \"umut\",\n" +
                "    \"emp_lastname\": \"faria\",\n" +
                "    \"emp_middle_name\": \"ms\",\n" +
                "    \"emp_gender\": \"F\",\n" +
                "    \"emp_birthday\": \"1995-05-24\",\n" +
                "    \"emp_status\": \"Confirmed\",\n" +
                "    \"emp_job_title\": \"Engineer\"\n" +
                "}");


        Response response = preparedRequest.when().post("/createEmployee.php");

        response.prettyPrint();

        response.then().assertThat().statusCode(201);

        String employee_id = response.jsonPath().getString("Employee.employee_id");

        //we are verifying the firstname in the response body
        response.then().assertThat().body("Employee.emp_firstname", equalTo("umut"));
        response.then().assertThat().body("Employee.emp_lastname", equalTo("faria"));
        response.then().assertThat().header("Content-Type", "application/json");
    }

    @When("a POST call is made to create an employee")
    public void a_post_call_is_made_to_create_an_employee() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Then("the status code for createing an employee is {int}")
    public void the_status_code_for_createing_an_employee_is(Integer int1) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
}
