package Resources.POM.PageObjects;

import Resources.POM.Common.CommonMethods;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * BasketPage
 * Class to keep locators and methods for Basket Page
 */
public class BasketPage {

    CommonMethods commonMethods;

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

}
