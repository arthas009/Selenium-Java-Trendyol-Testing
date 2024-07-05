package Resources.POM.PageObjects;

import Resources.POM.Common.CommonMethods;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;

/**
 * SearchPage
 * This class is used to keep functions and locators about Search page
 */
public class SearchPage {
    WebDriver driver;
    CommonMethods commonMethods;
    By randomProduct = By.xpath("//div[@class = 'p-card-wrppr with-campaign-view']/div[1]");
    By productCenterWrapper = By.xpath("//div[@class = 'search-app-container']");
    By minPriceInput = By.xpath("//input[@class = 'fltr-srch-prc-rng-input min']");
    By maxPriceInput = By.xpath("//input[@class = 'fltr-srch-prc-rng-input max']");
    By pricesDiv = By.xpath("//div[@class = 'fltr-cntnr-ttl' and text() = 'Fiyat']/..");
    By clickOnSearchInPrices = By.xpath("//button[@class = 'fltr-srch-prc-rng-srch']");
    By favoriteButton = By.xpath("//i[@class = 'fvrt-btn']");

    /**
     * Constructor of SearchPage
     *
     * @param driver: Driver instance to keep
     */
    public SearchPage(WebDriver driver, CommonMethods commonMethods) {
        this.driver = driver;
        this.commonMethods = commonMethods;
    }

    /**
     * clickOnBrandCheckbox
     * Clicks on checkbox of given brand name
     */
    public void clickOnBrandCheckbox(String brandName) {
        By checkBox = By.xpath("//div[@class = 'fltr-item-text' and text() = '" + brandName + "']/parent::a");
        commonMethods.clickElement(checkBox);
        commonMethods.waitUntilLoadingIconIsNotVisible();
        waitUntilSearchMainContainerLoaded();
    }

    /**
     * clickOnPrices
     * Clicks on prices menu to open price range options in search page
     */
    public void clickOnPrices() {
        commonMethods.clickElement(pricesDiv);
    }

    /**
     * clickOnSearchInPrices
     * Clicks on search button in prices section
     */
    public void clickOnSearchInPrices() {
        commonMethods.clickElement(clickOnSearchInPrices);
        commonMethods.waitUntilLoadingIconIsNotVisible();
        waitUntilSearchMainContainerLoaded();
    }

    /**
     * inputMinPrice
     * Inputs a price value to min price limit in prices section
     */
    public void inputMinPrice(int price) {
        if (!commonMethods.waitUntilElementIsVisible(minPriceInput)) {
            clickOnPrices();
        }
        commonMethods.inputText(minPriceInput, price + "");
    }

    /**
     * inputMaxPrice
     * Inputs a price value to max price limit in prices section
     */
    public void inputMaxPrice(int price) {
        if (!commonMethods.waitUntilElementIsVisible(maxPriceInput)) {
            clickOnPrices();
        }
        commonMethods.inputText(maxPriceInput, price + "");
    }

    /**
     * inputPriceLimitsAndMakeSearch
     * Inputs a price value to max price limit and min price limit in prices section
     *
     * @param lowestPrice  low bound value of price range
     * @param highestPrice high bound value of price range
     */
    public void inputPriceLimitsAndMakeSearch(int lowestPrice, int highestPrice) {
        inputMinPrice(lowestPrice);
        inputMaxPrice(highestPrice);
        clickOnSearchInPrices();
    }

    /**
     * clickOnAddToFavorite
     * Clicks on add to favorites button of first found product
     */
    public void clickOnAddToFavorite() {
        commonMethods.clickBody();
        commonMethods.clickElement(favoriteButton);
        commonMethods.closeAllTabs();
    }

    /**
     * clickOnRandomProduct
     * Clicks on random (first found) products
     */
    public void clickOnRandomProduct() {
        commonMethods.clickElement(randomProduct);
    }

    /**
     * waitUntilSearchMainContainerLoaded
     * Waits until search page main container div is loaded
     */
    public void waitUntilSearchMainContainerLoaded() {
        commonMethods.waitUntilElementIsVisibleAndClickable(productCenterWrapper);
    }

    /**
     * switchToProductPage
     * Switches driver to newly opened tab which is opened by clicking product
     */
    public void switchToProductPage() {
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
    }
}
