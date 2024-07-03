package Resources.Utility.CustomDrivers;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

/**
 * CustomChromeDriver
 * This class is used when cross-browser tests are required. Check configuration .yaml file and Test suite setup to
 * see how it works.
 */
public class CustomChromeDriver extends DriverClass {

    /**
     * Constructor for CustomChromeDriver
     * Sets required options for custom chrome web driver.
     */
    public CustomChromeDriver() {
        WebDriverManager.chromedriver().setup();

        /* System.setProperty("webdriver.chrome.driver", "BrowserDrivers/chromedriver.exe"); */
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        options.addArguments("enable-automation");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-infobars");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-browser-side-navigation");
        options.addArguments("--disable-gpu");
        options.addArguments("--remote-allow-origins=*");
        super.customDriver = new ChromeDriver(options);
    }

    /**
     * getDriver
     * Gets the custom chrome driver
     *
     * @return web driver instance
     */
    public WebDriver getDriver() {
        return this.customDriver;
    }
}
