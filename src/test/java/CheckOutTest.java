import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.CheckoutCartPage;
import pages.CheckoutCompletePage;
import pages.CheckoutInformationPage;
import pages.CheckoutOverviewPage;
import pages.LoginPage;
import pages.ProductPage;
import providers.DataProviders;

/**
 * Test class for checkout functionality.
 */
@Epic("Swag Labs UI Tests")
@Feature("Checkout")
public class CheckOutTest extends BaseTest {

    @BeforeMethod
    public void validLogin() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");
    }

    @Test(dataProviderClass = DataProviders.class, dataProvider = "checkoutFlowDataProvider")
    public void completeCheckout(
            String testCaseId,
            String scenario,
            String[] productNames,
            String firstName,
            String lastName,
            String postalCode
    ) {
        markTestCase(testCaseId, scenario);
        ProductPage productPage = new ProductPage(driver);
        for (String productName : productNames) {
            productPage.addProductToCart(productName);
        }
        productPage.openCart();

        CheckoutCartPage checkoutCartPage = new CheckoutCartPage(driver);
        Assert.assertEquals(checkoutCartPage.getCheckoutPageTitle(), "Your Cart", "Cart page is not displayed.");
        checkoutCartPage.clickCheckout();

        CheckoutInformationPage checkoutInfoPage = new CheckoutInformationPage(driver);
        checkoutInfoPage.enterCheckoutInformation(firstName, lastName, postalCode);

        CheckoutOverviewPage checkoutOverviewPage = new CheckoutOverviewPage(driver);
        Assert.assertEquals(checkoutOverviewPage.getOverviewItemCount(), productNames.length, "Overview item count mismatch.");
        checkoutOverviewPage.clickFinish();

        CheckoutCompletePage completePage = new CheckoutCompletePage(driver);
        Assert.assertEquals(completePage.getPageTitle(), "Checkout: Complete!");
        Assert.assertEquals(completePage.getConfirmationHeader(), "Thank you for your order!");
        Assert.assertTrue(
                completePage.getConfirmationText().contains("Your order has been dispatched"),
                "Unexpected checkout completion message."
        );
    }

    @Test(dataProviderClass = DataProviders.class, dataProvider = "checkoutSummaryDataProvider")
    public void orderSummaryShouldCalculateTotals(
            String testCaseId,
            String scenario,
            String[] productNames,
            double expectedSubtotal,
            double expectedTax,
            double expectedTotal
    ) {
        markTestCase(testCaseId, scenario);
        CheckoutOverviewPage checkoutOverviewPage = navigateToOverview(productNames);

        Assert.assertEquals(checkoutOverviewPage.getItemTotal(), expectedSubtotal, 0.01, "Subtotal mismatch.");
        Assert.assertEquals(checkoutOverviewPage.getTaxAmount(), expectedTax, 0.01, "Tax mismatch.");
        Assert.assertEquals(checkoutOverviewPage.getTotalAmount(), expectedTotal, 0.01, "Total mismatch.");
    }

    @Test
    public void cancelFromInformationShouldReturnToCart() {
        markTestCase("TC-049", "Cancel on checkout information should return the user to the cart page.");
        ProductPage productPage = new ProductPage(driver);
        productPage.addProductToCart("Sauce Labs Backpack");
        productPage.openCart();

        CheckoutCartPage checkoutCartPage = new CheckoutCartPage(driver);
        checkoutCartPage.clickCheckout();

        CheckoutInformationPage checkoutInformationPage = new CheckoutInformationPage(driver);
        checkoutInformationPage.clickCancel();

        Assert.assertEquals(checkoutCartPage.getCheckoutPageTitle(), "Your Cart");
    }

    @Test
    public void cancelFromOverviewShouldReturnToProducts() {
        markTestCase("TC-050", "Cancel on checkout overview should return the user to the products page.");
        CheckoutOverviewPage checkoutOverviewPage = navigateToOverview(new String[]{"Sauce Labs Backpack"});
        checkoutOverviewPage.clickCancel();

        ProductPage productPage = new ProductPage(driver);
        Assert.assertTrue(productPage.isOnProductsPage(), "Products page should be displayed after cancelling from overview.");
    }

    private CheckoutOverviewPage navigateToOverview(String[] productNames) {
        ProductPage productPage = new ProductPage(driver);
        for (String productName : productNames) {
            productPage.addProductToCart(productName);
        }
        productPage.openCart();

        CheckoutCartPage cartPage = new CheckoutCartPage(driver);
        cartPage.clickCheckout();

        CheckoutInformationPage informationPage = new CheckoutInformationPage(driver);
        informationPage.enterCheckoutInformation("John", "Doe", "12345");

        return new CheckoutOverviewPage(driver);
    }
}
