import pages.LoginPage;
import pages.ProductPage;
import pages.CheckoutCartPage;
import pages.CheckoutInformationPage;
import pages.CheckoutOverviewPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Test class for checkout functionality.
 */
public class CheckOutTest extends BaseTest {

    @BeforeMethod
    public void validLogin() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");
    }

    @Test
    public void completeCheckout() {
        // Add a product to cart
        ProductPage productPage = new ProductPage(driver);
        productPage.clickProductAddToCart("sauce-labs-backpack");

        // Go to checkout
        CheckoutCartPage checkoutCartPage = new CheckoutCartPage(driver);
        Assert.assertEquals(
                checkoutCartPage.getCheckoutPageTitle(),
                "Your Cart",
                "Cart page is NOT displayed"
        );
        checkoutCartPage.clickCheckout();

        // Enter checkout information
        CheckoutInformationPage checkoutInfoPage = new CheckoutInformationPage(driver);
        checkoutInfoPage.enterCheckoutInformation("John", "Doe", "12345");

        // Complete checkout
        CheckoutOverviewPage checkoutOverviewPage = new CheckoutOverviewPage(driver);
        checkoutOverviewPage.clickFinish();
    }
}
