package com.onlinebookstore.testcases;

import com.onlinebookstore.pages.HomePage;
import com.onlinebookstore.pages.LoginPage;
import com.onlinebookstore.pages.RegisterPage;
import com.onlinebookstore.testbase.TestBase;
import com.onlinebookstore.utils.ReadDataFromExcel;
import com.onlinebookstore.utils.ReadDataFromJson;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class RegisterPageTest extends TestBase {

    HomePage homePage = null;
    RegisterPage registerPage = null;

    LoginPage loginPage = null;

    public RegisterPageTest() {
        super();
    }

    @DataProvider(name = "RegisterPageData")
    public Iterator<Object[]> getData() {
        ReadDataFromExcel excel = new ReadDataFromExcel();
        //int[] num = new int[]{1}
        //num[0] = 1
        Object[] obj = new Object[]{excel.getDataFromSheet("RegisterPage")};//
        List<Object[]> data = new ArrayList<>();
        data.add(obj);
        return data.iterator();
    }

    @DataProvider(name = "RegisterPageDataJson")
    public Iterator<Object[]> getDataJson() {
        ReadDataFromJson readDataFromJson = new ReadDataFromJson();
        Object[] obj = new Object[]{readDataFromJson.readDataFromJson()};//
        List<Object[]> data = new ArrayList<>();
        data.add(obj);
        return data.iterator();
    }

    @Test(dataProvider = "RegisterPageData")
    public void validateSuccessfulUserRegistration(Object[] data) {
        final HashMap<String, String> datum = (HashMap<String, String>) data[0];
        launchApplication();
        homePage = new HomePage();
        registerPage = homePage.navigateToRegistrationPage();
        loginPage = registerPage.performUserRegistration(datum);
        loginPage.verifyUserRegisteredSuccessfully("User Registered Successfully");
    }

    @Test(dataProvider = "RegisterPageDataJson")
    public void validateSuccessfulUserRegistrationPage(Object[] data) {
        final HashMap<String, String> datum = (HashMap<String, String>) data[0];
        launchApplication();
        homePage = new HomePage();
        registerPage = homePage.navigateToRegistrationPage();
        loginPage = registerPage.performUserRegistration(datum);
        loginPage.verifyUserRegisteredSuccessfully("User Registered Successfully");
    }
}