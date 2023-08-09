package APIStepDefinitions;

import Utils.APIConstants;
import Utils.APIPayloadConstants;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.junit.Assert;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class APIWorkflowSteps {

    RequestSpecification request;
    Response response;

    public static String employeeID;

    @Given("a request is prepared to create an employee")
    public void a_request_is_prepared_to_create_an_employee() {

        request = given().
                header(APIConstants.HEADER_KEY_CONTENT_TYPE, APIConstants.HEADER_VALUE_CONTENT_TYPE).
                header(APIConstants.HEADER_KEY_AUTHORIZATION, GenerateToken.token).
                body(APIPayloadConstants.createEmployeePayload());
    }

    @When("a POST call is made to create an employee")
    public void a_post_call_is_made_to_create_an_employee() {
        response = request.when().post(APIConstants.CREATE_EMPLOYEE_URI);
        response.prettyPrint();
    }

    @Then("the status code for creating an employee is {int}")
    public void the_status_code_for_creating_an_employee_is(Integer int1) {
        response.prettyPrint();
        response.then().assertThat().statusCode(201);
    }

    @Then("the employee contains key {string} and value {string}")
    public void the_employee_contains_key_and_value(String message, String value) {

        response.then().assertThat().body(message, equalTo(value));

        //OR

        String messageFromResponse = response.jsonPath().getString(message);
        Assert.assertEquals(messageFromResponse, messageFromResponse);
        System.out.println(messageFromResponse);
    }

    @Then("the employee id {string} is stored as a global variable to be used in other calls")
    public void the_employee_id_is_stored_as_a_global_variable_to_be_used_in_other_calls(String string) {
        employeeID = response.jsonPath().getString(string);
        System.out.println(employeeID);
    }

    @Given("a request is prepared to create an employee with json payload")
    public void a_request_is_prepared_to_create_an_employee_with_json_payload() {
        System.out.println(employeeID);
        request = given().
                header(APIConstants.HEADER_KEY_CONTENT_TYPE, APIConstants.HEADER_VALUE_CONTENT_TYPE).
                header(APIConstants.HEADER_KEY_AUTHORIZATION, GenerateToken.token).
                body(APIPayloadConstants.createEmployeePayloadJson());
    }

    @Given("a request is prepared to get the created employee")
    public void a_request_is_prepared_to_get_the_created_employee() {
        request = given().
                header(APIConstants.HEADER_KEY_CONTENT_TYPE, APIConstants.HEADER_VALUE_CONTENT_TYPE).
                header(APIConstants.HEADER_KEY_AUTHORIZATION, GenerateToken.token).queryParam("employee_id", employeeID);
    }

    @When("a GET call is made to get the employee")
    public void a_get_call_is_made_to_get_the_employee() {
        response = request.when().get(APIConstants.GET_ONE_EMPLOYEE);
    }

    @Then("the status code for this employee is {int}")
    public void the_status_code_for_this_employee_is(Integer int1) {
        response.then().assertThat().statusCode(int1);
    }

    @Then("the employee data we get having id {string} must match with globally stored employee_id")
    public void the_employee_data_we_get_having_id_must_match_with_globally_stored_employee_id(String string) {
        String tempEmployeeID = response.jsonPath().getString(string);
        Assert.assertEquals(employeeID, tempEmployeeID);
    }

    @Then("retrieved data at {string} object matches with the data of created employee")
    public void retrieved_data_at_object_matches_with_the_data_of_created_employee(String string, DataTable dataTable) {

        List<Map<String, String>> expectedData = dataTable.asMaps();

        Map<String, String> actualData = response.body().jsonPath().get(string);

        for (Map<String, String> map : expectedData) {
            Set<String> keys = map.keySet();

            for (String key : keys) {
                String expectedValue = map.get(key);
                String actualValue = actualData.get(key);

                Assert.assertEquals(expectedValue, actualValue);
            }
        }
    }

    @Given("a request is prepared to create an employee with dynamic data such as {string}, {string}, {string}, {string}, {string}, {string}, {string}")
    public void a_request_is_prepared_to_create_an_employee_with_dynamic_data_such_as(String name, String lastName, String middleName, String gender, String birthday, String status, String title) {
        request = given().
                header(APIConstants.HEADER_KEY_CONTENT_TYPE, APIConstants.HEADER_VALUE_CONTENT_TYPE).
                header(APIConstants.HEADER_KEY_AUTHORIZATION, GenerateToken.token).body(APIPayloadConstants.createEmployeePayloadDynamic(name, lastName, middleName, gender, birthday, status, title));
    }

    @Given("a request is prepared the update an employee")
    public void a_request_is_prepared_the_update_an_employee() {
        request = given().
                header(APIConstants.HEADER_KEY_CONTENT_TYPE, APIConstants.HEADER_VALUE_CONTENT_TYPE).
                header(APIConstants.HEADER_KEY_AUTHORIZATION, GenerateToken.token).body(APIPayloadConstants.updateEmployeePayloadJson());
    }

    @When("a PUT call is made to update the employee")
    public void a_put_call_is_made_to_update_the_employee() {
        response = request.when().put(APIConstants.UPDATE_EMPLOYEE_URI);
    }

    @Then("status code of the updated employee is {int}")
    public void status_code_of_the_updated_employee_is(Integer int1) {
        response.then().assertThat().statusCode(int1);
    }

    @Given("a request is prepared to create an employee with datatable")
    public void a_request_is_prepared_to_create_an_employee_with_datatable(DataTable dataTable) {
        List<Map<String, String>> employeeInformationMap = dataTable.asMaps();

        for (Map<String, String> map : employeeInformationMap) {

            request = given().
                    header(APIConstants.HEADER_KEY_CONTENT_TYPE, APIConstants.HEADER_VALUE_CONTENT_TYPE).
                    header(APIConstants.HEADER_KEY_AUTHORIZATION, GenerateToken.token).body(APIPayloadConstants.createEmployeeFromDatatable(map));

        }

    }

    @When("{string} stored in global value")
    public void stored_in_global_value(String string) {
        employeeID = response.jsonPath().getString(string);
        System.out.println(employeeID);
    }
}
