import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.ByteArrayInputStream;
import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * TestNG listener that enriches Allure reports with failure diagnostics.
 */
public class AllureTestListener implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {
        attachFailureDetails(result);
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        if (result.getThrowable() != null) {
            Allure.addAttachment("Skipped reason", result.getThrowable().getMessage());
        }
    }

    private void attachFailureDetails(ITestResult result) {
        if (result.getThrowable() != null) {
            StringWriter writer = new StringWriter();
            result.getThrowable().printStackTrace(new PrintWriter(writer));
            Allure.addAttachment("Failure stack trace", "text/plain", writer.toString(), ".txt");
        }

        Object instance = result.getInstance();
        if (!(instance instanceof BaseTest)) {
            return;
        }

        WebDriver driver = ((BaseTest) instance).getDriver();
        if (driver == null) {
            return;
        }

        if (driver instanceof TakesScreenshot) {
            byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            Allure.addAttachment("Failure screenshot", "image/png", new ByteArrayInputStream(screenshot), ".png");
        }

        Allure.addAttachment("Current URL", driver.getCurrentUrl());
        Allure.addAttachment("Page source", "text/html", driver.getPageSource(), ".html");
    }
}
