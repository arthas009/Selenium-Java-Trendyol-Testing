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

    /**
     * prepareProductOnBasket
     * Being used for preparing a product added to basked before each test execution.
     */
    public void prepareProductOnBasket() {
        pageHeader.searchSomethingInSearchbar("Süpürge");
        searchPage.clickOnBrandCheckbox("Arzum");
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
        pageHeader.goToMyBasketPage();
        basketPage.deleteItem(1);
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
        if (basketPage.verifyBasketIsEmpty())
            prepareProductOnBasket();
    }

    @Test(priority = 1,
            groups = "Tests.BuyingTests",
            description = "Goes to basked page, approves basket and tries to buy the product using invalid card number" +
                    "Verifies invalid card number triggers a warning text." +
                    "Buy button is disabled while card number is invalid.")
    public void Verify_UnsuccessfulBuying_WrongCardNumber() {
        Assert.assertFalse(basketPage.verifyBasketIsEmpty());
        basketPage.approveBasket();
        basketPage.skipTrendyolPass();
        paymentPage.verifyAddressInformationIsShown();
        paymentPage.verifyPaymentInformationIsShown();
        paymentPage.saveAndContinue();
        paymentPage.inputCardNumber("1234431213451234");
        paymentPage.selectMonth("12");
        paymentPage.selectYear("2027");
        Assert.assertTrue(paymentPage.verifyWarningIsShown());
        Assert.assertTrue(paymentPage.verifyPayButtonIsDisabled());
    }

    @Test(priority = 2,
            groups = "Tests.BuyingTests",
            description = "Goes to basked page, approves basket and tries to buy the product using invalid card month" +
                    "Verifies invalid card month triggers a warning text." +
                    "Buy button is disabled while month is invalid.")
    public void Verify_UnsuccessfulBuying_WrongCardMonth() {
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
        Assert.assertTrue(paymentPage.verifyPayButtonIsDisabled());

    }

    @Test(priority = 2,
            groups = "Tests.BuyingTests",
            description = "Goes to basked page, approves basket and tries to buy the product using invalid card yeara" +
                    "Verifies invalid card year triggers a warning text." +
                    "Buy button is disabled while yeara is invalid.")
    public void Verify_UnsuccessfulBuying_WrongCardYear() {
        Assert.assertFalse(basketPage.verifyBasketIsEmpty());
        basketPage.approveBasket();
        basketPage.skipTrendyolPass();
        paymentPage.verifyAddressInformationIsShown();
        paymentPage.verifyPaymentInformationIsShown();
        paymentPage.saveAndContinue();
        paymentPage.inputCardNumber("1234431213451234");
        paymentPage.selectMonth("6");
        paymentPage.selectYear("2032");
        paymentPage.inputCvv("123");
        Assert.assertTrue(paymentPage.verifyWarningIsShown());
        Assert.assertTrue(paymentPage.verifyPayButtonIsDisabled());

    }

    @Test(priority = 2,
            groups = "Tests.BuyingTests",
            description = "Assume 999 is an invalid CVV number." +
                    "Goes to basked page, approves basket and tries to buy the product using invalid card cvv" +
                    "Verifies invalid card cvv triggers a warning text." +
                    "Buy button is disabled while cvv is invalid.")
    public void Verify_UnsuccessfulBuying_WrongCardCvv() {
        Assert.assertFalse(basketPage.verifyBasketIsEmpty());
        basketPage.approveBasket();
        basketPage.skipTrendyolPass();
        paymentPage.verifyAddressInformationIsShown();
        paymentPage.verifyPaymentInformationIsShown();
        paymentPage.saveAndContinue();
        paymentPage.inputCardNumber("1234431213451234");
        paymentPage.selectMonth("1");
        paymentPage.selectYear("2027");
        paymentPage.inputCvv("999");
        Assert.assertTrue(paymentPage.verifyWarningIsShown());
        Assert.assertTrue(paymentPage.verifyPayButtonIsDisabled());

    }

    @Test(priority = 2,
            groups = "Tests.BuyingTests",
            description = "Goes to basked page, approves basket and tries to buy the product using long (more than 3 digit) card cvv" +
                    "Verifies invalid card cvv triggers a warning text." +
                    "Buy button is disabled while cvv is invalid.")
    public void Verify_UnsuccessfulBuying_LongCardCvv() {
        Assert.assertFalse(basketPage.verifyBasketIsEmpty());
        basketPage.approveBasket();
        basketPage.skipTrendyolPass();
        paymentPage.verifyAddressInformationIsShown();
        paymentPage.verifyPaymentInformationIsShown();
        paymentPage.saveAndContinue();
        paymentPage.inputCardNumber("1234431213451234");
        paymentPage.selectMonth("1");
        paymentPage.selectYear("2027");
        paymentPage.inputCvv("99999999");
        Assert.assertTrue(paymentPage.verifyWarningIsShown());
        Assert.assertTrue(paymentPage.verifyPayButtonIsDisabled());

    }

    @Test(priority = 3,
            groups = "Tests.BuyingTests",
            description = "In this case we assume this is a valid scenario with valid card number credentials." +
                    "I could not use a real card number for the case so do not care about the card values," +
                    "just assume they are correct.")
    public void Verify_SuccessfulBuying() {
        Assert.assertFalse(basketPage.verifyBasketIsEmpty());
        basketPage.approveBasket();
        basketPage.skipTrendyolPass();
        paymentPage.verifyAddressInformationIsShown();
        paymentPage.verifyPaymentInformationIsShown();
        paymentPage.saveAndContinue();
        paymentPage.inputCardNumber("1234431213451234");
        paymentPage.selectMonth("1");
        paymentPage.selectYear("2028");
        paymentPage.inputCvv("125");
        Assert.assertFalse(paymentPage.verifyWarningIsShown());
        Assert.assertFalse(paymentPage.verifyPayButtonIsDisabled());
    }

}
