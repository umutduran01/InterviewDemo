package StepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Login {

    WebDriver driver;

    @Given("open the browser and launch HRMS application")
    public void open_the_browser_and_launch_hrms_application() {
        driver = new ChromeDriver();
        driver.get("http://hrm.syntaxtechs.net/humanresources/symfony/web/index.php/auth/login");
        driver.manage().window().maximize();
    }

    @When("user enter valid email and valid password")
    public void user_enter_valid_email_and_valid_password() {
        WebElement username = driver.findElement(By.id("txtUsername"));
        username.sendKeys("Admin");
        WebElement password = driver.findElement(By.id("txtPassword"));
        password.sendKeys("Hum@nhrm123");
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
}
