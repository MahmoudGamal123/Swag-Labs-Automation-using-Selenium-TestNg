package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Page Object for the checkout completion page.
 */
public class CheckoutCompletePage extends BasePage {
    private final By pageTitle = By.cssSelector("span.title");
    private final By confirmationHeader = By.cssSelector(".complete-header");
    private final By confirmationText = By.cssSelector(".complete-text");
    private final By backHomeButton = By.id("back-to-products");

    public CheckoutCompletePage(WebDriver driver) {
        super(driver);
    }

    /**
     * Gets the completion page title.
     */
    public String getPageTitle() {
        return getText(pageTitle);
    }

    /**
     * Gets the order confirmation headline.
     */
    public String getConfirmationHeader() {
        return getText(confirmationHeader);
    }

    /**
     * Gets the order confirmation message.
     */
    public String getConfirmationText() {
        return getText(confirmationText);
    }

    /**
     * Returns to the product page.
     */
    public void clickBackHome() {
        click(backHomeButton);
    }
}
