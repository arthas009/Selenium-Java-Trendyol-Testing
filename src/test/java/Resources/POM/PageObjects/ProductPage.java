package Resources.POM.PageObjects;

import Resources.POM.Common.CommonMethods;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;

/**
 * ProductPage
 * This class is used to keep functions and locators about Product page
 */
public class ProductPage {

    CommonMethods commonMethods;
    WebDriver driver;
    By addToBasket = By.xpath("//button[@class = 'add-to-basket']");

    By addedToBasketSuccessfully = By.xpath("//button[@class = 'add-to-basket success'");
    By productContainer = By.xpath("//div[@class = 'product-container']");

    /**
     * Constructor of ProductPage
     *
     * @param driver: Driver instance to keep
     */
    public ProductPage(WebDriver driver, CommonMethods commonMethods) {
        this.driver = driver;
        this.commonMethods = commonMethods;
    }

    /**
     * clickOnAddToBasket
     * Clicks on Add to Basket button
     */
    public void clickOnAddToBasket() {
        commonMethods.clickElement(addToBasket);
    }

    /**
     * waitUntilProductContainerLoaded
     * Waits until product container element is loaded
     */
    public void waitUntilAddedToBasketSuccessButtonIsVisible() {
        commonMethods.waitUntilElementIsVisibleAndClickable(addedToBasketSuccessfully);
    }

    /**
     * closeProductBrowserTab
     * Closes newly opened tab which is opened by clicking a product on search page
     */
    public void closeProductBrowserTab() {
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.close();
        driver.switchTo().window(tabs.get(0));
    }
}
