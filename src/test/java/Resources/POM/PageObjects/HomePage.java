package Resources.POM.PageObjects;

import Resources.POM.Common.CommonMethods;
import Resources.Utility.YamlReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;

import java.util.Arrays;
import java.util.List;

/**
 * HomePage
 * This class is used to keep functions and locators about web elements about main page
 * It also has a default Fluent wait which is used by itself and child classes to wait elements to be visible.
 * Fluent wait has 45 second timeout and polls every second
 */
public class HomePage {
    protected String test_output_directory;
    WebDriver driver;
    CommonMethods commonMethods;
    By cookieAcceptButton = By.id("onetrust-accept-btn-handler");
    By firstComponentOfTab = By.xpath("//div[@data-testid = 'sliderList']/a[1]");
    By productCenterContainer = By.xpath("//div[@class = 'prdct-cntnr-wrppr']");
    By favoredCenterContainer = By.xpath("//div[@class = 'favored-product-container']");
    String headerTabs = "(//li[@class = 'tab-link']/a)";

    /**
     * Constructor of HomePage
     *
     * @param driver: Driver instance to keep
     */
    public HomePage(WebDriver driver, CommonMethods commonMethods) {
        this.driver = driver;
        this.commonMethods = commonMethods;
        YamlReader configurationReader = new YamlReader("src/test/resources/configuration.yaml");
        test_output_directory = configurationReader.getString("TEST_OUTPUT_DIRECTORY");
    }

    /**
     * acceptCookie
     * Clicks on accept button when Accept Cookie window is shown
     */
    public void acceptCookie() {
        commonMethods.clickElement(cookieAcceptButton);
    }


    /**
     * traverseHeadersToVerifyImages
     * Loops through all the tabs in trendyol header. Then opens each tab and verifies the first 4 product in first component
     * has a valid image or not.
     */
    public void traverseHeadersToVerifyImages() {
        commonMethods.waitUntilElementIsVisibleAndClickable(headerTabs);
        List<WebElement> elements = driver.findElements(By.xpath(headerTabs));
        List<String> excludeList = Arrays.asList("Elektronik", "İş Yerine Özel", "Çok Satanlar", "Flaş Ürünler");
        for (int counter = 1; counter < elements.size(); counter++) {
            commonMethods.getPageHeader().clickOnTrendyolIcon();
            commonMethods.waitUntilLoadingIconIsNotVisible();
            commonMethods.waitUntilElementIsVisibleAndClickable(By.xpath(headerTabs + "[" + counter + "]"));
            String tabName = commonMethods.getElementText(By.xpath(headerTabs + "[" + counter + "]"));
            if (excludeList.contains(tabName))
                continue;

            commonMethods.clickElement(By.xpath(headerTabs + "[" + counter + "]"));
            commonMethods.clickBody();

            verifyFirstFourProductImageIsShown(tabName);
        }
    }

    /**
     * verifyFirstFourProductImageIsShown
     * Verifies first 4 product image is shown. First productCenterContainer is visible.
     * If not, checks favoredCenterContainer is visible.
     * For productCenterContainer If page contains advertisement products, starts
     * the counter from counter + 1;
     *
     * @param tabName: Tab name used for logging.
     */
    public void verifyFirstFourProductImageIsShown(String tabName) {
        commonMethods.waitUntilElementIsVisible(firstComponentOfTab);
        commonMethods.clickElement(firstComponentOfTab);
        commonMethods.clickBody();

        WebElement productContainer;

        if (commonMethods.waitUntilElementIsVisible(productCenterContainer)) {
            productContainer = driver.findElement(productCenterContainer);
            int start = 1;
            if (tabName.equals("Kozmetik") || tabName.equals("Ayakkabı & Çanta"))
                start = 2;
            for (int i = start; i <= start + 3; i++) {
                WebElement childImage = productContainer.findElement(By.xpath(
                        "//div[" + i + "]/div[1]/a[1]/div[1]/div[1]/img"));
                if (commonMethods.imageExists(childImage)) {
                    System.out.println("Image loaded correctly for " + i + ". product in " + tabName + " tab");
                    Reporter.log("Image loaded correctly for " + i + ". product in " + tabName + " tab", 5);
                } else {
                    System.out.println("Image is not loaded correctly for " + i + ". product in " + tabName + " tab");
                    Reporter.log("Image is not loaded correctly for " + i + ". product in " + tabName + " tab", 5);
                }
            }

        } else {
            commonMethods.waitUntilElementIsVisible(favoredCenterContainer);
            productContainer = driver.findElement(favoredCenterContainer);
            for (int i = 1; i <= 4; i++) {
                WebElement childImage = productContainer.findElement(By.xpath(
                        "//div[" + i + "]/a[1]/div[1]/img"));
                if (commonMethods.imageExists(childImage)) {
                    System.out.println("Image loaded correctly for " + i + ". product in " + tabName + " tab");
                    Reporter.log("Image loaded correctly for " + i + ". product in " + tabName + " tab", 5);
                } else {
                    System.out.println("Image is not loaded correctly for " + i + ". product in " + tabName + " tab");
                    Reporter.log("Image is not loaded correctly for " + i + ". product in " + tabName + " tab", 5);
                }
            }
        }
    }

}
