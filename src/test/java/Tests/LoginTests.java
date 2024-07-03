package Tests;

import Resources.POM.PageObjects.PageHeader;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

/**
 * Test Class Functionality_LoginTests
 * Being used to execute tests based on login scenarios on Trendyol
 */
public class LoginTests extends BaseClass {

    static PageHeader pageHeader;

    public LoginTests(){
        pageHeader = commonMethods.getPageHeader();
    }
    @AfterMethod(onlyForGroups = {"Tests.LoginTests"})
    public void CaseTeardown() {
        if(commonMethods.getPageHeader().checkHoverMyAccountIsVisible())
            pageHeader.logOut();
        pageHeader.clickOnTrendyolIcon();
    }

    @Test(priority = 1, groups = {"Tests.LoginTests"},
            description = "Tries to login with correct user values. " +
            "Expects 'Hesabım' hover menu is shown.")
    public void Verify_Login_ValidLogin() {
        BaseClass.loginPage.performLogin(getTestingUsername(), getTestingPassword());
        Assert.assertTrue(commonMethods.getPageHeader().checkHoverMyAccountIsVisible());
    }

    @Test(priority = 2, groups = {"Tests.LoginTests"}, description = "Tries to login with a incorrect e-mail format (without @)." +
            "Verifies Invalid e-mail warning is shown after invalid login attempt." +
            "Verifies 'Hesabım' hover menu is not shown after invalid login attempt.")
    public void Verify_Login_InvalidUsernameAttempt() {
        Assert.assertFalse(loginPage.performLogin("yusufalti333gmail.com", getTestingPassword()));
        Assert.assertTrue(loginPage.checkInvalidEmailWarningIsShown());
    }

    @Test(priority = 2, groups = {"Tests.LoginTests"}, description = "Tries to login with a correct mail and correct password, but the user is not present in the system." +
            "Verifies wrong username or password warning is shown after the login attempt." +
            "Verifies 'Hesabım' hover menu is not shown after invalid login attempt.")
    public void Verify_Login_NonExistingUser() {
        Assert.assertFalse(loginPage.performLogin("denemeeeeee@deneme.com", "Deneme123."));
        Assert.assertTrue(loginPage.checkWrongPasswordOrMailWarningIsShown());
    }

    @Test(priority = 3, groups = {"Tests.LoginTests"}, description = "Tries to login with a incorrect e-mail format (length longer than 255). " +
            "Verifies Invalid e-mail warning is shown after invalid login attempt." +
            "Verifies 'Hesabım' hover menu is not shown after invalid login attempt.")
    public void Verify_Login_ExceedingUsernameLength() {
        Assert.assertFalse(loginPage.performLogin("ASDQWEWQE1231231432AWQWEZXCXCZXCQ@@@ZĞCKOFKSZĞCKOFKSDOPFKPOD" +
                "AOKGPADGPĞZKPZXOCMSPADOMPZXOCMPSADZPXOQWEWPLZĞPXCÖZPOSĞPLZXCÜPÖVLPMSDFPZXCMKOPSADĞPDWQKPIZXCMPISAĞZKPZXOCMSP" +
                "DOMPZXOCMPSADZPXOQWEWPLZĞPXCÖZPOSĞPLZXCÜPÖVLPMSDFPZXCMKOPSADĞPDWQKPIZXCMPISADFOGMDFPGMFOINOIRENTORENTEAOTERADF" +
                "GFGFDZĞCKOFKSDOPFKPODAOKGPADGPDFOGMDFPGMFOINOIRENTORENTEAOTERADFGFGFDZĞCKOFKSDOPFKPODAOKGPADGPDFOGMDFPGMFOINOIRE" +
                "NTORENTEAOTERADFGFGFDZĞCKOFKSDOPFKPODAOKGPADGPDFOGMDFPGMFOINOIRENTORENTEAOTERADFGFGFDZĞCKOFKSDOPFKPODAOKGPADGPDFOG" +
                "MDFPGMFOINOIRENTORENTEAOTERADFGFGFDZĞCKOFKSDOPFKPODAOKGPADGPDFOGMDFPGMFOINOIRENTORENTEAOTERADFGFGFDZĞCKOFKSDOPFKPODAOK" +
                "GPADGPDFOGMDFPGMFOINOIRENTORENTEAOTERADFGFGFDZĞCKOFKSDOPFKPODAOKGPADGPDFOGMDFPGMFOINOIRENTORENTEAOTERADFGFGFDDOPFKPO" +
                "DAOKGPADGPDFOGMDFPGMFOINOIRENTORENTEAOTERADFGFGFD", getTestingPassword()));
        Assert.assertTrue(loginPage.checkInvalidEmailWarningIsShown());
    }

