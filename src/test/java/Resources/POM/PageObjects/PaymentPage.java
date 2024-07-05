package Resources.POM.PageObjects;

import Resources.POM.Common.CommonMethods;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;

public class PaymentPage {

    CommonMethods commonMethods;

    By addressInformation = By.xpath("//p[text() = 'Adres Bilgileri']");
    By paymentInformation = By.xpath("//p[text() = 'Ödeme Seçenekleri]'");
    By saveAndContinue = By.xpath("//button[text() = 'Kaydet ve Devam Et']");
    By approveAgreement = By.xpath("//label[@class = 'p-checkbox-wrapper']");
    By warning = By.xpath("//span[text() = 'Lütfen belirtilen alanları kontrol ediniz.']");
    By pay = By.xpath("//button[@class = 'ty-primary-btn ty-btn-large ty-disabled-secondary' and text = 'Ödeme Yap']");
    By cardNumber = By.id("card-number");
    By month = By.id("card-date-month");
    By year = By.id("card-date-year");
    By cvv = By.id("card-cvv");

    public PaymentPage(WebDriver driver, CommonMethods commonMethods) {
        this.commonMethods = commonMethods;
    }

    public void verifyAddressInformationIsShown() {
        commonMethods.waitUntilElementIsVisible(addressInformation);
    }

    public void verifyPaymentInformationIsShown() {
        commonMethods.waitUntilElementIsVisible(paymentInformation);
    }

    public void saveAndContinue() {
        commonMethods.clickElement(saveAndContinue);
    }

    public void approveAgreement() {
        commonMethods.clickElement(approveAgreement);
    }

    public void inputCardNumber(String cardNumberText) {
        commonMethods.inputText(cardNumber, cardNumberText);
    }

    public void selectMonth(String monthText) {
        commonMethods.selectFromList(month, monthText);

    }

    public void selectYear(String yearText) {
        commonMethods.selectFromList(year, yearText);

    }

    public void inputCvv(String cvvText){
        commonMethods.inputText(cvv, cvvText);
    }

    public void clickOnPayButton(){
        commonMethods.clickElement(pay);
    }

    public boolean verifyWarningIsShown(){
        try{
            commonMethods.waitUntilElementIsVisible(warning);
            return true;
        } catch (TimeoutException e){
            return false;
        }
    }
}

