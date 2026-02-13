package automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RegistrationPage extends BasePage {

    public By firstNameField = By.id("input-firstname");
    public By lastNameField = By.id("input-lastname");
    public By emailField = By.id("input-email");
    public By telephoneField = By.id("input-telephone");
    public By passwordField = By.id("input-password");
    public By confirmPasswordField = By.id("input-confirm");
    public By continueButton = By.cssSelector("input[type='submit']");
    public By errorMessages = By.cssSelector(".text-danger");
    public By privacyPolicyCheckbox = By.name("agree");

    public RegistrationPage(WebDriver driver) {
        super(driver);
        System.out.println("Driver =" + driver);
    }

    public void open() {
        System.out.println(driver);
        driver.get("https://tutorialsninja.com/demo/index.php?route=account/register");
    }

    public void enterEmail(String email) {
        driver.findElement(emailField).clear();
        driver.findElement(emailField).sendKeys(email);
    }

    public void enterTelephone(String phone) {
        driver.findElement(telephoneField).clear();
        driver.findElement(telephoneField).sendKeys(phone);
    }

    public void enterPassword(String password) {
        driver.findElement(passwordField).clear();
        driver.findElement(passwordField).sendKeys(password);
    }

    public void enterConfirmPassword(String confirm) {
        driver.findElement(confirmPasswordField).clear();
        driver.findElement(confirmPasswordField).sendKeys(confirm);
    }

    public void acceptPolicy() {
        driver.findElement(privacyPolicyCheckbox).click();
    }

    public void clickContinue() {
        driver.findElement(continueButton).click();
    }

    public boolean isAccountCreated() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            wait.until(ExpectedConditions.titleContains("Your Account Has Been Created"));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private final Map<String, By> fieldMap = new HashMap<>();

    {
        fieldMap.put("firstName", By.id("input-firstname"));
        fieldMap.put("lastName", By.id("input-lastname"));
        fieldMap.put("email", By.id("input-email"));
        fieldMap.put("telephone", By.id("input-telephone"));
        fieldMap.put("password", By.id("input-password"));
        fieldMap.put("confirm", By.id("input-confirm"));
    }

    public void clearField(String fieldName) {

        By locator = fieldMap.get(fieldName);

        if (locator == null) {
            throw new IllegalArgumentException("Unknown field: " + fieldName);
        }

        driver.findElement(locator).clear();
    }

    public Map<String, String> getErrors() {

        By errorLocator = By.cssSelector(".text-danger");

        wait.until(driver ->
                driver.findElements(errorLocator).size() > 0
        );

        Map<String, String> errorMap = new HashMap<>();

        List<WebElement> errorElements =
                driver.findElements(By.cssSelector(".text-danger"));

        for (WebElement element : errorElements) {

            String message = element.getText();

            if (message.contains("First Name")) {
                errorMap.put("firstName", message);
            } else if (message.contains("Last Name")) {
                errorMap.put("lastName", message);
            } else if (message.contains("E-Mail")) {
                errorMap.put("email", message);
            } else if (message.contains("Telephone")) {
                errorMap.put("telephone", message);
            } else if (message.contains("Password")) {
                errorMap.put("password", message);
            }
        }
        List<WebElement> alert =
                driver.findElements(By.cssSelector(".alert-danger"));

        if (!alert.isEmpty()) {
            String alertText = alert.get(0).getText();
            if (alertText.contains("Privacy Policy")) {
                errorMap.put("privacyPolicy", alertText);
            }
        }

        return errorMap;
    }

}
