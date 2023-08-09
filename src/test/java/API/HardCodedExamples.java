package API;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class HardCodedExamples {

    String baseURI = RestAssured.baseURI = "http://hrm.syntaxtechs.net/syntaxapi/api";
    String token = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE2OTE0MzIyOTgsImlzcyI6ImxvY2FsaG9zdCIsImV4cCI6MTY5MTQ3NTQ5OCwidXNlcklkIjoiNTg1NiJ9.QOdLf1QzT1CaTCEUFvcyxKcfk2d0CGyDpK6M3bSLqW0";

    static String employeeID;


    //==================================================================================================================

    @Test //every method is considered as a test, so we use JUnit annotations
    public void aCreateEmployee() {

        //prepare the request
        RequestSpecification request = given().
                header("Content-Type", "application/json").
                header("Authorization", token).
                body("{\n" +
                        "  \"emp_firstname\": \"M47\",\n" +
                        "  \"emp_lastname\": \"Labs\",\n" +
                        "  \"emp_middle_name\": \"Barcelona\",\n" +
                        "  \"emp_gender\": \"M\",\n" +
                        "  \"emp_birthday\": \"2001-01-01\",\n" +
                        "  \"emp_status\": \"Pending\",\n" +
                        "  \"emp_job_title\": \"API Tester\"\n" +
                        "}");

        //hitting the endpoint / sending the request, and it will return a response
        Response response = request.when().post("/createEmployee.php");

        //print the output on console
        response.prettyPrint();

        //verifying the assertions
        response.then().assertThat().statusCode(201);
        response.then().assertThat().body("Employee.emp_firstname", equalTo("M47"));
        response.then().assertThat().header("Content-Type", "application/json");

        //extract employee_id
        employeeID = response.jsonPath().getString("Employee.employee_id");

        //console message
        System.out.println("My test case has passed.");
    }

    //==================================================================================================================

    @Test
    public void bGetCreatedEmployee() {

        //prepare the request
        RequestSpecification request = given().header("Content-Type", "application/json").header("Authorization", token).queryParam("employee_id", employeeID);

        //send the request and receive a response
        Response response = request.when().get("/getOneEmployee.php");

        //verify assertions
        response.then().assertThat().statusCode(200);

        String responseEmployeeID = response.jsonPath().getString("employee.employee_id"); //I extract the employee ID in getCreatedEmployee and assert with createEmployee's.
        Assert.assertEquals(employeeID, responseEmployeeID); //JUnit's assertions to compare two Strings.

        //print the response
        response.prettyPrint();
    }

    //==================================================================================================================

    @Test
    public void cUpdateEmployee() {

        RequestSpecification request = given().header("Content-Type", "application/json").header("Authorization", token).body("{\n" +
                "  \"employee_id\": \"" + employeeID + "\",\n" +
                "  \"emp_firstname\": \"M47\",\n" +
                "  \"emp_lastname\": \"Labs\",\n" +
                "  \"emp_middle_name\": \"Madrid\",\n" +
                "  \"emp_gender\": \"F\",\n" +
                "  \"emp_birthday\": \"2002-02-02\",\n" +
                "  \"emp_status\": \"Tester\",\n" +
                "  \"emp_job_title\": \"Automation Tester\"\n" +
                "}");

        //To be able to pace employeeID in the body, add "+variable+".

        //send the request
        Response response = request.when().put("/updateEmployee.php");

        response.prettyPrint();

        //assertions
        response.then().assertThat().statusCode(200);
        response.then().assertThat().body("Message",equalTo("Employee record Updated"));
    }

    //==================================================================================================================

    @Test
    public void dGetUpdatedEmployee(){
        //prepare the request
        RequestSpecification request = given().header("Content-Type", "application/json").header("Authorization", token).queryParam("employee_id", employeeID);

        //send the request and receive a response
        Response response = request.when().get("/getOneEmployee.php");

        response.prettyPrint();

        //verify assertions
        response.then().assertThat().statusCode(200);
        response.then().assertThat().body("employee.emp_job_title",equalTo("Automation Tester"));
    }

    //==================================================================================================================

    @Ignore
    @Test
    public void eGetAllEmployees() {
        RequestSpecification request = given().header("Content-Type", "application/json").header("Authorization", token);
        Response response = request.when().get("/getAllEmployees.php");
        response.then().assertThat().statusCode(200);
        response.prettyPrint();
    }
}

