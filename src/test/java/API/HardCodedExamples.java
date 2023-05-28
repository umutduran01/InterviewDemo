package API;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@FixMethodOrder(MethodSorters.NAME_ASCENDING) //We can use this when the order of the methods is important.
public class HardCodedExamples {

    @Test
    public void bGetCreatedEmployee() {
        RequestSpecification prepareRequest = given().header("Content-Type", "application/json").header("Authorization", token).queryParam("employee_id", employee_id);

        //hitting the endpoint
        Response response = prepareRequest.when().get("/getOneEmployee.php");

        //print the output
        response.prettyPrint();

        //verify the response
        response.then().assertThat().statusCode(200);

        String temporaryEmpID = response.jsonPath().getString("employee.employee_id");

        //We have 2 emp ID, one is global and second is local
        Assert.assertEquals(employee_id, temporaryEmpID);

        System.out.println("This case has passed.");
    }


    //In Postman we do not add anything to baseURL but here we add http:// to create a connection.

    String baseURI = RestAssured.baseURI = "http://hrm.syntaxtechs.net/syntaxapi/api";
    String token = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE2ODUyNzc5MzQsImlzcyI6ImxvY2FsaG9zdCIsImV4cCI6MTY4NTMyMTEzNCwidXNlcklkIjoiNTQ5OSJ9.LwrIVeHAl-rkYHvAZbKBKa6s4jjnPY_rV1O_-vORWCQ";
    static String employee_id;

    @Test
    public void aCreateEmployee() {
        //prepare the request
        RequestSpecification preparedRequest = given().header("Content-Type", "application/json").header("Authorization", token).body("{\n" +
                "    \"emp_firstname\": \"umut\",\n" +
                "    \"emp_lastname\": \"faria\",\n" +
                "    \"emp_middle_name\": \"ms\",\n" +
                "    \"emp_gender\": \"F\",\n" +
                "    \"emp_birthday\": \"1995-05-24\",\n" +
                "    \"emp_status\": \"Confirmed\",\n" +
                "    \"emp_job_title\": \"Engineer\"\n" +
                "}");


        //hitting the endpoint
        Response response = preparedRequest.when().post("/createEmployee.php");

        //it will print the output
        response.prettyPrint();

        //verifying the assertions
        response.then().assertThat().statusCode(201);

        //we capture emp ID from the response
        employee_id = response.jsonPath().getString("Employee.employee_id");

        //we are verifying the firstname in the response body
        response.then().assertThat().body("Employee.emp_firstname", equalTo("umut"));
        response.then().assertThat().body("Employee.emp_lastname", equalTo("faria"));
        response.then().assertThat().header("Content-Type", "application/json");

        //if assertions fail, we'll not see the message
        System.out.println("My test case passed.");

    }


    @Test
    public void cUpdateEmployee() {
        RequestSpecification preparedRequest = given().header("Content-Type", "application/json").header("Authorization", token).body("{\n" +
                "    \"employee_id\": \"" + employee_id + "\",\n" +
                "    \"emp_firstname\": \"umut\",\n" +
                "    \"emp_lastname\": \"duran\",\n" +
                "    \"emp_middle_name\": \"m\",\n" +
                "    \"emp_gender\": \"M\",\n" +
                "    \"emp_birthday\": \"1995-05-24\",\n" +
                "    \"emp_status\": \"Confirmed\",\n" +
                "    \"emp_job_title\": \"QA Tester\"\n" +
                "}");

        //When we want to put employee ID that is not hard coded, remove the employee ID - put "" - put ++ - put your variable between two +.

        Response response = preparedRequest.when().put("/updateEmployee.php");

        response.prettyPrint();

        response.then().assertThat().statusCode(200);
        response.then().assertThat().body("Message", equalTo("Employee record Updated"));

        System.out.println("Employee is updated.");
    }

    @Test
    public void dGetUpdatedEmployee() {
        RequestSpecification preparedRequest = given().header("Content-Type", "application/json").header("Authorization", token).queryParam("employee_id", employee_id);
        Response response = preparedRequest.when().get("/getOneEmployee.php");
        response.prettyPrint();
        response.then().assertThat().statusCode(200);

        //if we want to verify the body of the response, we can do that using hamcrest matchers
    }
}

/*
   @Test
    public void getEmployeeStatus() {
        RequestSpecification preparedRequest = given().header("Content-Type", "application/json").header("Authorization", token);
        Response response = preparedRequest.when().get("/employeementStatus.php");
        response.prettyPrint();
    }

    @Test
    public void getJobTitle() {
        RequestSpecification preparedRequest = given().header("Content-Type", "application/json").header("Authorization", token);
        Response response = preparedRequest.when().get("/jobTitle.php");
        response.prettyPrint();
    }
 */