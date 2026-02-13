package automation.forms;

import automation.pages.BasePage;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import java.util.List;

public class GenericFormWrapper<F extends BasePage> extends BasePage {

    protected F page;

    public GenericFormWrapper(WebDriver driver, F page) {

        super(driver);
        this.page = page;
    }

    protected void type(By locator, String text) {
        WebElement element = wait.until(
                ExpectedConditions.visibilityOfElementLocated(locator)
        );
        element.clear();
        element.sendKeys(text);
    }

    protected void click(By locator) {
        WebElement element = wait.until(
                ExpectedConditions.elementToBeClickable(locator)
        );
        element.click();
    }

    protected String getText(By locator) {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(locator)
        ).getText();
    }

    protected boolean isDisplayed(By locator) {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(locator)
        ).isDisplayed();
    }

    protected List<WebElement> getElements(By locator) {
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
        return driver.findElements(locator);
    }
}
