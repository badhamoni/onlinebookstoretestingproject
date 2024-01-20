package com.onlinebookstore.pages;

import com.onlinebookstore.testbase.TestBase;
import org.openqa.selenium.By;
import org.testng.Assert;

public class LoginPage extends TestBase {
    String pageName = "Login Page";
    private By lbl_LoginPageHeader = By.xpath("//a[@class='nav-link active']");
    private By lnk_AdminLogin = By.xpath("//a[contains(@href,'SellerLogin')]");
    private By txt_Username = By.id("userName");
    private By txt_Password = By.id("Password");
    private By btn_LoginAsAdmin = By.xpath("//input[@class='AdminLogin']");
    private By lbl_ErrorMessageInvalidLogin = By.xpath("//div[@class='tab']");
    private By lbl_Captcha = By.xpath("//div[@id='CaptchaDiv']");
    private By txt_CaptchInput = By.xpath("//input[@id='CaptchaInput']");
    private By btn_LoginAsUser = By.xpath("//input[contains(@value,'Login as an User')]");
    private By lbl_UserRegistrationMsg = By.xpath("//form[@id='theform']/following-sibling::table//td");

    public LoginPage() {
    }

    public By getLbl_LoginPageHeader() {
        return lbl_LoginPageHeader;
    }

    public By getLnk_AdminLogin() {
        return lnk_AdminLogin;
    }

    public By getTxt_Username() {
        return txt_Username;
    }

    public By getTxt_Password() {
        return txt_Password;
    }

    public By getBtn_LoginAsAdmin() {
        return btn_LoginAsAdmin;
    }

    public By getLbl_ErrorMessageInvalidLogin() {
        return lbl_ErrorMessageInvalidLogin;
    }

    public By getLbl_Captcha() {
        return lbl_Captcha;
    }

    public By getBtn_LoginAsUser() {
        return btn_LoginAsUser;
    }

    public void confirmPageNavigation() {
        if (isElementExists(lbl_LoginPageHeader)) {
            System.out.println(String.format("Page Successfully Navigated to %s", this.pageName));
        } else {
            System.out.println(String.format("Not abel to Navigate to %s", this.pageName));
        }
    }

    public void clickOnAdminLoginLink() {
        System.out.println(String.format("Click on Admin Login link on %s", this.pageName));
        click(lnk_AdminLogin);
    }

    public HomePage performAdminLogin(String userName, String password) {
        System.out.println(String.format("Enter username as %s on %s", userName, this.pageName));
        setText(txt_Username, userName);
        System.out.println(String.format("Enter password as %s on %s", password, this.pageName));
        setText(txt_Password, password);
        System.out.println(String.format("Click on Login as Admin button on %s", this.pageName));
        click(btn_LoginAsAdmin);
        return new HomePage();
    }

    public HomePage performCustomerLogin(String userName, String password) {
        System.out.println(String.format("Enter username as %s on %s", userName, this.pageName));
        setText(txt_Username, userName);
        System.out.println(String.format("Enter password as %s on %s", password, this.pageName));
        setText(txt_Password, password);
        System.out.println(String.format("Click on Login as Admin button on %s", this.pageName));
        String captcha = getElementText(lbl_Captcha);
        setText(txt_CaptchInput, captcha);
        click(btn_LoginAsUser);
        return new HomePage();
    }

    public void verifyErrorMessageForInvalidAdminLogin() {
        Assert.assertEquals(getElementText(lbl_ErrorMessageInvalidLogin), "Incorrect UserName or PassWord");
        System.out.println("As expected user is not able to login with invalid credentials");
        confirmPageNavigation();
    }

    public void verifyUserRegisteredSuccessfully(String expectedResult) {
        testStep().info(String.format("Verify %s is displayed on %s", expectedResult, this.pageName));
        String actualResult = getElementText(lbl_UserRegistrationMsg);
        if (isElementExists(lbl_UserRegistrationMsg)) {
            Assert.assertEquals(actualResult, expectedResult);
            testStep().pass(String.format("As expected %s is matching with %s", expectedResult, actualResult));
        } else {
            testStep().fail(String.format("Not as expected %s is not matching with %s", expectedResult, actualResult));
        }
    }
}
