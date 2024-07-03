package Tests;

import Resources.POM.PageObjects.PageHeader;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

/**
 * Test Class Functionality_RegisterTests
 * Being used to execute tests based on registering to Trendyol
 */
public class RegisterTests extends BaseClass {
    static PageHeader pageHeader;

    public RegisterTests(){
        pageHeader = commonMethods.getPageHeader();
    }
    @AfterMethod(onlyForGroups = {"Tests.RegisterTests"})
    public void CaseTeardown() {
        if(commonMethods.getPageHeader().checkHoverMyAccountIsVisible())
            pageHeader.logOut();
        pageHeader.clickOnTrendyolIcon();
    }
    @Test(priority = 1, groups = {"Tests.RegisterTests"},description = "Performs an end-to-end registration with correct e-mail and correct password" +
            "Verifies registration can be done successfully when username and password is in correct format and verification code is correct.")
    public void Verify_Registering_ValidRegistration() {
        Assert.assertTrue(registerPage.performRegistrationAndReturnResult(getRegisterTestingUsername(), getTestingPassword(),
                getRegisterTestingMailPassword()));
        Assert.assertTrue(loginPage.performLogin(getRegisterTestingUsername(), getTestingPassword()));
    }

    @Test(priority = 2, groups = {"Tests.RegisterTests"},description = "Performs an end-to-end registration with empty mail and valid password" +
            "Verifies enter e-mail or password warning after invalid registration attempt." +
            "Verifies e-mail verification input is not shown after invalid registration attempt")
    public void Verify_Registering_NoUsername() {
        Assert.assertTrue(registerPage.performRegistrationAndExpectFailure("", getTestingPassword()));
        Assert.assertTrue(registerPage.checkEmptyEmailOrPasswordWarningIsShown());
    }

    @Test(priority = 3, groups = {"Tests.RegisterTests"},description = "Performs an end-to-end registration with an e-mail without '@' sign." +
            "Verifies invalid e-mail format warning is shown after invalid registration attempt." +
            "Verifies e-mail verification input is not shown after invalid registration attempt")
    public void Verify_Registering_InvalidEmailAttempt_WithoutAtSign() {
        Assert.assertTrue(registerPage.performRegistrationAndExpectFailure("yusufalti123213gmail", "Test1234."));
        Assert.assertTrue(registerPage.checkWrongEmailFormatWarningIsShown());
    }

    @Test(priority = 4, groups = {"Tests.RegisterTests"},description = "Performs an end-to-end registration with an e-mail including no mail server on it. " +
            "Verifies invalid e-mail format warning is shown after invalid registration attempt." +
            "Verifies e-mail verification input is not shown after invalid registration attempt")
    public void Verify_Registering_InvalidEmailAttempt_WithoutValidMailServer() {
        Assert.assertTrue(registerPage.performRegistrationAndExpectFailure("yusufalti123213@", "Test1234."));
        Assert.assertTrue(registerPage.checkWrongEmailFormatWarningIsShown());
    }

    @Test(priority = 5, groups = {"Tests.RegisterTests"},description = "Performs an end-to-end registration with an e-mail including no username before '@' sign. " +
            "Verifies invalid e-mail format warning is shown after invalid registration attempt." +
            "Verifies e-mail verification input is not shown after invalid registration attempt")
    public void Verify_Registering_InvalidEmailAttempt_WithoutValidMailUsername() {
        Assert.assertTrue(registerPage.performRegistrationAndExpectFailure("@gmail.com", "Test1234."));
    }

    @Test(priority = 6, groups = {"Tests.RegisterTests"},description = "Performs an end-to-end registration with a e-mail already registered to trendyol system." +
            "Verifies e-mail verification input is not shown after invalid registration attempt")
    public void Verify_Registering_InvalidEmailAttempt_ExistingUser() {
        Assert.assertTrue(registerPage.performRegistrationAndExpectFailure("yusufalti1997@gmail.com", "Test1234."));
    }

    @Test(priority = 7, groups = {"Tests.RegisterTests"},description = "Performs an end-to-end registration with a e-mail exceeding 255 characters" +
            "Verifies e-mail verification input is not shown after invalid registration attempt")
    public void Verify_Registering_InvalidEmailAttempt_ExceedingLongUsername() {
        Assert.assertTrue(registerPage.performRegistrationAndExpectFailure("ASD123.ASDPZXCJPIJSADPIASDJPAJPIVMIXPDNFSADOIPFNIOSPWEPRKPWOERPXZCMKSDMFOIAMXVCDS" +
                "AOPIFMSDMFPOWEOPMVDKDFAFMASDPMVXPOASDIPOFNOXCVOSADFPOasdfadsfgdafgaVZCXVADFASDFDSAFSAFDSA@gmail.com", "Test1234."));
    }

    @Test(priority = 8, groups = {"Tests.RegisterTests"},description = " Performs an end-to-end registration with correct e-mail and wrong password with incorrect format (lowercase letters only)." +
            "Verifies include number in password warning is shown after invalid registration attempt." +
            "Verifies e-mail verification input is not shown after invalid registration attempt")
    public void Verify_Registering_OnlyLowercasePassword() {
        Assert.assertTrue(registerPage.performRegistrationAndExpectFailure("yusufalti12312312@gmail.com", "onlylowercase"));
        Assert.assertTrue(registerPage.checkWrongPasswordIncludeNumberWarningIsShown());
    }

