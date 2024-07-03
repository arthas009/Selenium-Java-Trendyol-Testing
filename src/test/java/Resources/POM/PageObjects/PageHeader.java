package Resources.POM.PageObjects;

import Resources.POM.Common.CommonMethods;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.testng.Reporter;

/**
 * BasketPage
 * Class to keep locators and methods for Basket Page
 */
public class PageHeader {

    CommonMethods commonMethods;
    WebDriver driver;
    By searchButton = By.xpath("//*[@data-testid = 'search-icon']");
    By searchBar = By.xpath("//input[@data-testid = 'suggestion']");
    By hoverMyAccount = By.xpath("//div[@class = 'account-nav-item user-login-container']/div/p[text() = 'Hesabım']");
    By logoutButtonInHoverMenu = By.xpath("//p[text() = 'Çıkış Yap']/..");
    By logInButton = By.xpath("//p[text() = 'Giriş Yap' and @class = 'link-text']");
    By myFavorites = By.xpath("//p[text() = 'Favorilerim' and @class = 'link-text']");
    By myBasket = By.xpath("//p[text() = 'Sepetim' and @class = 'link-text']");
    By trendyolIcon = By.xpath("//img[@alt = 'Trendyol']");


    /**
     * Constructor of BasketPage
     *
     * @param driver: Driver instance to keep
     */
    public PageHeader(WebDriver driver, CommonMethods commonMethods) {
        this.commonMethods = commonMethods;
        this.driver = driver;
    }

    /**
     * clickOnLogIn
     * Clicks on Log in button in Home page
     */

    public void clickOnLogIn() {
        this.commonMethods.clickElement(logInButton);
    }

    /**
     * clickOnSearch
     * Clicks on the search input
     */
    public void clickOnSearch() {
        this.commonMethods.clickElement(searchButton);
    }

    /**
     * inputSearchBar
     * Inputs a text into searchbar
     *
     * @param text: text to input
     */
    public void inputSearchBar(String text) {
        this.commonMethods.waitUntilElementIsVisibleAndClickable(searchBar);
        WebElement searchBarElement = driver.findElement(searchBar);

        //Avoid Stale Element Exception in any case
        try {
            searchBarElement.click();
            searchBarElement.sendKeys(text);
        } catch (StaleElementReferenceException | ElementClickInterceptedException e) {
            searchBarElement.click();
            searchBarElement.sendKeys(text);
        }
    }

    /**
     * clickOnTab
     * Clicks on a Tab in trendyol main page
     *
     * @param tabName: Text of tab to click
     */
    public void clickOnTab(String tabName) {
        this.commonMethods.waitUntilLoadingIconIsNotVisible();
        By tab = By.xpath("//a[text() = '" + tabName + "' and @class = 'category-header']");
        this.commonMethods.clickElement(tab);
        this.commonMethods.clickBody();
        this.commonMethods.waitUntilLoadingIconIsNotVisible();
    }

    /**
     * goToFavoritesTab
     * Clicks on My Favorites button in Home page
     */

    public void goToFavoritesPage() {
        this.commonMethods.clickBodyAndElement(myFavorites);
    }

    /**
     * goToMyBasketTab
     * Clicks on My Basket button in Home page
     */
    public void goToMyBasketPage() {
        this.commonMethods.clickBodyAndElement(myBasket);
    }

    /**
     * clickOnTrendyolIcon
     * Clicks on Trendyol icon
     */
    public void clickOnTrendyolIcon() {
        this.commonMethods.clickElement(trendyolIcon);
    }

    /**
     * searchSomethingInSearchbar
     * Inputs a text into searchbar and performs search
     *
     * @param text: text to search on searchbar
     */
    public void searchSomethingInSearchbar(String text) {
        inputSearchBar(text);
        this.commonMethods.waitUntilLoadingIconIsNotVisible();
        clickOnSearch();
        this.commonMethods.waitUntilLoadingIconIsNotVisible();
        Reporter.log("Searched " + text + "in searchbar", 1);
    }

    /**
     * checkHoverMyAccountIsVisible
     * Checks an user is logged in.
     */
    public boolean checkHoverMyAccountIsVisible() {
        try {
            return this.commonMethods.waitUntilElementIsVisible(hoverMyAccount);

        } catch (TimeoutException e) {
            Reporter.log("Element " + hoverMyAccount.toString() + " not visible");
            return false;
        }
    }

    /**
     * hoverOnElementAndClickSubElement
     * Hovers on an element, then clicks on a button in hover menu
     *
     * @param hoveringElementXpath xpath of element to hover on
     * @param elementToClickXpath  xpath of hover menu button to click
     */
    public void hoverOnElementAndClickSubElement(By hoveringElementXpath, By elementToClickXpath) {
        Actions action = new Actions(driver);
        this.commonMethods.waitUntilElementIsVisibleAndClickable(hoveringElementXpath);
        WebElement we = driver.findElement(hoveringElementXpath);
        action.moveToElement(we).perform();
        if (this.commonMethods.waitUntilElementIsVisibleAndClickable(elementToClickXpath)) {
            try {
                WebElement elementToClick = driver.findElement(elementToClickXpath);
                action.moveToElement(elementToClick).perform();
                elementToClick.click();
            } catch (Exception e) {
            }
        }
    }

    /**
     * logOut
     * Logs out from trendyol by hovering on My Account menu and clicking Log Out button
     */
    public void logOut() {
        hoverOnElementAndClickSubElement(hoverMyAccount, logoutButtonInHoverMenu);
    }


}
