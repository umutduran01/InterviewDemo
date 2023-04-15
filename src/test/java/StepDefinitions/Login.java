package StepDefinitions;

import Pages.LoginPage;
import Utils.CommonMethods;
import Utils.ConfigReader;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import io.cucumber.datatable.DataTable;

import java.util.List;
import java.util.Map;

public class Login extends CommonMethods {

    LoginPage login = new LoginPage();

    @When("user enter valid email and valid password")
    public void user_enter_valid_email_and_valid_password() {
        sendText(login.usernameTextBox, "username");
        sendText(login.passwordTextBox, "password");
    }

    @When("click on login button")
    public void click_on_login_button() {
        driver.findElement(By.id("btnLogin")).click();
    }

    @Then("user is logged in successfully")
    public void user_is_logged_in_successfully() {
        Boolean userLogged = driver.findElement(By.xpath("//a[text()='Welcome Admin']")).isDisplayed();
        if (userLogged) {
            System.out.println("User logged in successfully.");
        }

    }

//    @Then("close the browser")
//    public void close_the_browser() {
//        closeBrowser();
//    }

    @When("user enter valid {string} and valid {string}")
    public void user_enter_valid_and_valid(String username1, String password1) {
        login.usernameTextBox.sendKeys(username1);
        login.passwordTextBox.sendKeys(password1);
    }

    @When("user enters username and password than verifies login")
    public void user_enters_username_and_password_than_verifies_login(DataTable dataTable) {
        List<Map<String, String>> userCredentials = dataTable.asMaps();
        for (Map<String, String> userCredential : userCredentials) {
            String username = userCredential.get("username");
            String password = userCredential.get("password");
            sendText(login.usernameTextBox, "username");
            sendText(login.passwordTextBox, "password");
            doClick(login.loginButton);
            doClick(login.welcomeIcon);
            doClick(login.logout);
        }
    }
}
