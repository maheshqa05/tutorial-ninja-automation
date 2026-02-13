package automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage extends BasePage {
    public By emailField = By.id("input-email");
    public By passwordField = By.id("input-password");
    public By loginButton = By.cssSelector("input[type='submit']");
    public By logoutLink = By.linkText("Logout");
    public By loginError = By.cssSelector(".alert-danger");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void open() {
        driver.get("https://tutorialsninja.com/demo/index.php?route=account/login");
    }

    public String getLoginErrorMessage() {
        return waitForElement(loginError).getText();
    }


    public void enterEmail(String email) {
        type(emailField, email);
    }

    public void enterPassword(String password) {
        type(passwordField, password);
    }

    public void clickLogin() {
        driver.findElement(loginButton).click();
    }

    public boolean isLoggedIn() {

        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.linkText("Logout")
            ));

            return true;

        } catch (Exception e) {
            return false;
        }
    }

}
