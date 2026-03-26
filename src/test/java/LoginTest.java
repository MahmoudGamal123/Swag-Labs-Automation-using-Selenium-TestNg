import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.ProductPage;
import providers.DataProviders;

/**
 * Test class for login functionality.
 */
@Epic("Swag Labs UI Tests")
@Feature("Login")
public class LoginTest extends BaseTest {

    @Test(dataProviderClass = DataProviders.class, dataProvider = "validLoginDataProvider")
    public void validLogin(String testCaseId, String scenario, String username, String password) {
        markTestCase(testCaseId, scenario);
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(username, password);

        ProductPage productPage = new ProductPage(driver);
        Assert.assertTrue(productPage.isOnProductsPage(), "Products page is not displayed after login.");
        Assert.assertEquals(productPage.getProductPageTitle(), "Products");
    }
}
