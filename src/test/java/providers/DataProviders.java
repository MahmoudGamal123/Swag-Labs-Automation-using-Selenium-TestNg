package providers;

import org.testng.annotations.DataProvider;

/**
 * Provides test data for various test scenarios.
 */
public class DataProviders {

    @DataProvider(name = "validLoginDataProvider")
    public Object[][] getValidLoginData() {
        return new Object[][]{
                {"TC-001", "Log in successfully with standard_user.", "standard_user", "secret_sauce"},
                {"TC-002", "Log in successfully with problem_user.", "problem_user", "secret_sauce"},
                {"TC-003", "Log in successfully with performance_glitch_user.", "performance_glitch_user", "secret_sauce"},
                {"TC-004", "Log in successfully with error_user.", "error_user", "secret_sauce"},
                {"TC-005", "Log in successfully with visual_user.", "visual_user", "secret_sauce"}
        };
    }

    @DataProvider(name = "negativeLoginDataProvider")
    public Object[][] getNegativeLoginData() {
        return new Object[][]{
                {"TC-006", "Locked out user should be blocked from logging in.", "locked_out_user", "secret_sauce", "Epic sadface: Sorry, this user has been locked out."},
                {"TC-007", "Blank username should display username required validation.", "", "secret_sauce", "Epic sadface: Username is required"},
                {"TC-008", "Blank password should display password required validation.", "standard_user", "", "Epic sadface: Password is required"},
                {"TC-009", "Blank username and password should display username required validation.", "", "", "Epic sadface: Username is required"},
                {"TC-010", "Invalid credentials should show mismatch validation.", "invalid_user", "invalid_password", "Epic sadface: Username and password do not match any user in this service"}
        };
    }

    @DataProvider(name = "inventorySortDataProvider")
    public Object[][] getInventorySortData() {
        return new Object[][]{
                {"TC-011", "Sort inventory by Name (A to Z).", "Name (A to Z)"},
                {"TC-012", "Sort inventory by Name (Z to A).", "Name (Z to A)"},
                {"TC-013", "Sort inventory by Price (low to high).", "Price (low to high)"},
                {"TC-014", "Sort inventory by Price (high to low).", "Price (high to low)"}
        };
    }

    @DataProvider(name = "singleProductDataProvider")
    public Object[][] getSingleProductData() {
        return new Object[][]{
                {"TC-016", "Add Sauce Labs Backpack to cart from inventory.", "Sauce Labs Backpack"},
                {"TC-017", "Add Sauce Labs Bike Light to cart from inventory.", "Sauce Labs Bike Light"},
                {"TC-018", "Add Sauce Labs Bolt T-Shirt to cart from inventory.", "Sauce Labs Bolt T-Shirt"},
                {"TC-019", "Add Sauce Labs Fleece Jacket to cart from inventory.", "Sauce Labs Fleece Jacket"},
                {"TC-020", "Add Sauce Labs Onesie to cart from inventory.", "Sauce Labs Onesie"},
                {"TC-021", "Add Test.allTheThings() T-Shirt (Red) to cart from inventory.", "Test.allTheThings() T-Shirt (Red)"}
        };
    }

    @DataProvider(name = "inventoryRemovalDataProvider")
    public Object[][] getInventoryRemovalData() {
        return new Object[][]{
                {"TC-022", "Remove Sauce Labs Backpack from inventory after adding it.", "Sauce Labs Backpack"},
                {"TC-023", "Remove Sauce Labs Bike Light from inventory after adding it.", "Sauce Labs Bike Light"},
                {"TC-024", "Remove Sauce Labs Bolt T-Shirt from inventory after adding it.", "Sauce Labs Bolt T-Shirt"},
                {"TC-025", "Remove Sauce Labs Fleece Jacket from inventory after adding it.", "Sauce Labs Fleece Jacket"}
        };
    }

