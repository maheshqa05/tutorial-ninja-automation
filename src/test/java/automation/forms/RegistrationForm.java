package automation.forms;

import automation.pages.RegistrationPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RegistrationForm extends GenericFormWrapper<RegistrationPage> {

    public RegistrationForm(WebDriver driver, RegistrationPage page) {
        super(driver, page);
    }

    public void submit(String firstName,
                       String lastName,
                       String email,
                       String telephone,
                       String password,
                       String confirmPassword,
                       boolean acceptPolicy) {

        type(page.firstNameField, firstName);
        type(page.lastNameField, lastName);
        type(page.emailField, email);
        type(page.telephoneField, telephone);
        type(page.passwordField, password);
        type(page.confirmPasswordField, confirmPassword);
        if (acceptPolicy) {
            page.acceptPolicy();
        }

        click(page.continueButton);
    }

    public Map<String, String> getErrors() {
        List<WebElement> elements = getElements(page.errorMessages);

        Map<String, String> errorMap = new HashMap<>();

        for (WebElement element : elements) {
            String message = element.getText();

            if (message.contains("First Name"))
                errorMap.put("firstName", message);
            if (message.contains("Last Name"))
                errorMap.put("lastName", message);
            if (message.contains("E-Mail"))
                errorMap.put("email", message);
            if (message.contains("Telephone"))
                errorMap.put("telephone", message);
            if (message.contains("Password"))
                errorMap.put("password", message);
        }

        return errorMap;
    }
}
