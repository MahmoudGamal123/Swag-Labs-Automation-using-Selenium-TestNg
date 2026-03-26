import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.CheckoutCartPage;
import pages.LoginPage;
import pages.ProductPage;
import providers.DataProviders;

import java.util.Arrays;
import java.util.List;

/**
 * Test class for cart functionality.
 */
@Epic("Swag Labs UI Tests")
@Feature("Cart")
public class CartTest extends BaseTest {

    @BeforeMethod
    public void validLogin() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");
    }

    @Test(dataProviderClass = DataProviders.class, dataProvider = "cartContentsDataProvider")
    public void selectedProductsShouldAppearInCart(String testCaseId, String scenario, String[] products) {
        markTestCase(testCaseId, scenario);
        ProductPage productPage = new ProductPage(driver);
        for (String product : products) {
            productPage.addProductToCart(product);
        }

        productPage.openCart();
        CheckoutCartPage cartPage = new CheckoutCartPage(driver);
        List<String> cartItems = cartPage.getCartItemNames();

        Assert.assertEquals(cartPage.getCheckoutPageTitle(), "Your Cart");
        Assert.assertEquals(cartItems.size(), products.length, "Unexpected cart item count.");
        Assert.assertTrue(cartItems.containsAll(Arrays.asList(products)), "Cart is missing selected products.");
    }

    @Test(dataProviderClass = DataProviders.class, dataProvider = "cartRemovalDataProvider")
    public void productCanBeRemovedFromCart(String testCaseId, String scenario, String productName) {
        markTestCase(testCaseId, scenario);
        ProductPage productPage = new ProductPage(driver);
        productPage.addProductToCart(productName);
        productPage.openCart();

        CheckoutCartPage cartPage = new CheckoutCartPage(driver);
        cartPage.removeProduct(productName);

        Assert.assertEquals(cartPage.getCartItemCount(), 0, "Cart should be empty after removing the only product.");
        Assert.assertFalse(cartPage.isProductDisplayed(productName), "Removed product should no longer appear in the cart.");
    }

    @Test
    public void continueShoppingShouldReturnToProductsPage() {
        markTestCase("TC-033", "Continue Shopping from cart should return the user to the inventory page.");
        ProductPage productPage = new ProductPage(driver);
        productPage.addProductToCart("Sauce Labs Backpack");
        productPage.openCart();

        CheckoutCartPage cartPage = new CheckoutCartPage(driver);
        cartPage.clickContinueShopping();

        Assert.assertTrue(productPage.isOnProductsPage(), "Products page should be displayed after continuing shopping.");
    }
}
