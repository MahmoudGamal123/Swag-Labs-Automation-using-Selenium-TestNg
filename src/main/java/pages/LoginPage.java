package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.time.Duration;
import java.util.NoSuchElementException;

/**
 * Page Object for the Login page.
 */
public class LoginPage extends BasePage {
    private final By userNameLocator = By.id("user-name");
    private final By passwordLoginLocator = By.id("password");
    private final By buttonLoginLocator = By.id("login-button");
    private final By errorMessage = By.xpath("//h3[@data-test ='error']");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Performs login with the given credentials.
     */
    public void login(String username, String password) {
        driver.findElement(userNameLocator).sendKeys(username);
        driver.findElement(passwordLoginLocator).sendKeys(password);

        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(15))
                .pollingEvery(Duration.ofSeconds(1))
                .ignoring(NoSuchElementException.class);
        WebElement buttonLogin = wait.until(driver -> driver.findElement(buttonLoginLocator));
        buttonLogin.click();
    }

    /**
     * Checks if the error message is displayed.
     */
    public boolean isErrorMessageDisplayed() {
        return driver.findElement(errorMessage).isDisplayed();
    }

    /**
     * Gets the error message text.
     */
    public String getErrorMessageText() {
        return driver.findElement(errorMessage).getText();
    }
}
