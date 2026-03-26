package providers;

import org.testng.annotations.DataProvider;

/**
 * Provides test data for various test scenarios.
 */
public class DataProviders {

    @DataProvider(name = "loginDataProvider")
    public Object[][] getLoginData() {
        return new Object[][]{
                {"standard_user", "secret_sauce"},
                {"problem_user", "secret_sauce"},
                {"performance_glitch_user", "secret_sauce"},
                {"error_user", "secret_sauce"},
                {"visual_user", "secret_sauce"}
        };
    }

    @DataProvider(name = "negativeLoginDataProvider")
    public Object[][] getNegativeLoginData() {
        return new Object[][]{
                {"", "secret_sauce", "Epic sadface: Username is required"},
                {"standard_user", "", "Epic sadface: Password is required"},
                {"invalid_user", "invalid_password", "Epic sadface: Username and password do not match any user in this service"}
        };
    }

    @DataProvider(name = "productNameDataProvider")
    public Object[][] getProductNameData() {
        return new Object[][]{
                {"bike"},
                {"bolt-t-shirt"},
                {"fleece-jacket"},
                {"onesie"},
                {"sauce-labs-backpack"},
                {"test.allthethings()-t-shirt-(red)"}
        };
    }
}
