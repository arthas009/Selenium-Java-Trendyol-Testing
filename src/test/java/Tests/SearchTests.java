package Tests;

import Resources.POM.PageObjects.PageHeader;
import Resources.POM.PageObjects.BasketPage;
import Resources.POM.PageObjects.FavoritesPage;
import Resources.POM.PageObjects.ProductPage;
import Resources.POM.PageObjects.SearchPage;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Test Class Functionality_SearchTests
 * Being used to execute tests based on searching on Trendyol
 */
public class SearchTests extends BaseClass {
    static SearchPage searchPage;
    static ProductPage productPage;
    static FavoritesPage favoritesPage;
    static BasketPage basketPage;
    static PageHeader pageHeader;

    public SearchTests() {
        searchPage = new SearchPage(driver, commonMethods);
        productPage = new ProductPage(driver, commonMethods);
        basketPage = new BasketPage(driver, commonMethods);
        favoritesPage = new FavoritesPage(driver, commonMethods);

        pageHeader = commonMethods.getPageHeader();

    }

    @AfterMethod(onlyForGroups = {"Tests.SearchTests"})
    public void CaseTeardown() {
        commonMethods.closeAllTabs();
        pageHeader.logOut();
        pageHeader.clickOnTrendyolIcon();
    }

    @BeforeMethod(onlyForGroups = {"Tests.SearchTests"})
    public void SearchTestsCaseSetup() {
        if (!loginPage.performLogin(getTestingUsername(), getTestingPassword())) {
            Reporter.log("Login has failed. Terminating test case");
            Assert.fail("Login has failed. Terminating test case");
        }
    }

    @Test(priority = 1,
            groups = "Tests.SearchTests",
            description = "Searchs Supurge products under 3000 - 10000 TL limit and adds first product to basket." +
                    "Verifies adding to basket in product page is working.")
    public void Verify_ProductAddingToBasket() {
        pageHeader.searchSomethingInSearchbar("Süpürge");
        searchPage.clickOnRandomProduct();
        searchPage.switchToProductPage();
        productPage.clickOnAddToBasket();
        productPage.waitUntilAddedToBasketSuccessButtonIsVisible();
        productPage.closeProductBrowserTab();
        pageHeader.goToMyBasketPage();
        Assert.assertFalse(basketPage.verifyBasketIsEmpty());
        basketPage.deleteItem(1);
        Assert.assertTrue(basketPage.verifyBasketIsEmpty());

    }

    @Test(priority = 1,
            groups = "Tests.SearchTests",
            description = "Searchs a random Gomlek product and adds to favorite and basket pages." +
                    "Verifies adding to favorite in search page is working.")
    public void Verify_ProductAddingToFavorites() {
        pageHeader.searchSomethingInSearchbar("Ayakkabı");
        searchPage.clickOnAddToFavorite();
        pageHeader.goToFavoritesPage();
        Assert.assertTrue(favoritesPage.verifyFavoritesIsNotEmpty());
        favoritesPage.remoteItemFromFavorites(1);
        Assert.assertTrue(favoritesPage.verifyFavoritesIsEmpty());
    }

    @Test(priority = 2,
            groups = "Tests.SearchTests",
            description = "Searchs a random product and adds basket page. Compare the price of product is in between the given range" +
                    "Verifies price of the selected product is in between the given range")
    public void Verify_PriceLimit() {
        pageHeader.searchSomethingInSearchbar("Süpürge");
        searchPage.clickOnBrandCheckbox("Arzum");
        searchPage.inputPriceLimitsAndMakeSearch(3000, 10000);
        searchPage.clickOnRandomProduct();
        searchPage.switchToProductPage();
        productPage.clickOnAddToBasket();
        productPage.waitUntilAddedToBasketSuccessButtonIsVisible();
        productPage.closeProductBrowserTab();
        pageHeader.goToMyBasketPage();
        Assert.assertFalse(basketPage.verifyBasketIsEmpty());
        Assert.assertTrue(basketPage.verifyPriceIsBetweenGivenRange(3000,10000));
    }

    @Test(priority = 2,
            groups = "Tests.SearchTests",
            description = "Searchs a random product and adds basket page. Compare the brand of product." +
                    "Verifies brand of product is as selected as in search page")
    public void Verify_BrandName() {
        pageHeader.searchSomethingInSearchbar("Süpürge");
        searchPage.clickOnBrandCheckbox("Philips");
        searchPage.inputPriceLimitsAndMakeSearch(3000, 10000);
        searchPage.clickOnRandomProduct();
        searchPage.switchToProductPage();
        productPage.clickOnAddToBasket();
        productPage.waitUntilAddedToBasketSuccessButtonIsVisible();
        productPage.closeProductBrowserTab();
        pageHeader.goToMyBasketPage();
        Assert.assertFalse(basketPage.verifyBasketIsEmpty());
        Assert.assertTrue(basketPage.verifyBrandNameWithGiven("Philips"));
    }

    @Test(priority = 2,
            groups = "Tests.SearchTests",
            description = "Searchs a random product and add first two of them to basket page." +
                    "Verifies 2 products is added to basket page.")
    public void Verify_MultipleProduct() {
        pageHeader.searchSomethingInSearchbar("Süpürge");
        searchPage.clickOnRandomProduct();
        searchPage.switchToProductPage();
        productPage.clickOnAddToBasket();
        productPage.waitUntilAddedToBasketSuccessButtonIsVisible();
        productPage.closeProductBrowserTab();
        searchPage.clickOnRandomProduct2();
        searchPage.switchToProductPage();
        productPage.clickOnAddToBasket();
        productPage.waitUntilAddedToBasketSuccessButtonIsVisible();
        productPage.closeProductBrowserTab();
        pageHeader.goToMyBasketPage();
        Assert.assertTrue(basketPage.verifyBasketHasMultipleItems());
    }

    @Test(priority = 2,
            groups = "Tests.SearchTests",
            description = "Adds a product to basket and increased the count of product." +
                    "Verifies price count is doubled when there is 2 same product.")
    public void Verify_IncreaseProductCountAtBasketPage() {
        pageHeader.searchSomethingInSearchbar("Süpürge");
        searchPage.clickOnRandomProduct();
        searchPage.switchToProductPage();
        productPage.clickOnAddToBasket();
        productPage.waitUntilAddedToBasketSuccessButtonIsVisible();
        productPage.closeProductBrowserTab();
        pageHeader.goToMyBasketPage();
        int currentPrice = basketPage.getPrice();
        basketPage.clickOnIncreaseProductCounter();
        int newPrice = basketPage.getPrice();
        Assert.assertEquals(currentPrice * 2, newPrice);
    }

}