    @Test(priority = 9, groups = {"Tests.RegisterTests"},description = "Performs an end-to-end registration with correct e-mail and wrong password with incorrect format (uppercase letters only)." +
            "Verifies include number in password warning is shown after invalid registration attempt." +
            "Verifies e-mail verification input is not shown after invalid registration attempt")
    public void Verify_Registering_OnlyUppercasePassword() {
        Assert.assertTrue(registerPage.performRegistrationAndExpectFailure("yusufalti12312312@gmail.com", "ONLYUPPERCASE"));
        Assert.assertTrue(registerPage.checkWrongPasswordIncludeNumberWarningIsShown());
    }

    @Test(priority = 10, groups = {"Tests.RegisterTests"},description = "Performs an end-to-end registration with correct e-mail and wrong password with incorrect format (uppercase letters only)." +
            "Verifies include number in password warning is shown after invalid registration attempt." +
            "Verifies e-mail verification input is not shown after invalid registration attempt")
    public void Verify_Registering_OnlyUpperLowerSignPassword() {
        Assert.assertTrue(registerPage.performRegistrationAndExpectFailure("yusufalti12312312@gmail.com", "Onlyuplow."));
        Assert.assertTrue(registerPage.checkWrongPasswordIncludeNumberWarningIsShown());
    }

    @Test(priority = 11, groups = {"Tests.RegisterTests"},description = "Performs an end-to-end registration with correct e-mail and wrong password with shorter than 7 characters." +
            "Verifies password length warning is shown after invalid registration attempt." +
            "Verifies e-mail verification input is not shown after invalid registration attempt")
    public void Verify_Registering_ShortPassword() {
        Assert.assertTrue(registerPage.performRegistrationAndExpectFailure("yusufalti12312312@gmail.com", "asdqwe"));
        Assert.assertTrue(registerPage.checkWrongPasswordLengthWarningIsShown());
    }

    @Test(priority = 12, groups = {"Tests.RegisterTests"},description = "Performs an end-to-end registration with correct e-mail and no password." +
            "Verifies enter e-mail or password warning after invalid registration attempt." +
            "Verifies e-mail verification input is not shown after invalid registration attempt")
    public void Verify_Registering_NoPassword() {
        Assert.assertTrue(registerPage.performRegistrationAndExpectFailure("yusufalti12312312@gmail.com", ""));
        Assert.assertTrue(registerPage.checkEmptyEmailOrPasswordWarningIsShown());
    }

    @Test(priority = 13, groups = {"Tests.RegisterTests"},description = "Performs an end-to-end registration with correct e-mail and wrong password with longer than 15 characters." +
            "Verifies password length warning is shown after invalid registration attempt." +
            "Verifies e-mail verification input is not shown after invalid registration attempt")
    public void Verify_Registering_LongPassword() {
        Assert.assertTrue(registerPage.performRegistrationAndExpectFailure("yusufalti12312312@gmail.com", "asdqwezxcqweasdc"));
        Assert.assertTrue(registerPage.checkWrongPasswordLengthWarningIsShown());
    }

    @Test(priority = 14, groups = {"Tests.RegisterTests"},description = "Performs an end-to-end registration with correct e-mail and wrong password with longer than 255 characters." +
            "Verifies password length warning is shown after invalid registration attempt." +
            "Verifies e-mail verification input is not shown after invalid registration attempt")
    public void Verify_Registering_ExceedingLongPassword() {
        Assert.assertTrue(registerPage.performRegistrationAndExpectFailure("yusufalti12312312@gmail.com", "asdqweasdqweasdQWEQWAASZXCASSAD.1245ZXCASDGH" +
                "HCKXZMASDKĞZKPZXOCMSPADOMPZXOCMPSADZPXOQWEWPLZĞPXCÖZPOSĞPLZXCÜPÖVLPMSDFPZĞZKPZXOCMSPADOMPZXOCMPSADZPXOQWEWPLZĞPXCÖZPOSĞPLZXCÜPÖVLPMSDFPZXC" +
                "MKOPSADĞPDWQKPIZXCMPISAĞZKPZXOCMSPADOMPZXOCMPSADZPXOQWEWPLZĞPXCÖZPOSĞPLZXCÜPÖVLPMSDFPZXCMKOPSADĞPDWQKPIZXCMPISAĞZKPZXOCMSPADOMPZXOCMPSADZPXOQ" +
                "WEWPLZĞPXCÖZPOSĞPLZXCÜPÖVLPMSDFPZXCMKOPSADĞPDWQKPIZXCMPISAĞZKPZXOCMSPADOMPZXOCMPSADZPXOQWEWPLZĞPXCÖZPOSĞPLZXCÜPÖVLPMSDFPZXCMKOPSADĞPDWQKPIZXC" +
                "MPISAĞZKPZXOCMSPADOMPZXOCMPSADZPXOQWEWPLZĞPXCÖZPOSĞPLZXCÜPÖVLPMSDFPZXCMKOPSADĞPDWQKPIZXCMPISAĞZKPZXOCMSPADOMPZXOCMPSADZPXOQWEWPLZĞPXCÖZPOSĞPLZX" +
                "CÜPÖVLPMSDFPZXCMKOPSADĞPDWQKPIZXCMPISAXCMKOPSADĞPDWQKPIZXCMPISADNJMPSADCVBSFDGDFGEARTzxcqweasdc"));
        Assert.assertTrue(registerPage.checkWrongPasswordLengthWarningIsShown());
    }

    @Test(priority = 15, groups = {"Tests.RegisterTests"},description = "Performs an end-to-end registration with correct e-mail and wrong password with longer than 255 characters." +
            "Verifies e-mail verification input is not shown after registration attempt without checking personal data acceptance")
    public void Verify_Registering_UncheckedPersonalDataAccept() {
        Assert.assertTrue(registerPage.performRegistrationWithoutCheckingPersonalDataAccept("yusufalti12312312@gmail.com", getTestingPassword()));
    }
}
