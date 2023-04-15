package StepDefinitions;

import Utils.CommonMethods;
import Utils.ConfigReader;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class SearchEmployee extends CommonMethods {
    @When("user enters valid employee id")
    public void user_enters_valid_employee_id() {
        WebElement enterId = driver.findElement(By.id("empsearch_id"));
        sendText(enterId,"empid");
    }

    @When("click on search button")
    public void click_on_search_button() {
        WebElement searchButton = driver.findElement(By.id("searchBtn"));
        doClick(searchButton);
    }

    @Then("user sees employee information is displayed")
    public void user_sees_employee_information_is_displayed() {
        WebElement employee = driver.findElement(By.xpath("//a[text()='" + ConfigReader.getPropertyValue("empid") + "']"));
        employee.isDisplayed();
    }

    @When("user selects job title")
    public void userSelectsJobTitle() {
        WebElement jobTitleDDL = driver.findElement(By.id("empsearch_job_title"));
        selectByVisibleText(jobTitleDDL, "Singer");
    }

    @Then("user sees employee information by job title is displayed")
    public void userSeesEmployeeInformationByJobTitleIsDisplayed() {
        System.out.println(driver.findElement(By.xpath("//table[@id='resultTable']/tbody/tr/td[5]")).getText());
    }
}
