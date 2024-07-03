package Resources.POM.PageObjects;

import Resources.POM.Common.CommonMethods;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * HomePageLogin
 * This class is used to keep functions and locators about web elements on login window
 */
public class LoginPage {
    CommonMethods commonMethods;
    WebDriver driver;
    By logInButton = By.xpath("//button[@type = 'submit']");
    By emailInput = By.id("login-email");
    By passwordInput = By.id("login-password-input");
    By invalidEmailWarning = By.xpath("//span[text() = 'Lütfen geçerli bir e-posta adresi giriniz.' and @class = 'message']");
    By emptyPasswordWarning = By.xpath("//span[text() = 'Lütfen şifrenizi giriniz.' and @class = 'message']");
    By wrongPasswordOrMailWarning = By.xpath("//span[text() = 'E-posta adresiniz ve/veya şifreniz hatalı.' and @class = 'message']");

    /**
     * Constructor of HomePageLogin
     *
     * @param driver: Driver instance to keep
     */
    public LoginPage(WebDriver driver, CommonMethods commonMethods) {
        this.driver = driver;
        this.commonMethods = commonMethods;
    }

    /**
     * inputLoginEmail
     * Inputs text to e-mail input element
     *
     * @param email text to input
     */
    public void inputLoginEmail(String email) {
        commonMethods.inputText(emailInput, email);
    }

    /**
     * inputLoginPassword
     * Inputs password to password input element
     *
     * @param password text to input
     */
    public void inputLoginPassword(String password) {
        commonMethods.inputText(passwordInput, password);
    }

    /**
     * checkInvalidEmailWarningIsShown
     * Checks invalid e-mail warning is shown after an invalid e mail input is given and login button is clicked
     */
    public boolean checkInvalidEmailWarningIsShown() {
        return commonMethods.waitUntilElementIsVisible(invalidEmailWarning);
    }

    /**
     * checkEmptyPasswordWarningIsShown
     * Checks empty password warning is shown after an empty password input is given and login button is clicked
     */
    public boolean checkEmptyPasswordWarningIsShown() {
        return commonMethods.waitUntilElementIsVisible(emptyPasswordWarning);
    }

    /**
     * checkWrongPasswordOrMailWarningIsShown
     * Checks invalid username or password error is shown or not.
     */
    public boolean checkWrongPasswordOrMailWarningIsShown() {
        return commonMethods.waitUntilElementIsVisible(wrongPasswordOrMailWarning);
    }

    /**
     * clickLogIn
     * Clicks on Login button
     */
    public void clickLogIn() {
        commonMethods.clickElement(logInButton);
    }

    /**
     * performLogin
     * Performs end-to-end login.
     *
     * @param username Username to login.
     * @param password Password of username to login.
     * @return Returns true if 'Hesabım' hover menu is visible.
     */
    public boolean performLogin(String username, String password) {
        this.commonMethods.getPageHeader().clickOnLogIn();
        inputLoginEmail(username);
        inputLoginPassword(password);
        clickLogIn();
        commonMethods.waitUntilLoadingIconIsNotVisible();
        return this.commonMethods.getPageHeader().checkHoverMyAccountIsVisible();
    }
}
