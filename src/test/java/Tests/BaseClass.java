package Tests;

import Resources.POM.Common.CommonMethods;
import Resources.POM.PageObjects.HomePage;
import Resources.POM.PageObjects.LoginPage;
import Resources.POM.PageObjects.RegisterPage;
import Resources.Utility.CustomDrivers.CustomChromeDriver;
import Resources.Utility.CustomDrivers.CustomEdgeDriver;
import Resources.Utility.CustomDrivers.CustomFirefoxDriver;
import Resources.Utility.YamlReader;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

/**
 * Repository class Tests.BaseClass
 * Being used to keep methods like suite setup, suite teardown or method teardown etc.
 */
public class BaseClass {
    static CommonMethods commonMethods;
    static LoginPage loginPage;
    static HomePage homePage;
    static RegisterPage registerPage;
    private final String testingUsername;
    private final String testingPassword;
    private final String registerTestingUsername;
    private final String registerTestingMailPassword;
    private final String testingMainURL;
    protected WebDriver driver;
    String browserType;
    YamlReader configurationReader;
    private String originalHandle;

    public BaseClass() {

        // Reading configuration from yaml
        configurationReader = new YamlReader("src/test/resources/configuration.yaml");
        browserType = configurationReader.getString("BROWSER");
        testingMainURL = configurationReader.getString("BASE_URL");
        testingUsername = configurationReader.getString("TESTING_USERNAME");
        testingPassword = configurationReader.getString("TESTING_PASSWORD");
        registerTestingUsername = configurationReader.getString("REGISTER_TESTING_USERNAME");
        registerTestingMailPassword = configurationReader.getString("REGISTER_TESTING_MAIL_PASSWORD");

        //Cross browser selection section
        switch (browserType) {
            case "Chrome" -> driver = new CustomChromeDriver().getDriver();
            case "Firefox" -> {
                String firefoxBinaryPath = configurationReader.getString("FIREFOX_BINARY_PATH");
                driver = new CustomFirefoxDriver(firefoxBinaryPath).getDriver();
            }
            case "Edge" -> driver = new CustomEdgeDriver().getDriver();
        }

        commonMethods = new CommonMethods(driver);
        // Instantiate Resources.POM object
        homePage = new HomePage(driver, commonMethods);
        registerPage = new RegisterPage(driver, commonMethods);
        loginPage = new LoginPage(driver, commonMethods);

    }

    /**
     * InitializeBrowser
     * Suite Setup
     * Handles Resources.POM pages, configuration reading and web driver initialization for cross browser testing.
     */
    @BeforeSuite
    public void InitializeBrowser() {
        // Get to url and accept cookie if needed
        driver.get(testingMainURL);
        originalHandle = driver.getWindowHandle();
        homePage.acceptCookie();
    }

    /**
     * CloseBrowser
     * Suite Teardown
     * Closes browser after all tests are executed.
     */
    @AfterSuite
    public void CloseBrowser() {
        for (String handle : driver.getWindowHandles()) {
            driver.switchTo().window(handle);
            driver.close();
        }
    }


    /**
     * getOriginalHandle
     * Gets the original handle of first opened browser tab
     */
    public String getOriginalHandle() {
        return originalHandle;
    }

    // Following variables are used in test cases.

    public String getTestingUsername() {
        return testingUsername;
    }

    public String getTestingPassword() {
        return testingPassword;
    }

    public String getRegisterTestingUsername() {
        return registerTestingUsername;
    }

    public String getRegisterTestingMailPassword() {
        return registerTestingMailPassword;
    }

    public String getTestingMainURL() {
        return testingMainURL;
    }

}
