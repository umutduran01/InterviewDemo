package StepDefinitions;

import Utils.CommonMethods;
import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Hooks extends CommonMethods {

    @Before
    public void preCondition() {
        openBrowserAndLaunchApplication();
    }

    @After
    public void postCondition() {
        closeBrowser();
    }

//    @Before(value = "@smoke")
//    public void preConditionWithPrint() {
//        openBrowserAndLaunchApplication();
//        System.out.println("This is a smoke test.");
//    }


}
