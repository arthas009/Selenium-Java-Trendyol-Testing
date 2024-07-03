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

    @Test(priority = 2,
            groups = "Tests.SearchTests",
            description = "Searchs Supurge products under 3000 - 10000 TL limit and adds first product to basket." +
                    "Verifies brand selection in search page is working." +
                    "Verifies price range selection in search page is working." +
                    "Verifies adding to basket in product page is working.")
    public void Verify_SupurgeProduct() {
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
        basketPage.deleteItem(1);
        Assert.assertTrue(basketPage.verifyBasketIsEmpty());

    }

    @Test(priority = 3,
            groups = "Tests.SearchTests",
            description = "Searchs a random Gomlek product and adds to favorite and basket pages." +
                    "Verifies adding to favorite in search page is working." +
                    "Verifies adding to basket from favorites page is working.")
    public void Verify_AyakkabıProduct() {
        pageHeader.searchSomethingInSearchbar("Ayakkabı");
        searchPage.clickOnAddToFavorite();
        pageHeader.goToFavoritesPage();
        Assert.assertTrue(favoritesPage.verifyFavoritesIsNotEmpty());
        favoritesPage.clickOnAddToBasket();
        favoritesPage.remoteItemFromFavorites(1);
        Assert.assertTrue(favoritesPage.verifyFavoritesIsEmpty());
        pageHeader.goToMyBasketPage();
        basketPage.deleteItem(1);
        Assert.assertTrue(basketPage.verifyBasketIsEmpty());

    }


}
