package automation.stepdefs;

import automation.forms.RegistrationForm;
import automation.pages.RegistrationPage;
import automation.utilities.DriverFactory;
import automation.utilities.TestUserContext;
import automation.validators.FieldValidator;
import automation.validators.InvalidEmailValidator;
import automation.validators.RequiredFieldValidator;
import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertTrue;

public class RegistrationSteps {

    private RegistrationPage registrationPage;
    private RegistrationForm registrationForm;

    @Given("user is on registration page")
    public void user_is_on_registration_page() {
        WebDriver driver = DriverFactory.getDriver();
        registrationPage = new RegistrationPage(DriverFactory.getDriver());
        registrationForm = new RegistrationForm(driver, registrationPage);
        registrationPage.open();
    }

    @When("user registers with valid details")
    public void register_with_valid_details() {

        String email = "john" + System.currentTimeMillis() + "@test.com";
        String password = "Password123";

        registrationForm.submit(
                "John",
                "Doe",
                email,
                "1234567890",
                password,
                password,
                true
        );

        TestUserContext.setUser(email, password);
    }

    @Then("account should be created successfully")
    public void verify_account_created() {
        assertTrue(registrationPage.isAccountCreated());
    }

    @When("user submits registration with invalid data")
    public void submit_invalid_registration() {

        List<FieldValidator> validators = List.of(
                new RequiredFieldValidator("firstName"),
                new RequiredFieldValidator("lastName"),
                new RequiredFieldValidator("telephone"),
                new RequiredFieldValidator("password"),
                new InvalidEmailValidator()
        );

        validators.forEach(v -> v.invalidate(registrationPage));

        registrationForm.submit("", "", "", "", "", "", false);
    }


    @Then("all registration error messages should be shown")
    public void verify_errors() {

        List<String> expectedFields = List.of(
                "firstName",
                "lastName",
                "email",
                "telephone",
                "password"
        );

        Map<String, String> errors = registrationPage.getErrors();


        expectedFields.forEach(field ->
                assertTrue(errors.containsKey(field))
        );
    }
}