package Resources.POM.PageObjects;

import Resources.POM.Common.CommonMethods;
import Resources.Utility.OutlookHandler;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;

/**
 * HomePageRegister
 * This class is used to keep functions and locators about web elements on main page and register window
 */
public class RegisterPage {
    OutlookHandler outlookHandler;
    CommonMethods commonMethods;
    PageHeader pageHeader;
    WebDriver driver;
    By registrationTabButton = By.xpath("//button[@class = 'q-secondary q-button-medium q-button tab button right ']");
    By verificationKeyAcceptButton = By.xpath("//button[text() = 'Onayla']");
    By maleButton = By.xpath("//button[@class = 'q-gray q-fluid q-button-medium q-button male  ']");
    By femaleButton = By.xpath("//button[@class = 'q-gray q-fluid q-button-medium q-button female   border-right-none']");
    By closeVerificationWindowButton = By.xpath(("//div[@class = 'ty-modal-content ty-relative']/a"));
    By registerButton = By.xpath("//button[@class = 'q-primary q-fluid q-button-medium q-button submit']");
    By emailVerificationInput = By.xpath("//input[@class = 'ty-bg-beige ty-input ty-textbox ty-bordered ty-input-small']");
    By registerEmailInputInput = By.id("register-email");
    By registerPasswordInput = By.id("register-password-input");
    By personalDataCheckbox = By.xpath("//div[@class = 'personal-checkbox']/div/div/div");
    By wrongPasswordIncludeNumberWarning = By.xpath("//span[text() = 'Şifreniz en az 1 rakam içermelidir.' and @class = 'message']");
    By wrongPasswordLengthWarning = By.xpath("//span[text() = 'Şifreniz 7 ile 15 karakter arasında olmalıdır.' and @class = 'message']");
    By existingEmailWarning = By.xpath("//span[text() = 'Bu e-posta adresi kullanılamaz. Lütfen başka bir e-posta adresi deneyiniz.' and @class = 'message']");
    By wrongEmailFormatWarning = By.xpath("//span[text() = 'Lütfen geçerli bir email adresi giriniz.' and @class = 'message']");
    By emptyEmailOrPasswordWarning = By.xpath("//span[text() = 'E-posta ve şifrenizi giriniz.' and @class = 'message']");
    By recaptchaIframe = By.xpath("//iframe[@title = 'reCAPTCHA']");
    By recaptchaAcceptBox = By.xpath("//div[@class ='recaptcha-checkbox-border']/..");
    /**
     * Constructor of HomePageRegister
     * Creates a new outlook handler
     *
     * @param driver: Driver instance to keep
     */
    public RegisterPage(WebDriver driver, CommonMethods commonMethods) {
        this.driver = driver;
        this.commonMethods = commonMethods;
        this.pageHeader = commonMethods.getPageHeader();
        this.outlookHandler = new OutlookHandler();
    }

    /**
     * openRegistrationSubTab
     * Clicks on the register tab button in login page.
     */
    public void openRegistrationSubTab() {
        commonMethods.clickElement(registrationTabButton);
    }

    /**
     * inputRegistrationEmail
     * Inputs given string to registration e-mail input
     *
     * @param email: E-mail to input
     */
    public void inputRegistrationEmail(String email) {
        commonMethods.inputText(registerEmailInputInput, email);
    }

    /**
     * inputRegistrationPassword
     * Inputs given string to registration password input
     *
     * @param password: Password to input
     */
    public void inputRegistrationPassword(String password) {
        commonMethods.inputText(registerPasswordInput, password);
    }

    /**
     * clickOnMaleButton
     * Clicks on the male button in registration page.
     */
    public void clickOnMaleButton() {
        commonMethods.clickElement(maleButton);
    }

    /**
     * clickOnFemaleButton
     * Clicks on the female button in registration page.
     */
    public void clickOnFemaleButton() {
        commonMethods.clickElement(femaleButton);
    }

