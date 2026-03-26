import pages.LoginPage;
import providers.DataProviders;
import org.testng.annotations.Test;

/**
 * Test class for login functionality.
 */
public class LoginTest extends BaseTest {

    @Test(dataProviderClass = DataProviders.class, dataProvider = "loginDataProvider")
    public void validLogin(String username, String password) {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(username, password);
    }
}
