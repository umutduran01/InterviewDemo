package StepDefinitions;

import Utils.CommonMethods;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

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

}