    /**
     * clickOnPersonalDataAcceptCheckbox
     * Clicks on personal data acceptance checkbox.
     */
    public void clickOnPersonalDataAcceptCheckbox() {
        commonMethods.clickElement(personalDataCheckbox);
    }

    /**
     * clickOnRegisterButton
     * Clicks on the register button in registration page.
     */
    public void clickOnRegisterButton() {
        commonMethods.clickElement(registerButton);
    }

    public void switchToRecaptchaIframe() {
        commonMethods.waitUntilElementIsVisibleAndClickable(recaptchaIframe);
        driver.switchTo().frame(driver.findElement(recaptchaIframe));
    }

    public void switchToMainIframe() {
        driver.switchTo().defaultContent();
    }
    public void clickOnRecaptchaAcceptBox() {
        commonMethods.clickElement(recaptchaAcceptBox);
    }

    /**
     * clickOnVerificationKeyAcceptButton
     * Clicks on accept button in e-mail verification window
     */
    public void clickOnVerificationKeyAcceptButton() {
        if (!commonMethods.waitUntilElementIsVisibleAndClickable(verificationKeyAcceptButton)) {
            Reporter.log("Verification code accept button is not shown or clickable", 5);
            System.out.println("Verification code accept button is not shown or clickable");
            return;
        }
        commonMethods.clickElement(verificationKeyAcceptButton);
    }

    /**
     * checkWrongPasswordIncludeNumberWarningIsShown
     * Checks wrong password format warning is shown after a correct mail and wrong password (including only letters) input is given,
     * personal data checkbox is clicked and register button is clicked.
     */
    public boolean checkWrongPasswordIncludeNumberWarningIsShown() {
        return commonMethods.waitUntilElementIsVisible(wrongPasswordIncludeNumberWarning);
    }

    /**
     * checkWrongPasswordLengthWarningIsShown
     * Checks wrong password length warning is shown after a correct mail and wrong password input by length is given,
     * personal data checkbox is clicked and register button is clicked.
     */
    public boolean checkWrongPasswordLengthWarningIsShown() {
        return commonMethods.waitUntilElementIsVisible(wrongPasswordLengthWarning);
    }

    /**
     * checkWrongEmailFormatWarningIsShown
     * Checks wrong e-mail format warning is shown after a wrong mail and correct password input is given,
     * personal data checkbox is clicked and register button is clicked.
     */
    public boolean checkWrongEmailFormatWarningIsShown() {
        if (!commonMethods.waitUntilElementIsVisible(wrongEmailFormatWarning)) {
            clickOnRegisterButton();
            return commonMethods.waitUntilElementIsVisible(wrongEmailFormatWarning);
        } else {
            return true;
        }
    }

    /**
     * checkEmptyEmailOrPasswordWarningIsShown
     * Checks empty e-mail or password warning is shown after an empty mail and correct password input is given,
     * personal data checkbox is clicked and register button is clicked.
     */
    public boolean checkEmptyEmailOrPasswordWarningIsShown() {
        if (!commonMethods.waitUntilElementIsVisible(emptyEmailOrPasswordWarning)) {
            clickOnRegisterButton();
            return commonMethods.waitUntilElementIsVisible(emptyEmailOrPasswordWarning);
        } else {
            return true;
        }
    }

    /**
     * checkEmailVerificationInputIsNotShown
     * Checks email verification code input is not shown
     */
    public boolean checkEmailVerificationInputIsNotShown() {
        return !commonMethods.waitUntilElementIsVisible(emailVerificationInput);
    }

