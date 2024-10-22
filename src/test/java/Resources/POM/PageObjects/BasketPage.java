package Resources.POM.PageObjects;

import Resources.POM.Common.CommonMethods;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;

/**
 * BasketPage
 * Class to keep locators and methods for Basket Page
 */
public class BasketPage {

    CommonMethods commonMethods;

    By approveBasket = By.xpath("//a[text() = 'Sepeti Onayla']");
    By skipTrendyolPass = By.xpath("//button[text() = 'Eklemeden Devam Et'");
    By productPrice = By.xpath("//div[@class = 'class=pb-basket-item-price'");
    By increaseProductCount = By.xpath("//button[@class ='ty-numeric-counter-button' and @aria-label = 'Ürün adedi arttırma']");
    By brandNameElement = By.xpath("//p[@class = 'pb-item']//span");


    /**
     * Constructor of BasketPage
     *
     * @param driver: Driver instance to keep
     */
    public BasketPage(WebDriver driver, CommonMethods commonMethods) {
        this.commonMethods = commonMethods;
    }

    /**
     * verifyBasketIsNotEmptyAndDeleteItem
     * Verifies basket page is not empty and removes a given item
     *
     * @param index: Index of item to remove
     */
    public void deleteItem(int index) {
        By removeItemFromBasket = By.xpath("(//i[@class = 'i-trash'])[" + index + "]/parent::button");
        commonMethods.clickBody();
        commonMethods.clickElement(removeItemFromBasket);

    }

    /**
     * verifyBasketIsEmpty
     * Verifies basket page is empty.
     *
     */
    public boolean verifyBasketIsEmpty() {
        By removeItemFromBasket = By.xpath("(//i[@class = 'i-trash'])[1]/parent::button");
        commonMethods.clickBody();
        try {
            return commonMethods.waitUntilElementInvisible(removeItemFromBasket);
        } catch (Exception e) {
            return false;
        }
    }

    public boolean verifyBasketHasMultipleItems() {
        By removeItemFromBasket = By.xpath("(//i[@class = 'i-trash'])[1]/parent::button");
        By removeItemFromBasket2 = By.xpath("(//i[@class = 'i-trash'])[2]/parent::button");

        commonMethods.clickBody();
        try {
            commonMethods.waitUntilElementInvisible(removeItemFromBasket);
            commonMethods.waitUntilElementInvisible(removeItemFromBasket2);
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    public void approveBasket(){
        commonMethods.clickElement(approveBasket);
    }

    public void skipTrendyolPass(){
        commonMethods.clickElement(skipTrendyolPass);

    }

    public boolean verifyPriceIsBetweenGivenRange(int priceMin, int priceHigh){
        commonMethods.waitUntilElementIsVisible(productPrice);
        int price = Integer.parseInt(commonMethods.getElementText((productPrice)));
        return price >= priceMin && price <= priceHigh;
    }

    public boolean verifyBrandNameWithGiven(String brandName){
        commonMethods.waitUntilElementIsVisible(brandNameElement);
        return brandName.equals(commonMethods.getElementText(brandNameElement));
    }

    public void clickOnIncreaseProductCounter(){
        commonMethods.clickElement(increaseProductCount);
    }

    public int getPrice(){
        commonMethods.waitUntilElementIsVisible(productPrice);
        return Integer.parseInt(commonMethods.getElementText((productPrice)));
    }

}
