package APIStepDefinitions;

import Utils.APIConstanst;
import Utils.APIPayloadConstants;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class APIWorkflowSteps {

    RequestSpecification request;
    Response response;
    public static String employee_id;


    //making a request with usual payload
    @Given("a request is prepared to create an employee")
    public void a_request_is_prepared_to_create_an_employee() {
        request = given().header(APIConstanst.HEADER_KEY_CONTENT_TYPE, APIConstanst.HEADER_VALUE_CONTENT_TYPE).header(APIConstanst.HEADER_KEY_AUTHORIZATION, GenerateToken.token).body(APIPayloadConstants.createEmployeePayload());
    }

    //-----------------------------------------------

    //another request to add payload with payload
    @Given("a request is prepared to create an employee using json payload")
    public void a_request_is_prepared_to_create_an_employee_using_json_payload() {
        request = given().header(APIConstanst.HEADER_KEY_CONTENT_TYPE, APIConstanst.HEADER_VALUE_CONTENT_TYPE).header(APIConstanst.HEADER_KEY_AUTHORIZATION, GenerateToken.token).body(APIPayloadConstants.createEmployeePayloadJson());
    }

    @When("a POST call is made to create an employee")
    public void a_post_call_is_made_to_create_an_employee() {
        response = request.when().post(APIConstanst.CREATE_EMPLOYEE_URI);
    }

    @Then("the status code for creating an employee is {int}")
    public void the_status_code_for_creating_an_employee_is(Integer int1) {
        //this value comes from feature file
        response.prettyPrint();
        response.then().assertThat().statusCode(int1);
    }

    @Then("the employee contains key {string} and value {string}")
    public void the_employee_contains_key_and_value(String string, String string2) {
        //we use hamcrest matchers here
        response.then().body(string, equalTo(string2));
    }

    @Then("employee id {string} is stored as a global variable to be used for other calls")
    public void employee_id_is_stored_as_a_global_variable_to_be_used_for_other_calls(String string) {
        //string employee id in global variable
        employee_id = response.jsonPath().getString(string);
        System.out.println(employee_id);
    }

    //---------------------------------------
    @Given("a request is prepared to get the created employee")
    public void a_request_is_prepared_to_get_the_created_employee() {
        request = given().header(APIConstanst.HEADER_KEY_CONTENT_TYPE, APIConstanst.HEADER_KEY_CONTENT_TYPE).header(APIConstanst.HEADER_KEY_AUTHORIZATION, GenerateToken.token).queryParam("employee_id", employee_id);
    }

    @When("a GET call is made to get the employee")
    public void a_get_call_is_made_to_get_the_employee() {
        response = request.when().get(APIConstanst.GET_EMPLOYEE_STATUS_URI);
    }

    @Then("the status code for this employee is {int}")
    public void the_status_code_for_this_employee_is(Integer int1) {
        response.then().assertThat().statusCode(int1);
    }

    @Then("the employee data we get having id {string} must match with globally stored employee id")
    public void the_employee_data_we_get_having_id_must_match_with_globally_stored_employee_id(String string) {
        //it will store th employee id coming from get call which will be compared to global employee id
        String tempEmpId = response.jsonPath().getString(string);
        //assertion is here
        Assert.assertEquals(employee_id, tempEmpId);
    }

    @Then("the retrieved data at {string} object matches with the data of created employee")
    public void the_retrieved_data_at_object_matches_with_the_data_of_created_employee(String employeeObject, io.cucumber.datatable.DataTable dataTable) {
        List<Map<String, String>> expectedData = dataTable.asMaps(); //comes from scenario

        Map<String, String> actualData = response.body().jsonPath().get(employeeObject); //comes from database

        for (Map<String, String> map : expectedData) {
            //Set is used to not to have duplicate values
            //We get all the keys
            Set<String> keys = map.keySet();
            //another enhanced for loop to get values and keys
            for (String key : keys) {
                //it will store the values against the key
                String expectedValue = map.get(key);
                String actualValue = actualData.get(key);

                Assert.assertEquals(expectedValue, actualValue);
            }
        }
    }


}