    @DataProvider(name = "cartContentsDataProvider")
    public Object[][] getCartContentsData() {
        return new Object[][]{
                {"TC-026", "Cart should display a single selected product.", new String[]{"Sauce Labs Backpack"}},
                {"TC-027", "Cart should display two selected products.", new String[]{"Sauce Labs Backpack", "Sauce Labs Bike Light"}},
                {"TC-028", "Cart should display three selected products.", new String[]{"Sauce Labs Bolt T-Shirt", "Sauce Labs Fleece Jacket", "Sauce Labs Onesie"}},
                {"TC-029", "Cart should display all six selected products.", new String[]{
                        "Sauce Labs Backpack",
                        "Sauce Labs Bike Light",
                        "Sauce Labs Bolt T-Shirt",
                        "Sauce Labs Fleece Jacket",
                        "Sauce Labs Onesie",
                        "Test.allTheThings() T-Shirt (Red)"
                }}
        };
    }

    @DataProvider(name = "cartRemovalDataProvider")
    public Object[][] getCartRemovalData() {
        return new Object[][]{
                {"TC-030", "Remove Sauce Labs Backpack from the cart page.", "Sauce Labs Backpack"},
                {"TC-031", "Remove Sauce Labs Bike Light from the cart page.", "Sauce Labs Bike Light"},
                {"TC-032", "Remove Sauce Labs Onesie from the cart page.", "Sauce Labs Onesie"}
        };
    }

    @DataProvider(name = "checkoutInvalidDataProvider")
    public Object[][] getCheckoutInvalidData() {
        return new Object[][]{
                {"TC-034", "Missing first name should block checkout.", "", "Doe", "12345", "Error: First Name is required"},
                {"TC-035", "Missing last name should block checkout.", "John", "", "12345", "Error: Last Name is required"},
                {"TC-036", "Missing postal code should block checkout.", "John", "Doe", "", "Error: Postal Code is required"},
                {"TC-037", "Providing only first name should require last name.", "John", "", "", "Error: Last Name is required"},
                {"TC-038", "Providing only last name should require first name.", "", "Doe", "", "Error: First Name is required"},
                {"TC-039", "Providing only postal code should require first name.", "", "", "12345", "Error: First Name is required"}
        };
    }

    @DataProvider(name = "checkoutValidDataProvider")
    public Object[][] getCheckoutValidData() {
        return new Object[][]{
                {"TC-040", "Checkout information accepts standard customer data.", "John", "Doe", "12345"},
                {"TC-041", "Checkout information accepts another valid customer profile.", "Sara", "Ali", "11511"},
                {"TC-042", "Checkout information accepts alphanumeric postal code values.", "Test", "User", "A1A1A1"}
        };
    }

    @DataProvider(name = "checkoutFlowDataProvider")
    public Object[][] getCheckoutFlowData() {
        return new Object[][]{
                {"TC-043", "Complete checkout with one product.", new String[]{"Sauce Labs Backpack"}, "John", "Doe", "12345"},
                {"TC-044", "Complete checkout with two products.", new String[]{"Sauce Labs Backpack", "Sauce Labs Bike Light"}, "Mona", "Saleh", "44651"},
                {"TC-045", "Complete checkout with three products.", new String[]{"Sauce Labs Bolt T-Shirt", "Sauce Labs Fleece Jacket", "Sauce Labs Onesie"}, "Adam", "Nabil", "77889"},
                {"TC-046", "Complete checkout with all six products.", new String[]{
                        "Sauce Labs Backpack",
                        "Sauce Labs Bike Light",
                        "Sauce Labs Bolt T-Shirt",
                        "Sauce Labs Fleece Jacket",
                        "Sauce Labs Onesie",
                        "Test.allTheThings() T-Shirt (Red)"
                }, "Lina", "Hassan", "90210"}
        };
    }

    @DataProvider(name = "checkoutSummaryDataProvider")
    public Object[][] getCheckoutSummaryData() {
        return new Object[][]{
                {"TC-047", "Overview totals should be correct for a single backpack order.", new String[]{"Sauce Labs Backpack"}, 29.99, 2.40, 32.39},
                {"TC-048", "Overview totals should be correct for backpack and onesie order.", new String[]{"Sauce Labs Backpack", "Sauce Labs Onesie"}, 37.98, 3.04, 41.02}
        };
    }
}
