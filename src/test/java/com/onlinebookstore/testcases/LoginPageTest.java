package com.onlinebookstore.testcases;

import com.onlinebookstore.pages.HomePage;
import com.onlinebookstore.pages.LoginPage;
import com.onlinebookstore.testbase.TestBase;
import org.testng.annotations.Test;

public class LoginPageTest extends TestBase {

    HomePage homePage = null;
    LoginPage loginPage = null;

    public LoginPageTest() {
        super();
    }

    @Test(description = "Validating whether the user able to login successfully with Admin Credentials")
    public void validateAdminLoginWithValidCredentials() {
        launchApplication();
        setTestStep("Step 1: Navigate to Home Page");
        homePage = new HomePage();//creates copy of home page
        homePage.confirmPageNavigation();
        setTestStep("Step 2: Click on Login Button to Navigate to Login Page");
        loginPage = homePage.clickOnLogin();
        loginPage.confirmPageNavigation();
        loginPage.clickOnAdminLoginLink();
        homePage = loginPage.performAdminLogin(properties.getProperty("adminusername"), properties.getProperty("adminpassword"));
        homePage.confirmPageNavigation();
    }

    @Test(description = "Verify customer login is successful")
    public void customerLogin() {
        launchApplication();
        setTestStep("Step 1: Navigate to Home Page");
        homePage = new HomePage();//creates copy of home page
        homePage.confirmPageNavigation();

        setTestStep("Step 2: Click on Login Button to Navigate to Login Page");
        homePage = new HomePage();
        loginPage = homePage.clickOnLogin();
        loginPage.confirmPageNavigation();
        homePage = loginPage.performCustomerLogin(properties.getProperty("username"), properties.getProperty("password"));
    }

    @Test(description = "Validating whether the user able to login successfully with Admin Credentials")
    public void validateAdminLoginWithInValidCredentials() {
        launchApplication();
        setTestStep("Step 1: Navigate to Home Page");
        homePage = new HomePage();//creates copy of home page
        homePage.confirmPageNavigation();
        setTestStep("Step 2: Click on Login Button to Navigate to Login Page");
        loginPage = homePage.clickOnLogin();
        loginPage.confirmPageNavigation();
        loginPage.clickOnAdminLoginLink();
        homePage = loginPage.performAdminLogin("", "");
        loginPage.verifyErrorMessageForInvalidAdminLogin();
    }
}