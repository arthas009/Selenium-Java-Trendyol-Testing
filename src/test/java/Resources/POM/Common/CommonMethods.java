package Resources.POM.Common;

import Resources.POM.Interfaces.IElementInteractions;
import Resources.POM.PageObjects.PageHeader;
import Resources.Utility.CustomWait.CustomWait;
import org.checkerframework.checker.units.qual.C;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;

import java.awt.*;
import java.util.List;

public class CommonMethods implements IElementInteractions {
    private final PageHeader pageHeader;
    private final CustomWait customWait;

    private final WebDriver driver;
    String originalHandle;

    public CommonMethods(WebDriver driver) {
        this.driver = driver;
        this.customWait = new CustomWait(driver);
        this.originalHandle = driver.getWindowHandle();
        this.pageHeader = new PageHeader(driver, this);
    }

    @Override
    public void inputText(By element, String text) {
        waitUntilElementIsVisible(element);
        driver.findElement(element).sendKeys(text);
    }

    @Override
    public void clickElement(By element) {
        waitUntilElementIsVisibleAndClickable(element);
        // To avoid stale element exception
        try {
            driver.findElement(element).click();
        } catch (StaleElementReferenceException | ElementClickInterceptedException e) {
            waitUntilElementIsVisibleAndClickable(element);
            driver.findElement(element).click();
        }
    }

    @Override
    public void clickBodyAndElement(By element) {
        waitUntilElementIsVisibleAndClickable(element);
        this.clickBody();
        // To avoid stale element exception
        try {
            driver.findElement(element).click();
        } catch (StaleElementReferenceException | ElementClickInterceptedException e) {
            driver.findElement(element).click();
        }
    }

    @Override
    public void hoverElement(By element) {
        Actions action = new Actions(driver);
        this.waitUntilElementIsVisibleAndClickable(element);
        WebElement webElement = driver.findElement(element);
        action.moveToElement(webElement).perform();

    }

    @Override
    public boolean verifyElementExists(By element) {
        return !driver.findElements(By.id("...")).isEmpty();
    }

    @Override
    public String getElementText(By element) {
        WebElement webElement = driver.findElement(element);
        return webElement.getText();
    }

    public void clickBody() {
        Actions action = new Actions(driver);
        Robot robot = null;

        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }

        assert robot != null;
        robot.mouseMove(1, 1);
        action.click().build().perform();
    }

    public void closeAllTabs() {
        for (String handle : driver.getWindowHandles()) {
            if (!handle.equals(originalHandle)) {
                driver.switchTo().window(handle);
                driver.close();
            }
        }
        driver.switchTo().window(originalHandle);
    }

    /**
     * waitUntilLoadingIconIsNotVisible
     * Waits until loading circle icon is not shown on web page
     */
    public void waitUntilLoadingIconIsNotVisible() {
        waitUntilElementIsVisible(By.xpath("//div[@class = 'q-loader']"));
    }

    /**
     * imageExists
     * <p>
     * Checks the element has a valid image or not
     */
    public boolean imageExists(WebElement image) {
        return !image.getAttribute("naturalWidth").equals("0");
    }

    public String generateXPATH(WebElement childElement) {
        String childTag = childElement.getTagName();
        if (childTag.equals("html")) {
            return "/html[1]";
        }
        WebElement parentElement = childElement.findElement(By.xpath(".."));
        List<WebElement> childrenElements = parentElement.findElements(By.xpath("*"));
        int count = 0;
        for (WebElement childrenElement : childrenElements) {
            String childrenElementTag = childrenElement.getTagName();
            if (childTag.equals(childrenElementTag)) {
                count++;
            }
            if (childElement.equals(childrenElement)) {
                return generateXPATH(parentElement);
            }
        }
        return null;
    }

    public boolean waitUntilElementIsVisible(By element) {
        return customWait.waitUntilElementIsVisible(element);
    }

    public boolean waitUntilElementIsVisibleAndClickable(By element) {
        return customWait.waitUntilElementIsVisibleAndClickable(element);
    }

    public boolean waitUntilElementIsVisibleAndClickable(String element_xpath) {
        return customWait.waitUntilElementIsVisibleAndClickable(element_xpath);
    }


    public boolean waitUntilElementInvisible(By element) {
        return customWait.waitUntilElementIsInvisible(element);
    }

    public PageHeader getPageHeader() {
        return pageHeader;
    }
}
