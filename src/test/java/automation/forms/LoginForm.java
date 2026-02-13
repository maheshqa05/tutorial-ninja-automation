package automation.forms;

import automation.pages.LoginPage;
import org.openqa.selenium.WebDriver;

public class LoginForm extends GenericFormWrapper<LoginPage> {

    public LoginForm(WebDriver driver, LoginPage loginPage) {
        super(driver, new LoginPage(driver));
    }

    public void submit(String email, String password) {
        type(page.emailField, email);
        type(page.passwordField, password);
        click(page.loginButton);
    }

    public boolean isLoggedIn() {
        return isDisplayed(page.logoutLink);
    }

    public String getErrorMessage() {
        return getText(page.loginError);
    }
}
