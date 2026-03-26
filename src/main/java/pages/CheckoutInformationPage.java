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

    public CheckoutInformationPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Enters checkout information and proceeds to the next step.
     */
    public void enterCheckoutInformation(String firstName, String lastName, String postalCode) {
        driver.findElement(firstNameLocator).sendKeys(firstName);
        driver.findElement(lastNameLocator).sendKeys(lastName);
        driver.findElement(postalCodeLocator).sendKeys(postalCode);
        driver.findElement(continueButtonLocator).click();
    }
}
