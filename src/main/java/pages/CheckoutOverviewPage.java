package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Page Object for the Checkout Overview page.
 */
public class CheckoutOverviewPage extends BasePage {
    private final By pageTitle = By.cssSelector("span.title");
    private final By finishButton = By.id("finish");
    private final By cancelButton = By.id("cancel");
    private final By itemTotalLabel = By.cssSelector(".summary_subtotal_label");
    private final By taxLabel = By.cssSelector(".summary_tax_label");
    private final By totalLabel = By.cssSelector(".summary_total_label");
    private final By overviewItems = By.cssSelector(".cart_item");

    public CheckoutOverviewPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Gets the overview page title.
     */
    public String getPageTitle() {
        return getText(pageTitle);
    }

    /**
     * Clicks the finish button to complete the checkout process.
     */
    public void clickFinish() {
        click(finishButton);
    }

    /**
     * Cancels checkout and returns to the inventory page.
     */
    public void clickCancel() {
        click(cancelButton);
    }

    /**
     * Gets the number of items shown in the order summary.
     */
    public int getOverviewItemCount() {
        return findAll(overviewItems).size();
    }

    /**
     * Gets the subtotal value.
     */
    public double getItemTotal() {
        return parseAmount(getText(itemTotalLabel));
    }

    /**
     * Gets the tax value.
     */
    public double getTaxAmount() {
        return parseAmount(getText(taxLabel));
    }

    /**
     * Gets the total value.
     */
    public double getTotalAmount() {
        return parseAmount(getText(totalLabel));
    }

    private double parseAmount(String label) {
        return Double.parseDouble(label.substring(label.indexOf('$') + 1).trim());
    }
}