    @Test(priority = 4, groups = {"Tests.LoginTests"}, description = "Tries to login with a incorrect e-mail format (more ambigue characters only)." +
            "Verifies Invalid e-mail warning is shown after invalid login attempt." +
            "Verifies 'Hesabım' hover menu is not shown after invalid login attempt.")
    public void Verify_Login_AmbigueUsernameCharacters() {
        Assert.assertFalse(loginPage.performLogin("--*1*23*140506'434*3466345*34*--123-12-5436][}][{[½{½{½$½$½@@@..;;", getTestingPassword()));
        Assert.assertTrue(loginPage.checkInvalidEmailWarningIsShown());
    }

    @Test(priority = 5, groups = {"Tests.LoginTests"}, description = "Tries to login with a incorrect e-mail format (empty username)." +
            "Verifies Invalid e-mail warning is shown after invalid login attempt." +
            "Verifies 'Hesabım' hover menu is not shown after invalid login attempt.")
    public void Verify_Login_EmptyUsername() {
        Assert.assertFalse(loginPage.performLogin("", getTestingPassword()));
        Assert.assertTrue(loginPage.checkInvalidEmailWarningIsShown());
    }

    @Test(priority = 5, groups = {"Tests.LoginTests"}, description = "Tries to login with a correct e-mail format but not registered before." +
            "Verifies Invalid e-mail warning is shown after invalid login attempt." +
            "Verifies 'Hesabım' hover menu is not shown after invalid login attempt.")
    public void Verify_Login_NonExistsUsername() {
        Assert.assertFalse(loginPage.performLogin("yusufalti4444@gmail.com", getTestingPassword()));
        Assert.assertTrue(loginPage.checkWrongPasswordOrMailWarningIsShown());
    }

    @Test(priority = 6, groups = {"Tests.LoginTests"}, description = "Tries to login with a correct mail and incorrect password format (empty password)." +
            "Verifies Invalid password warning is shown after invalid login attempt." +
            "Verifies 'Hesabım' hover menu is not shown after invalid login attempt.")
    public void Verify_Login_EmptyPassword() {
        Assert.assertFalse(loginPage.performLogin("yusufalti333@gmail.com", ""));
        Assert.assertTrue(loginPage.checkEmptyPasswordWarningIsShown());
    }

    @Test(priority = 7, groups = {"Tests.LoginTests"}, description = "Tries to login with a correct mail and incorrect password format (empty password)." +
            "Verifies Invalid password warning is shown after invalid login attempt." +
            "Verifies 'Hesabım' hover menu is not shown after invalid login attempt.")
    public void Verify_Login_ExceedingPasswordLength() {
        Assert.assertFalse(loginPage.performLogin("yusufalti333@gmail.com", "ASDQWEWQE1.231231432AWQWEZXCXCZXCQ@@@ZĞCKOFKSZĞCKOFKSDOPFKP" +
                "ODAOKGPADGPĞZKPZXOCMSPADOMPZXOCMPSADZPXOQWEWPLZĞPXCÖZPOSĞPLZXCÜPÖVLPMSDFPZXCMKOPSADĞPDWQKPIZXCMPISAĞZKPZX" +
                "OCMSPADOMPZXOCMPSADZPXOQWEWPLZĞPXCÖZPOSĞPLZXCÜPÖVLPMSDFPZXCMKOPSADĞPDWQKPIZXCMPISADFOGMDFPGMFOINOIRENTOR" +
                "ENTEAOTERADFGFGFDZĞCKOFKSDOPFKPODAOKGPADGPDFOGMDFPGMFOINOIRENTORENTEAOTERADFGFGFDZĞCKOFKSDOPFKPODAOKGPADGP" +
                "DFOGMDFPGMFOINOIRENTORENTEAOTERADFGFGFDZĞCKOFKSDOPFKPODAOKGPADGPDFOGMDFPGMFOINOIRENTORENTEAOTERADFGFGFDZĞCK" +
                "OFKSDOPFKPODAOKGPADGPDFOGMDFPGMFOINOIRENTORENTEAOTERADFGFGFDZĞCKOFKSDOPFKPODAOKGPADGPDFOGMDFPGMFOINOIRENTORE" +
                "NTEAOTERADFGFGFDZĞCKOFKSDOPFKPODAOKGPADGPDFOGMDFPGMFOINOIRENTORENTEAOTERADFGFGFDZĞCKOFKSDOPFKPODAOKGPADGPDFOGM" +
                "DFPGMFOINOIRENTORENTEAOTERADFGFGFDDOPFKPODAOKGPADGPDFOGMDFPGMFOINOIRENTORENTEAOTERADFGFGFD"));
        Assert.assertTrue(loginPage.checkWrongPasswordOrMailWarningIsShown());
    }

    @Test(priority = 8, groups = {"Tests.LoginTests"}, description = "Tries to login with a correct mail adress that contains SQL Injection" +
            "Verifies wrong username or password warning is shown after the login attempt." +
            "Verifies 'Hesabım' hover menu is not shown after invalid login attempt.")
    public void Verify_Login_SQLInjection() {
        Assert.assertFalse(loginPage.performLogin("SELECT * FROM Users WHERE 1=1; DROP TABLE Users;", "Deneme123."));
        Assert.assertTrue(loginPage.checkInvalidEmailWarningIsShown());
    }
}
