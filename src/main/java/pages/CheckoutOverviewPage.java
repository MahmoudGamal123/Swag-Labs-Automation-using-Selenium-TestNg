package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Page Object for the Checkout Overview page.
 */
public class CheckoutOverviewPage extends BasePage {
    private final By finishButton = By.id("finish");

    public CheckoutOverviewPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Clicks the finish button to complete the checkout process.
     */
    public void clickFinish() {
        driver.findElement(finishButton).click();
    }
}
