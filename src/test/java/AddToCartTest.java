import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.ProductPage;
import providers.DataProviders;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Test class for inventory and add to cart functionality.
 */
@Epic("Swag Labs UI Tests")
@Feature("Inventory")
public class AddToCartTest extends BaseTest {

    @BeforeMethod
    public void validLogin() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");
    }

    @Test
    public void inventoryShouldDisplaySixProducts() {
        markTestCase("TC-015", "Inventory page should list all six available products.");
        ProductPage productPage = new ProductPage(driver);

        Assert.assertEquals(productPage.getInventoryItemCount(), 6, "Unexpected inventory count.");
    }

    @Test(dataProviderClass = DataProviders.class, dataProvider = "inventorySortDataProvider")
    public void sortingShouldReorderInventory(String testCaseId, String scenario, String sortOption) {
        markTestCase(testCaseId, scenario);
        ProductPage productPage = new ProductPage(driver);
        productPage.selectSortByVisibleText(sortOption);
        Assert.assertEquals(productPage.getSelectedSortOption(), sortOption, "Selected sort option mismatch.");

        if (sortOption.startsWith("Name")) {
            List<String> actualNames = productPage.getInventoryItemNames();
            List<String> expectedNames = new ArrayList<>(actualNames);
            Collections.sort(expectedNames);
            if ("Name (Z to A)".equals(sortOption)) {
                Collections.reverse(expectedNames);
            }
            Assert.assertEquals(actualNames, expectedNames, "Product names were not sorted as expected.");
        } else {
            List<Double> actualPrices = productPage.getInventoryItemPrices();
            List<Double> expectedPrices = new ArrayList<>(actualPrices);
            Collections.sort(expectedPrices);
            if ("Price (high to low)".equals(sortOption)) {
                Collections.reverse(expectedPrices);
            }
            Assert.assertEquals(actualPrices, expectedPrices, "Product prices were not sorted as expected.");
        }
    }

    @Test(dataProviderClass = DataProviders.class, dataProvider = "singleProductDataProvider")
    public void productCanBeAddedToCartFromInventory(String testCaseId, String scenario, String productName) {
        markTestCase(testCaseId, scenario);
        ProductPage productPage = new ProductPage(driver);
        productPage.addProductToCart(productName);

        Assert.assertEquals(productPage.getCartBadgeCount(), 1, "Cart badge count should be 1.");
        Assert.assertEquals(productPage.getProductButtonText(productName), "Remove");
    }

    @Test(dataProviderClass = DataProviders.class, dataProvider = "inventoryRemovalDataProvider")
    public void productCanBeRemovedFromInventory(String testCaseId, String scenario, String productName) {
        markTestCase(testCaseId, scenario);
        ProductPage productPage = new ProductPage(driver);
        productPage.addProductToCart(productName);
        productPage.removeProductFromCart(productName);

        Assert.assertEquals(productPage.getCartBadgeCount(), 0, "Cart badge should be cleared after removal.");
        Assert.assertEquals(productPage.getProductButtonText(productName), "Add to cart");
    }
}
