package Utils;

import StepDefinitions.PageInitializer;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.Select;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;

public class CommonMethods extends PageInitializer {

    public static WebDriver driver;

    public static void openBrowserAndLaunchApplication() {

        ConfigReader.readProperties(Constants.PROPERTIES_FILE_PATH);

        String browserType = ConfigReader.getPropertyValue("browserType");
        switch (browserType) {
            case "Chrome":
                ChromeOptions ops = new ChromeOptions();
                ops.addArguments("--no-sandbox");
                ops.addArguments("--remote-allow-origins=*");
                if (ConfigReader.getPropertyValue("Headless").equals("true")) {
                    ops.addArguments("--headless=new");
                }
                driver = new ChromeDriver(ops);
                break;
            case "Firefox":
                driver = new FirefoxDriver();
                break;
            case "IE":
                driver = new InternetExplorerDriver();
            default:
                driver = new EdgeDriver();
                break;
        }

        driver.get(ConfigReader.getPropertyValue("url"));
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(Constants.WAIT_TIME));
        initializePageObjects();

        //To configure the File and pattern it has

        DOMConfigurator.configure("log4j.xml");
        Log.startTestCase("This is the beginning of my test case.");
        Log.info("My test case is executing.");
        Log.warning("My test case might have some important issues.");
    }

    public static void closeBrowser() {
        Log.info("This test case is about to get completed.");
        Log.endTestCase("This test case has finished.");
        driver.close();
    }

    public static void doClick(WebElement element) {
        element.click();
    }

    public static void sendText(WebElement element, String value) {
        element.sendKeys(ConfigReader.getPropertyValue(value));
    }

    public static Select clickOnDropdown(WebElement element) {
        Select select = new Select(element);
        return select;
    }

    public static void selectByValue(WebElement element, String value) {
        clickOnDropdown(element).selectByValue(value);
    }

    public static void selectByVisibleText(WebElement element, String text) {
        clickOnDropdown(element).selectByVisibleText(text);
    }

    public static void selectByIndex(WebElement element, int index) {
        clickOnDropdown(element).selectByIndex(index);
    }

    public static void selectByOption(WebElement element, String text) {
        List<WebElement> options = clickOnDropdown(element).getOptions();
        for (WebElement option : options) {
            String ddlOptionText = option.getText();
            if (ddlOptionText.equalsIgnoreCase(text)) {
                option.click();
            }
        }
    }

    public static byte[] takeScreenshot(String imageName) {
        //This casts the webDriver instance to TakesScreenshot interface.
        TakesScreenshot ts = (TakesScreenshot) driver;

        //This captures screenshots and stores as byte array.
        byte[] picBytes = ts.getScreenshotAs(OutputType.BYTES);

        //This captures screenshots and stores as a file in sourceFile variable.
        File sourcePath = ts.getScreenshotAs(OutputType.FILE);

        try {
            FileUtils.copyFile(sourcePath, new File(Constants.SCREENSHOT_FILEPATH + imageName + getTimeStamp("yyyy-MM-dd-HH-mm-ss") + ".png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return picBytes;
    }

    public static String getTimeStamp(String pattern) {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(date);
    }
}
