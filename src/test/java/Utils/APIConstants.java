package Utils;

import io.restassured.RestAssured;

public class APIConstants {

    //constants for all end points
    public static final String BaseURI = RestAssured.baseURI = "http://hrm.syntaxtechs.net/syntaxapi/api";
    public static final String GENERATE_TOKEN_URI = BaseURI + "/generateToken.php";
    public static final String CREATE_EMPLOYEE_URI = BaseURI + "/createEmployee.php";
    public static final String GET_ONE_EMPLOYEE = BaseURI + "/getOneEmployee.php";
    public static final String UPDATE_EMPLOYEE_URI = BaseURI + "/updateEmployee.php";
    public static final String GET_ALL_EMPLOYEES_URI = BaseURI + "/getAllEmployees.php";
    public static final String PARTIALLY_UPDATE_EMPLOYEE_URI = BaseURI + "/updatePartialEmplyeesDetails.php";
    public static final String GET_EMPLOYEE_STATUS_URI = BaseURI + "/employeementStatus.php";
    public static final String GET_JOB_TITLE_EMPLOYEE_URI = BaseURI + "/jobTitle.php";
    public static final String DELETE_EMPLOYEE__URI = BaseURI + "/deleteEmployee.php";

    //constants for headers
    public static final String HEADER_KEY_CONTENT_TYPE = "Content-Type";
    public static final String HEADER_VALUE_CONTENT_TYPE = "application/json";
    public static final String HEADER_KEY_AUTHORIZATION = "Authorization";
}
