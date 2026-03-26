import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import providers.DataProviders;

/**
 * Test class for negative login scenarios.
 */
@Epic("Swag Labs UI Tests")
@Feature("Login")
public class NegativeLoginTest extends BaseTest {

    @Test(dataProviderClass = DataProviders.class, dataProvider = "negativeLoginDataProvider")
    public void negativeLoginTest(String testCaseId, String scenario, String username, String password, String expectedErrorMessage) {
        markTestCase(testCaseId, scenario);
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(username, password);

        Assert.assertTrue(loginPage.isOnLoginPage(), "Login page should remain visible for invalid login.");
        Assert.assertTrue(loginPage.isErrorMessageDisplayed(), "Error message should be displayed.");
        Assert.assertEquals(loginPage.getErrorMessageText(), expectedErrorMessage);
    }
}
