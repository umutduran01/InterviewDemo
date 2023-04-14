package StepDefinitions;

import Utils.CommonMethods;
import Utils.ConfigReader;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class AddEmployee extends CommonMethods {

    @When("user clicks on PIM option")
    public void user_clicks_on_pim_option() {
        WebElement pimButton = driver.findElement(By.id("menu_pim_viewPimModule"));
        doClick(pimButton);
    }

    @When("user clicks on add employee button")
    public void user_clicks_on_add_employee_button() {
        WebElement addEmployee = driver.findElement(By.id("menu_pim_addEmployee"));
        doClick(addEmployee);
    }

    @When("user enters first name, middle name and last name")
    public void user_enters_first_name_middle_name_and_last_name() {
        WebElement firstName = driver.findElement(By.id("firstName"));
        sendText(firstName, "firstname");
        WebElement middleName = driver.findElement(By.id("middleName"));
        sendText(middleName, "middlename");
        WebElement lastName = driver.findElement(By.id("lastName"));
        sendText(lastName, "lastname");
    }

    @Then("user clicks on save button")
    public void user_clicks_on_save_button() {
        WebElement saveButton = driver.findElement(By.id("btnSave"));
        doClick(saveButton);
    }

}
