package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Page Object for the Checkout Information page.
 */
public class CheckoutInformationPage extends BasePage {
    private final By firstNameLocator = By.id("first-name");
    private final By lastNameLocator = By.id("last-name");
    private final By postalCodeLocator = By.id("postal-code");
    private final By continueButtonLocator = By.id("continue");
    private final By cancelButtonLocator = By.id("cancel");
    private final By pageTitle = By.cssSelector("span.title");
    private final By errorMessage = By.cssSelector("h3[data-test='error']");

    public CheckoutInformationPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Enters checkout information and proceeds to the next step.
     */
    public void enterCheckoutInformation(String firstName, String lastName, String postalCode) {
        type(firstNameLocator, firstName);
        type(lastNameLocator, lastName);
        type(postalCodeLocator, postalCode);
        clickContinue();
    }

    /**
     * Clicks continue without changing the current field values.
     */
    public void clickContinue() {
        click(continueButtonLocator);
    }

    /**
     * Returns to the cart page.
     */
    public void clickCancel() {
        click(cancelButtonLocator);
    }

    /**
     * Gets the page title.
     */
    public String getPageTitle() {
        return getText(pageTitle);
    }

    /**
     * Gets the validation error text.
     */
    public String getErrorMessageText() {
        return getText(errorMessage);
    }

    /**
     * Checks whether the validation error is shown.
     */
    public boolean isErrorMessageDisplayed() {
        return isDisplayed(errorMessage);
    }
}
