package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

/**
 * Page Object for the Checkout Cart page.
 */
public class CheckoutCartPage extends BasePage {
    private final By checkoutButton = By.id("checkout");
    private final By continueShoppingButton = By.id("continue-shopping");
    private final By pageTitle = By.cssSelector("span.title");
    private final By cartItemNames = By.cssSelector(".cart_item .inventory_item_name");

    public CheckoutCartPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Clicks the checkout button to proceed to checkout.
     */
    public void clickCheckout() {
        click(checkoutButton);
    }

    /**
     * Returns to the inventory page.
     */
    public void clickContinueShopping() {
        click(continueShoppingButton);
    }

    /**
     * Gets the checkout page title.
     */
    public String getCheckoutPageTitle() {
        return getText(pageTitle);
    }

    /**
     * Gets the number of items in the cart.
     */
    public int getCartItemCount() {
        return findAll(cartItemNames).size();
    }

    /**
     * Gets the names of the items shown in the cart.
     */
    public List<String> getCartItemNames() {
        List<String> names = new ArrayList<>();
        for (WebElement itemName : findAll(cartItemNames)) {
            names.add(itemName.getText().trim());
        }
        return names;
    }

    /**
     * Removes the named item from the cart.
     */
    public void removeProduct(String productName) {
        String escapedProductName = escapeXpathText(productName);
        By removeButton = By.xpath(
                "//div[contains(@class,'cart_item')][.//*[contains(@class,'inventory_item_name') and normalize-space()="
                        + escapedProductName + "]]//button"
        );
        click(removeButton);
    }

    /**
     * Checks whether the named item is displayed in the cart.
     */
    public boolean isProductDisplayed(String productName) {
        String escapedProductName = escapeXpathText(productName);
        By productNameLocator = By.xpath(
                "//div[contains(@class,'cart_item')]//*[contains(@class,'inventory_item_name') and normalize-space()="
                        + escapedProductName + "]"
        );
        return !driver.findElements(productNameLocator).isEmpty();
    }

    private String escapeXpathText(String value) {
        if (!value.contains("'")) {
            return "'" + value + "'";
        }

        String[] parts = value.split("'");
        StringBuilder builder = new StringBuilder("concat(");
        for (int i = 0; i < parts.length; i++) {
            if (i > 0) {
                builder.append(", \"'\", ");
            }
            builder.append("'").append(parts[i]).append("'");
        }
        builder.append(")");
        return builder.toString();
    }
}
