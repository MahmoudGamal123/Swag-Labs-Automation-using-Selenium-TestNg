package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.Random;

/**
 * Page Object for the Product page.
 */
public class ProductPage extends BasePage {
    private final By titleProduct = By.xpath("//span[text()='Products']");
    private final By cartButton = By.xpath("//button[text()='Add to cart']");
    private final By cartIcon = By.xpath("//*[@id=\"shopping_cart_container\"]/a");
    private final By sortDropDown = By.xpath("//select[@class='product_sort_container']");
    private final By cartBadgeNumbers = By.xpath("//span[@data-test='shopping-cart-badge']");

    public ProductPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Selects a sort option by visible text.
     */
    public void selectSortByVisibleText(String text) {
        WebElement dropDown = wait.until(ExpectedConditions.visibilityOfElementLocated(sortDropDown));
        Select select = new Select(dropDown);
        select.selectByVisibleText(text);
    }

    /**
     * Selects a sort option by value.
     */
    public void selectSortByValue(String value) {
        WebElement dropDown = wait.until(ExpectedConditions.visibilityOfElementLocated(sortDropDown));
        Select select = new Select(dropDown);
        select.selectByValue(value);
    }

    /**
     * Adds random products to the cart.
     */
    public void clickProductAddToCart(String productName) {
        List<WebElement> listOfElements = driver.findElements(cartButton);
        Random random = new Random();
        int randomClicks = random.nextInt(6) + 1;
        for (int i = 0; i < randomClicks; i++) {
            listOfElements.get(i).click();
        }
    }

    /**
     * Gets the product page title.
     */
    public String getProductPageTitle() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(titleProduct)).getText();
    }

    /**
     * Gets the cart badge number.
     */
    public int getCartBadgeNumbers() {
        String badgeText = wait.until(ExpectedConditions.visibilityOfElementLocated(cartBadgeNumbers)).getText();
        return Integer.parseInt(badgeText);
    }
}
