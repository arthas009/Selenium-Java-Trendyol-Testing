package Tests;

import Resources.POM.PageObjects.*;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Test Class Functionality_BuyingTests
 * Being used to execute tests based on buying on Trendyol
 */
public class BuyingTests extends BaseClass {

    static SearchPage searchPage;
    static ProductPage productPage;
    static FavoritesPage favoritesPage;
    static BasketPage basketPage;
    static PageHeader pageHeader;
    static PaymentPage paymentPage;

    public BuyingTests() {
        searchPage = new SearchPage(driver, commonMethods);
        productPage = new ProductPage(driver, commonMethods);
        basketPage = new BasketPage(driver, commonMethods);
        favoritesPage = new FavoritesPage(driver, commonMethods);
        paymentPage = new PaymentPage(driver, commonMethods);

        pageHeader = commonMethods.getPageHeader();

    }
    public void prepareProductOnBasket(){
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
    }
    @AfterMethod(onlyForGroups = {"Tests.BuyingTests"})
    public void CaseTeardown() {
        commonMethods.closeAllTabs();
        pageHeader.logOut();
        pageHeader.clickOnTrendyolIcon();
    }

    @BeforeMethod(onlyForGroups = {"Tests.BuyingTests"})
    public void BuyingTestsCaseSetup() {
        if (!loginPage.performLogin(getTestingUsername(), getTestingPassword())) {
            Reporter.log("Login has failed. Terminating test case");
            Assert.fail("Login has failed. Terminating test case");
        }
        pageHeader.goToMyBasketPage();
        if(basketPage.verifyBasketIsEmpty())
           prepareProductOnBasket();
    }

    @Test(priority = 1,
            groups = "Tests.BuyingTests",
            description = "")
    public void Verify_UnsuccessfulBuying_WrongCardNumber() {
        basketPage.approveBasket();
        basketPage.skipTrendyolPass();
        paymentPage.verifyAddressInformationIsShown();
        paymentPage.verifyPaymentInformationIsShown();
        paymentPage.saveAndContinue();
        paymentPage.inputCardNumber("1234431213451234");
        paymentPage.selectMonth("12");
        paymentPage.selectYear("2027");
        Assert.assertTrue(paymentPage.verifyWarningIsShown());
    }

    @Test(priority = 2,
            groups = "Tests.BuyingTests",
            description = "")
    public void Verify_UnsuccessfulBuying_WrongCardMonth() {
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
        basketPage.approveBasket();
        basketPage.skipTrendyolPass();
        paymentPage.verifyAddressInformationIsShown();
        paymentPage.verifyPaymentInformationIsShown();
        paymentPage.saveAndContinue();
        paymentPage.inputCardNumber("1234431213451234");
        paymentPage.selectMonth("1");
        paymentPage.selectYear("2027");
        paymentPage.inputCvv("234");
        Assert.assertTrue(paymentPage.verifyWarningIsShown());

    }

    @Test(priority = 2,
            groups = "Tests.BuyingTests",
            description = "")
    public void Verify_UnsuccessfulBuying_WrongCardYear() {
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
        basketPage.approveBasket();
        basketPage.skipTrendyolPass();
        paymentPage.verifyAddressInformationIsShown();
        paymentPage.verifyPaymentInformationIsShown();
        paymentPage.saveAndContinue();
        paymentPage.inputCardNumber("1234431213451234");
        paymentPage.selectMonth("1");
        paymentPage.selectYear("2032");
        paymentPage.inputCvv("123");
        Assert.assertTrue(paymentPage.verifyWarningIsShown());

    }
    @Test(priority = 2,
            groups = "Tests.BuyingTests",
            description = "")
    public void Verify_UnsuccessfulBuying_WrongCardCvv() {
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
        basketPage.approveBasket();
        basketPage.skipTrendyolPass();
        paymentPage.verifyAddressInformationIsShown();
        paymentPage.verifyPaymentInformationIsShown();
        paymentPage.saveAndContinue();
        paymentPage.inputCardNumber("1234431213451234");
        paymentPage.selectMonth("1");
        paymentPage.selectYear("2032");
        paymentPage.inputCvv("999");
        Assert.assertTrue(paymentPage.verifyWarningIsShown());

    }

}
