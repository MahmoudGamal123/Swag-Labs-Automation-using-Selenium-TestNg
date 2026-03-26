package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

/**
 * Page Object for the Product page.
 */
public class ProductPage extends BasePage {
    private final By titleProduct = By.cssSelector("span.title");
    private final By cartIcon = By.cssSelector(".shopping_cart_link");
    private final By sortDropDown = By.cssSelector(".product_sort_container");
    private final By cartBadgeNumbers = By.cssSelector("span[data-test='shopping-cart-badge']");
    private final By inventoryItems = By.cssSelector(".inventory_item");
    private final By inventoryItemNames = By.cssSelector(".inventory_item_name");
    private final By inventoryItemPrices = By.cssSelector(".inventory_item_price");

    public ProductPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Selects a sort option by visible text.
     */
    public void selectSortByVisibleText(String text) {
        WebElement dropDown = waitForVisible(sortDropDown);
        Select select = new Select(dropDown);
        select.selectByVisibleText(text);
    }

    /**
     * Gets the currently selected sort option.
     */
    public String getSelectedSortOption() {
        WebElement dropDown = waitForVisible(sortDropDown);
        Select select = new Select(dropDown);
        return select.getFirstSelectedOption().getText().trim();
    }

    /**
     * Adds the named product to the cart.
     */
    public void addProductToCart(String productName) {
        getProductButton(productName).click();
    }

    /**
     * Removes the named product from the cart.
     */
    public void removeProductFromCart(String productName) {
        getProductButton(productName).click();
    }

    /**
     * Gets the product page title.
     */
    public String getProductPageTitle() {
        return getText(titleProduct);
    }

    /**
     * Checks whether the products page is displayed.
     */
    public boolean isOnProductsPage() {
        return "Products".equals(getProductPageTitle());
    }

    /**
     * Gets the number of products displayed.
     */
    public int getInventoryItemCount() {
        return findAll(inventoryItems).size();
    }

    /**
     * Gets the displayed product names.
     */
    public List<String> getInventoryItemNames() {
        List<String> names = new ArrayList<>();
        for (WebElement element : findAll(inventoryItemNames)) {
            names.add(element.getText().trim());
        }
        return names;
    }

    /**
     * Gets the displayed product prices.
     */
    public List<Double> getInventoryItemPrices() {
        List<Double> prices = new ArrayList<>();
        for (WebElement element : findAll(inventoryItemPrices)) {
            prices.add(Double.parseDouble(element.getText().replace("$", "").trim()));
        }
        return prices;
    }

    /**
     * Gets the cart badge number.
     */
    public int getCartBadgeCount() {
        if (driver.findElements(cartBadgeNumbers).isEmpty()) {
            return 0;
        }
        return Integer.parseInt(getText(cartBadgeNumbers));
    }

    /**
     * Gets the current action button text for the named product.
     */
    public String getProductButtonText(String productName) {
        return getProductButton(productName).getText().trim();
    }

    /**
     * Opens the cart page.
     */
    public void openCart() {
        click(cartIcon);
    }

    private WebElement getProductButton(String productName) {
        String escapedProductName = escapeXpathText(productName);
        By productButton = By.xpath(
                "//div[@data-test='inventory-item'][.//*[@data-test='inventory-item-name' and normalize-space()="
                        + escapedProductName + "]]//button"
        );
        return waitForVisible(productButton);
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
