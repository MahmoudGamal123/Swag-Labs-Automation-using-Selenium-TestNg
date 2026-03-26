import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.CheckoutCartPage;
import pages.CheckoutInformationPage;
import pages.CheckoutOverviewPage;
import pages.LoginPage;
import pages.ProductPage;
import providers.DataProviders;

/**
 * Test class for checkout information validation.
 */
@Epic("Swag Labs UI Tests")
@Feature("Checkout Information")
public class CheckoutInformationTest extends BaseTest {

    @BeforeMethod
    public void navigateToCheckoutInformationPage() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");

        ProductPage productPage = new ProductPage(driver);
        productPage.addProductToCart("Sauce Labs Backpack");
        productPage.openCart();

        CheckoutCartPage cartPage = new CheckoutCartPage(driver);
        cartPage.clickCheckout();
    }

    @Test(dataProviderClass = DataProviders.class, dataProvider = "checkoutInvalidDataProvider")
    public void invalidCheckoutInformationShouldShowValidation(
            String testCaseId,
            String scenario,
            String firstName,
            String lastName,
            String postalCode,
            String expectedError
    ) {
        markTestCase(testCaseId, scenario);
        CheckoutInformationPage informationPage = new CheckoutInformationPage(driver);
        informationPage.enterCheckoutInformation(firstName, lastName, postalCode);

        Assert.assertEquals(informationPage.getPageTitle(), "Checkout: Your Information");
        Assert.assertTrue(informationPage.isErrorMessageDisplayed(), "Validation error should be shown.");
        Assert.assertEquals(informationPage.getErrorMessageText(), expectedError);
    }

    @Test(dataProviderClass = DataProviders.class, dataProvider = "checkoutValidDataProvider")
    public void validCheckoutInformationShouldOpenOverview(
            String testCaseId,
            String scenario,
            String firstName,
            String lastName,
            String postalCode
    ) {
        markTestCase(testCaseId, scenario);
        CheckoutInformationPage informationPage = new CheckoutInformationPage(driver);
        informationPage.enterCheckoutInformation(firstName, lastName, postalCode);

        CheckoutOverviewPage overviewPage = new CheckoutOverviewPage(driver);
        Assert.assertEquals(overviewPage.getPageTitle(), "Checkout: Overview");
    }
}