    /**
     * findAndInputEmailVerificationCode
     * Logs in to given e-mail, retrieves the Trendyol e-mail verification code from mails and inputs it to verification code input.
     *
     * @param emailToLogIn:    E-mail address. Currently only supports @outlook.com.
     * @param passwordOfEmail: Password of given E-mail address.
     */
    public void findAndInputEmailVerificationCode(String emailToLogIn, String passwordOfEmail) {
        if (!commonMethods.waitUntilElementIsVisibleAndClickable(emailVerificationInput)) {
            return;
        }
        WebElement registrationPasswordInputElement = driver.findElement(emailVerificationInput);
        String[] verificationCode = outlookHandler.startSessionAndGetVerificationCode(emailToLogIn, passwordOfEmail);

        // If verification code is null, close verification window
        if (verificationCode == null) {
            driver.findElement(closeVerificationWindowButton).click();
            return;
        }
        StringBuilder codeToInput = new StringBuilder();
        for (int i = 0; i < verificationCode.length; i++) {
            codeToInput.append(verificationCode[i]);
        }
        try {
            // Copy verification code to clipboard
            StringSelection selection = new StringSelection(codeToInput.toString());
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            clipboard.setContents(selection, selection);

            // Do Ctrl + v to verification input
            registrationPasswordInputElement.click();
            registrationPasswordInputElement.sendKeys(Keys.CONTROL + "v");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * performRegistrationWithoutCheckingPersonalDataAccept
     * Performs end-to-end register. Decision mechanism is ending by checking e-mail verification input is shown or not.
     *
     * @param username Username to register.
     * @param password Password of username to register.
     * @return Returns true if  e-mail verification input hover menu is not visible
     */
    public boolean performRegistrationWithoutCheckingPersonalDataAccept(String username, String password) {
        pageHeader.clickOnLogIn();
        openRegistrationSubTab();
        switchToRecaptchaIframe();
        clickOnRecaptchaAcceptBox();
        switchToMainIframe();
        inputRegistrationEmail(username);
        inputRegistrationPassword(password);
        clickOnFemaleButton();
        clickOnRegisterButton();

        //Check e-mail verification input is not shown
        return checkEmailVerificationInputIsNotShown();
    }

    /**
     * performRegistrationAndExpectFailure
     * Performs end-to-end register. Decision mechanism is ending by checking e-mail verification input is shown or not.
     *
     * @param username Username to register.
     * @param password Password of username to register.
     * @return Returns true if e-mail verification input hover menu is not visible
     */
    public boolean performRegistrationAndExpectFailure(String username, String password) {
        pageHeader.clickOnLogIn();
        openRegistrationSubTab();
        switchToRecaptchaIframe();
        clickOnRecaptchaAcceptBox();
        switchToMainIframe();
        inputRegistrationEmail(username);
        inputRegistrationPassword(password);
        clickOnFemaleButton();
        clickOnPersonalDataAcceptCheckbox();
        clickOnRegisterButton();

        //Check e-mail verification input is not shown
        return checkEmailVerificationInputIsNotShown();
    }

    /**
     * performRegistrationAndReturnResult
     * Performs end-to-end register. Decision mechanism is ending by checking 'Hesabım' text is finally shown or not at the end of registration.
     *
     * @param username Username to register.
     * @param password Password of username to register.
     * @return Returns true if 'Hesabım' hover menu is visible
     */
    public boolean performRegistrationAndReturnResult(String username, String password, String passwordOfEmail) {
        pageHeader.clickOnLogIn();
        openRegistrationSubTab();
        switchToRecaptchaIframe();
        clickOnRecaptchaAcceptBox();
        switchToMainIframe();
        inputRegistrationEmail(username);
        inputRegistrationPassword(password);
        clickOnMaleButton();
        clickOnPersonalDataAcceptCheckbox();
        clickOnRegisterButton();
        findAndInputEmailVerificationCode(username, passwordOfEmail);
        clickOnVerificationKeyAcceptButton();
        System.out.println("Registering completed. Checiking 'Hesabım' text now..");
        Reporter.log("Registering completed. Checiking 'Hesabım' text now..", 3);
        pageHeader.clickOnTrendyolIcon();

        //Check 'Hesabım' text is visible or not, then return
        return pageHeader.checkHoverMyAccountIsVisible();
    }
}
