import pages.LoginPage;
import providers.DataProviders;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

/**
 * Test class for negative login scenarios.
 */
public class NegativeLoginTest extends BaseTest {

    @Test(dataProviderClass = DataProviders.class, dataProvider = "negativeLoginDataProvider")
    public void negativeLoginTest(String username, String password, String expectedErrorMessage) {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(username, password);

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(loginPage.getErrorMessageText(), expectedErrorMessage);
        softAssert.assertTrue(loginPage.isErrorMessageDisplayed());
        softAssert.assertAll();
    }
}
