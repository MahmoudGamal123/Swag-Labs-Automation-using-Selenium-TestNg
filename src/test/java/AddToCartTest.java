import pages.LoginPage;
import pages.ProductPage;
import providers.DataProviders;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Test class for add to cart functionality.
 */
public class AddToCartTest extends BaseTest {

    @BeforeMethod
    public void validLogin() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");
    }

    @Test(dataProviderClass = DataProviders.class, dataProvider = "productNameDataProvider")
    public void addToCartTest(String productName) {
        ProductPage product = new ProductPage(driver);
        product.selectSortByVisibleText("Name (Z to A)");
        product.clickProductAddToCart(productName);
        Assert.assertEquals(
                product.getProductPageTitle(),
                "Products",
                "Products page is NOT displayed after login"
        );
    }
}
