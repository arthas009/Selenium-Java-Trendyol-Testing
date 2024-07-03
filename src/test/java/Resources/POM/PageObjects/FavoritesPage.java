package Resources.POM.PageObjects;

import Resources.POM.Common.CommonMethods;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * FavoritesPage
 * Class to keep locators and methods for Favorites Page
 */
public class FavoritesPage {
    CommonMethods commonMethods;

    By addToBasket = By.xpath("//span[text() = 'Sepete Ekle' and @class = 'basket-text ']/..");
    By favoriteItem = By.xpath("//div[@class = 'p-card-wrppr']");

    /**
     * Constructor of FavoritesPage
     *
     * @param driver: Driver instance to keep
     */
    public FavoritesPage(WebDriver driver, CommonMethods commonMethods) {
        this.commonMethods = commonMethods;
    }

    public void clickOnAddToBasket() {
        commonMethods.clickElement(addToBasket);
    }

    /**
     * verifyFavoritesIsNotEmpty
     * Waits until at least one element is present in favorites page
     */
    public boolean verifyFavoritesIsNotEmpty() {
        return commonMethods.waitUntilElementIsVisibleAndClickable(favoriteItem);
    }

    /**
     * verifyFavoritesIsEmpty
     * Verifies favorites page is empty.
     *
     */
    public boolean verifyFavoritesIsEmpty() {
        try {
            return commonMethods.waitUntilElementInvisible(favoriteItem);
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * remoteItemFromFavorites
     * Removes an item from favorites page
     *
     * @param index: Index of item to remove
     */
    public void remoteItemFromFavorites(int index) {
        By removeItemFromFavorites = By.xpath("(//div[@class = 'ufvrt-btn-wrppr'])[" + index + "]");
        commonMethods.clickBodyAndElement(removeItemFromFavorites);
    }
}