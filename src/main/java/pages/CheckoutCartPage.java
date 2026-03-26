package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Page Object for the Checkout Cart page.
 */
public class CheckoutCartPage extends BasePage {
    private final By checkoutButton = By.id("checkout");
    private final By pageTitle = By.xpath("//span[text()='Your Cart']");

    public CheckoutCartPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Clicks the checkout button to proceed to checkout.
     */
    public void clickCheckout() {
        driver.findElement(checkoutButton).click();
    }

    /**
     * Gets the checkout page title.
     */
    public String getCheckoutPageTitle() {
        return driver.findElement(pageTitle).getText();
    }
}
