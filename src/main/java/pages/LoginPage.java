package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Page Object for the Login page.
 */
public class LoginPage extends BasePage {
    private final By userNameLocator = By.id("user-name");
    private final By passwordLoginLocator = By.id("password");
    private final By buttonLoginLocator = By.id("login-button");
    private final By errorMessage = By.cssSelector("h3[data-test='error']");
    private final By loginLogo = By.className("login_logo");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Performs login with the given credentials.
     */
    public void login(String username, String password) {
        type(userNameLocator, username);
        type(passwordLoginLocator, password);
        click(buttonLoginLocator);
    }

    /**
     * Checks if the error message is displayed.
     */
    public boolean isErrorMessageDisplayed() {
        return isDisplayed(errorMessage);
    }

    /**
     * Gets the error message text.
     */
    public String getErrorMessageText() {
        return getText(errorMessage);
    }

    /**
     * Checks whether the login page is still displayed.
     */
    public boolean isOnLoginPage() {
        return isDisplayed(loginLogo);
    }
}
