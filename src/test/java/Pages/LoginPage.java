package Pages;

import Utils.CommonMethods;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends CommonMethods {

    public LoginPage() {
        PageFactory.initElements(driver, this);
    }

    //We want to initialize all web elements using driver on this page.

    @FindBy(id = "txtUsername")
    public WebElement usernameTextBox;

    @FindBy(id = "txtPassword")
    public WebElement passwordTextBox;

    @FindBy(id = "btnLogin")
    public WebElement loginButton;

    @FindBy(id = "welcome")
    public WebElement welcomeIcon;

    @FindBy(xpath = "//a[text()='Logout']")
    public WebElement logout;

    //Page Factory Model


}
