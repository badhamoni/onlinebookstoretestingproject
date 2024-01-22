package com.onlinebookstore.testcases;

import com.onlinebookstore.pages.HomePage;
import com.onlinebookstore.pages.LoginPage;
import com.onlinebookstore.testbase.TestBase;
import org.apache.log4j.Logger;
import org.testng.annotations.Test;

public class LoginPageTest extends TestBase {
    Logger log = Logger.getLogger(LoginPageTest.class.getName());
    HomePage homePage = null;
    LoginPage loginPage = null;

    public LoginPageTest() {
        super();
    }

    @Test(description = "Validating whether the user able to login successfully with Admin Credentials")
    public void validateAdminLoginWithValidCredentials() {
        launchApplication();
        log.info("Step 1: Navigate to Home Page");
        homePage = new HomePage();//creates copy of home page
        log.info("Step 2: Confirm Page Navigate to Home Page");
        homePage.confirmPageNavigation();
        addScreenshot();
        log.info("Step 3: Click on Login Button to Navigate to Login Page");
        loginPage = homePage.clickOnLogin();
        addScreenshot();
        log.info("Step 4: Confirm Page Navigate to Login Page");
        loginPage.confirmPageNavigation();
        log.info("Step 5: Click on Admin Link");
        loginPage.clickOnAdminLoginLink();
        addScreenshot();
        log.info("Step 6: Peform Admin Login");
        homePage = loginPage.performAdminLogin(properties.getProperty("adminusername"), properties.getProperty("adminpassword"));
        log.info("Step 7: Confirm Page Navigation to Home Page");
        addScreenshot();
//        homePage.confirmPageNavigation();
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

    @Test
    public void sampleTest() {
        log.info("I am writing a sample log into test log");
    }
}