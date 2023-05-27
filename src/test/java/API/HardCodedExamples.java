package API;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class HardCodedExamples {

    //In Postman we do not add anything to baseURL but here we add http:// to create a connection.

    String baseURI = RestAssured.baseURI = "http://hrm.syntaxtechs.net/syntaxapi/api";
    String token = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE2ODUxODk0MTcsImlzcyI6ImxvY2FsaG9zdCIsImV4cCI6MTY4NTIzMjYxNywidXNlcklkIjoiNTQ5OSJ9.pVCaZD2MuLs2Ssfsnb1-rCeU3galPMEk0JcJJ5E6RWc";

    @Test
    public void createEmployee() {
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

        //we are verifying the firstname in the response body
        response.then().assertThat().body("Employee.emp_firstname", equalTo("umut"));

        //if assertions fail, we'll not see the message
        System.out.println("My test case passed.");

    }

}
