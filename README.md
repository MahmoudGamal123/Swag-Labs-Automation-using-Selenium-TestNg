# SwagLabs Automation

A Selenium WebDriver-based test automation framework for the [Swag Labs](https://www.saucedemo.com/) e-commerce application.

## Project Structure

```
SwagLabsAutomation/
├── src/
│   ├── main/java/pages/          # Page Object Model classes
│   │   ├── BasePage.java         # Base page class with common functionality
│   │   ├── LoginPage.java        # Login page actions and locators
│   │   ├── ProductPage.java      # Product listing page actions
│   │   ├── CheckoutCartPage.java # Shopping cart page actions
│   │   ├── CheckoutInformationPage.java  # Checkout form page
│   │   └── CheckoutOverviewPage.java     # Order summary page
│   └── test/java/
│       ├── BaseTest.java         # Test setup and teardown
│       ├── LoginTest.java        # Valid login tests
│       ├── NegativeLoginTest.java # Invalid login tests
│       ├── AddToCartTest.java    # Add to cart functionality tests
│       ├── CheckOutTest.java     # Complete checkout flow tests
│       └── providers/
│           └── DataProviders.java # Test data providers
├── pom.xml                       # Maven configuration
├── testng.xml                    # TestNG suite configuration
└── README.md                     # This file
```

## Prerequisites

- **Java**: JDK 17 or higher
- **Maven**: 3.6+ 
- **Chrome Browser**: Latest version
- **ChromeDriver**: Automatically managed by WebDriver Manager (or install manually)

## Setup

### 1. Clone the Repository

```bash
git clone <repository-url>
cd SwagLabsAutomation
```

### 2. Install Dependencies

```bash
mvn clean install
```

## Running Tests

### Run All Tests

```bash
mvn test
```

### Run Specific Test Class

```bash
mvn test -Dtest=LoginTest
```

### Run Using TestNG Suite

```bash
mvn test -DsuiteXmlFile=testng.xml
```

## Test Coverage

| Test Class | Description |
|------------|-------------|
| `LoginTest` | Validates successful login with various valid user credentials |
| `NegativeLoginTest` | Validates error handling for invalid login attempts |
| `AddToCartTest` | Tests adding products to the shopping cart |
| `CheckOutTest` | Tests the complete checkout flow from cart to order completion |

## Page Object Model

This project follows the **Page Object Model (POM)** design pattern:

- **BasePage**: Provides common WebDriver and WebDriverWait instances
- **LoginPage**: Handles login form interactions
- **ProductPage**: Manages product listing and sorting
- **CheckoutCartPage**: Controls shopping cart actions
- **CheckoutInformationPage**: Handles customer information input
- **CheckoutOverviewPage**: Manages order review and completion

## Configuration

### Browser Configuration

Tests run on Chrome by default. To modify browser settings, edit `BaseTest.java`:

```java
ChromeOptions options = new ChromeOptions();
// Add your custom options here
```

### Test Data

Test data is managed in `DataProviders.java`. Modify the `@DataProvider` methods to customize test inputs.

## Reporting

### TestNG Report

TestNG results are generated in the `target/surefire-reports/` directory after test execution.

### Allure Report

This project is configured with **Allure TestNG** support. Test execution writes raw Allure results to:

```bash
target/allure-results
```

Generate the HTML report with:

```bash
mvn allure:report
```

Open the interactive report locally with:

```bash
mvn allure:serve
```

## Troubleshooting

### ChromeDriver Issues

If you encounter ChromeDriver compatibility issues:

1. Ensure Chrome browser is updated to the latest version
2. Update Selenium version in `pom.xml` if needed
3. Consider using WebDriver Manager for automatic driver management

### Test Failures

Common causes:
- Network connectivity issues
- Website changes affecting locators
- Timeout configurations

## Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

## License

This project is open source and available under the MIT License.

## Acknowledgments

- [Sauce Labs](https://saucelabs.com/) for providing the demo application
- [Selenium](https://www.selenium.dev/) for the WebDriver library
- [TestNG](https://testng.org/) for the testing framework
