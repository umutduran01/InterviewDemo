package StepDefinitions;

import Utils.CommonMethods;
import Utils.DBUtility;
import Utils.GlobalVariables;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class AddEmployee extends CommonMethods {

    @When("user clicks on PIM option")
    public void user_clicks_on_pim_option() {
        doClick(addEmployeePage.pimButton);
    }

    @When("user clicks on add employee button")
    public void user_clicks_on_add_employee_button() {
        doClick(addEmployeePage.addEmployee);
    }

    @When("user enters first name, middle name and last name")
    public void user_enters_first_name_middle_name_and_last_name() {
        sendText(addEmployeePage.firstName, "firstname");
        sendText(addEmployeePage.middleName, "middlename");
        sendText(addEmployeePage.lastName, "lastname");
    }

    @Then("user clicks on save button")
    public void user_clicks_on_save_button() {
        doClick(addEmployeePage.saveButton);
    }

    @When("user enters {string} and {string} and {string}")
    public void user_enters_and_and(String firstName, String middleName, String lastName) {
        sendText(addEmployeePage.firstName, firstName);
        sendText(addEmployeePage.middleName, middleName);
        sendText(addEmployeePage.lastName, lastName);
    }

    @When("user captures employee id")
    public void user_captures_employee_id() {
        GlobalVariables.emp_id = addEmployeePage.empIdLocator.getAttribute("value");
        System.out.println("Employee ID is " + GlobalVariables.emp_id);
    }

    @When("query the information in backend")
    public void query_the_information_in_backend() {
        String query = "select * from hs_hr_employees where employee_id =' " + GlobalVariables.emp_id + "'";
        //To store the table coming from DB, we used global variable here
        //In this variable, we got all keys and values for the employee we searched
        GlobalVariables.tableData = DBUtility.getListOfMapsFromResultSet(query);
    }

    @Then("verify the results from frontend and backend")
    public void verify_the_results_from_frontend_and_backend() {
        //Now from all these values, we need to compare one by one value
        String firstNameFromDB = GlobalVariables.tableData.get(0).get("emp_firstname"); //From the table, go to the first line which is index 0 and get the key with name of emp_firstname.
        System.out.println(firstNameFromDB);
        String lastNameFromDB = GlobalVariables.tableData.get(0).get("emp_lastname"); //From the table, go to the first line which is index 0 and get the key with name of emp_firstname.
        System.out.println(lastNameFromDB);

        //adding assertions
        Assert.assertEquals(firstNameFromDB, "nesha");
        Assert.assertEquals(lastNameFromDB, "standart");
        System.out.println("My assertion has passed.");
    }

}
