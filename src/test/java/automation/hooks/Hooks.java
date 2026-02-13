package automation.hooks;

import automation.utilities.DriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;

public class Hooks {

    @Before
    public void beforeScenario() {
        WebDriver driver = DriverFactory.getDriver();
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
    }

    @After
    public void afterScenario() {
        DriverFactory.quitDriver();
    }

}
