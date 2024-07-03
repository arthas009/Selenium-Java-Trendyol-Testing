package Resources.Utility.CustomWait;

import Resources.Utility.ScreenshotHandler;
import Resources.Utility.YamlReader;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.Reporter;

import java.time.Duration;

/**
 * Test Class CustomWait
 * Being used to perform wait operations on elements
 */
public class CustomWait implements ICustomWait {
    static ScreenshotHandler screenshotHandler;
    protected WebDriver driver;
    FluentWait<WebDriver> wait;

    /**
     * Constructor for CustomWait
     *
     * @param driver: WebDriver instance for selenium usage
     */
    public CustomWait(WebDriver driver) {
        this.driver = driver;

        if (screenshotHandler == null)
            screenshotHandler = new ScreenshotHandler();
        YamlReader configurationReader = new YamlReader("src/test/resources/configuration.yaml");
        int timeout = configurationReader.getInt("ELEMENT_VISIBLE_TIMEOUT");
        int poll = configurationReader.getInt("ELEMENT_VISIBLE_POLLING");

        wait = new FluentWait<>(this.driver);
        wait.withTimeout(Duration.ofMillis(timeout));
        wait.pollingEvery(Duration.ofMillis(poll));
        wait.ignoring(NoSuchElementException.class);
    }

    /**
     * waitUntilElementIsVisible
     * Waits until element is visible and reports an error screenshot in failure.
     *
     * @param element: Element to wait.
     */
    public boolean waitUntilElementIsVisible(By element) {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(element));
            return true;
        } catch (TimeoutException e) {
            // screenshotHandler.screenshotError(driver, errorText + ".png");
            Reporter.log("Element " + element.toString() + " not visible after 10 seconds");
            screenshotHandler.screenshotError(driver);
            return false;
        }
    }

    /**
     * waitUntilElementIsVisibleAndClickable
     * Waits until element is visible and clickable. Reports an error screenshot in failure.
     *
     * @param element: Element to wait.
     */
    public boolean waitUntilElementIsVisibleAndClickable(By element) {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(element));
            wait.until(ExpectedConditions.elementToBeClickable(element));
            return true;
        } catch (TimeoutException | NoSuchElementException e) {
            // screenshotHandler.screenshotError(driver, errorText + ".png");
            Reporter.log("Element " + element.toString() + " not visible or clickable after 10 seconds");
            screenshotHandler.screenshotError(driver);
            return false;
        }
    }

    public boolean waitUntilElementIsVisibleAndClickable(String xPath) {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xPath)));
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xPath)));
            return true;
        } catch (TimeoutException | NoSuchElementException e) {
            // screenshotHandler.screenshotError(driver, errorText + ".png");
            Reporter.log("Element " + xPath + " not visible or clickable after 10 seconds");
            screenshotHandler.screenshotError(driver);
            return false;
        }
    }

    /**
     * waitUntilElementIsInvisible
     * Waits until element is invisible and reports an error screenshot in failure.
     *
     * @param element: Element to wait.
     */
    public boolean waitUntilElementIsInvisible(By element) {
        try {
            wait.until(ExpectedConditions.invisibilityOfElementLocated(element));
            return true;
        } catch (TimeoutException e) {
            // screenshotHandler.screenshotError(driver, errorText + ".png");
            Reporter.log("Element " + element.toString() + " still visible or clickable after 10 seconds");
            screenshotHandler.screenshotError(driver);
            return false;
        }
    }

}
