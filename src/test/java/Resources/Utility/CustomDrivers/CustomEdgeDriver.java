package Resources.Utility.CustomDrivers;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

/**
 * CustomEdgeDriver
 * This class is used when cross-browser tests are required. Check configuration .yaml file and Test suite setup to
 * see how it works.
 */
public class CustomEdgeDriver extends DriverClass {

    /**
     * Constructor for CustomEdgeDriver
     * Sets required options for custom edge web driver.
     */
    public CustomEdgeDriver() {
        WebDriverManager.edgedriver().setup();

        /* System.setProperty("webdriver.edge.driver", "BrowserDrivers/msedgedriver.exe"); */
        EdgeOptions options = new EdgeOptions();
        options.addArguments("start-maximized");
        options.addArguments("enable-automation");
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-infobars");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-browser-side-navigation");
        options.addArguments("--disable-gpu");
        super.customDriver = new EdgeDriver(options);
    }

    /**
     * getDriver
     * Gets the custom edge driver
     *
     * @return web driver instance
     */
    public WebDriver getDriver() {
        return this.customDriver;
    }
}
