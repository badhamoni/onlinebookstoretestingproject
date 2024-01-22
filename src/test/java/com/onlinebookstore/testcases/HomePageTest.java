package com.onlinebookstore.testcases;

import com.onlinebookstore.pages.HomePage;
import com.onlinebookstore.testbase.TestBase;
import org.testng.annotations.Test;

import java.util.logging.Logger;

public class HomePageTest extends TestBase {

    Logger log = Logger.getLogger(HomePageTest.class.getName());
    HomePage homePage = null;

    public HomePageTest() {
        super();
    }

    @Test
    public void verifyHomePageNavigation_01() {
        launchApplication();
        homePage = new HomePage();//creates copy of home page
        homePage.confirmPageNavigation();
    }
}
