package automation.stepdefs;

import automation.forms.LoginForm;
import automation.pages.LoginPage;
import automation.utilities.ConfigReader;
import automation.utilities.DriverFactory;
import io.cucumber.java.en.*;
import automation.utilities.TestUserContext;

import static org.junit.Assert.assertTrue;

public class LoginSteps {

    private LoginPage loginPage;
    private LoginForm loginForm;

    @Given("user is on login page")
    public void open_login_page() {

        loginPage = new LoginPage(DriverFactory.getDriver());
        loginForm = new LoginForm(DriverFactory.getDriver(), loginPage);

        loginPage.open();
    }

    @When("user logs in with valid credentials")
    public void login_valid() {

        loginForm.submit(
                ConfigReader.get("valid.email"),
                ConfigReader.get("valid.password")
        );
    }

    @Then("login should be successful")
    public void verify_login_success() {
        assertTrue(loginPage.isLoggedIn());
    }

    @When("user logs in with invalid credentials")
    public void login_invalid() {

        TestUserContext.generateInvalidUser();

        loginForm.submit(
                TestUserContext.getEmail(),
                TestUserContext.getPassword()
        );
    }

    @Then("login error message should be displayed")
    public void verify_login_error() {
        String actual = loginPage.getLoginErrorMessage();
        assertTrue(actual.contains("Warning: No match for E-Mail Address and/or Password."));
    }
}