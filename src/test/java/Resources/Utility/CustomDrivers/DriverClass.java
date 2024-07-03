package Resources.Utility.CustomDrivers;

import org.openqa.selenium.WebDriver;

public abstract class DriverClass {
    WebDriver customDriver;

    public abstract WebDriver getDriver();

}
