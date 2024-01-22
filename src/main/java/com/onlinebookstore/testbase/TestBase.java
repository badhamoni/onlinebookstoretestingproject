package com.onlinebookstore.testbase;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.onlinebookstore.customactions.CustomSeleniumActions;
import com.onlinebookstore.driverfactory.DriverFactory;
import com.onlinebookstore.reporting.ExtentFactory;
import com.onlinebookstore.reporting.ExtentReport;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

public class TestBase extends CustomSeleniumActions {

    static ThreadLocal<ExtentTest> testStep = new ThreadLocal<>();
    public Properties properties = null;
    ExtentReports extent;
    String propertiesFilePath = System.getProperty("user.dir") + "\\src\\test\\resources\\config.properties";

    public TestBase() {
        try {
            FileInputStream fip = new FileInputStream(new File(propertiesFilePath));
            properties = new Properties();
            properties.load(fip);
        } catch (IOException io) {
            io.printStackTrace();
        }
    }

    private ExtentTest report() {
        return ExtentFactory.getInstance().getExtent();
    }

    public ExtentTest testStep() {
        return testStep.get();
    }

    public void setTestStep(String testStepsDescription) {
        testStep.set(report().createNode(testStepsDescription));
    }

    public String generateRandomString(int randomStringLength) {
        String aToz = "abcdefghijklmnopqrstuvwxyz";
        Random random = new Random();
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < randomStringLength; i++) {
            int index = random.nextInt(aToz.length());
            result.append(aToz.charAt(index));
        }
        return result.toString();
    }

    @BeforeSuite(alwaysRun = true)
    public void setupOverallReport() {
        extent = new ExtentReports();
        ExtentReport.setupExtentReport(extent);
    }

    @BeforeMethod
    public void setupTestReport(Method method) {
        ExtentFactory.getInstance().setExtent(extent.createTest(method.getName()));
    }

    @AfterMethod
    public void reportTestResult(ITestResult result) {
        try {
            ExtentReport.reportStatus(getDriver(), testStep.get(), result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        DriverFactory.closeBrowser();
    }

    public void launchApplication() {
        DriverFactory.setDriver(properties.getProperty("browser"));//new ChromeDriver()
        testStep.set(report().createNode("Launch Browser"));
        testStep.get().pass(properties.getProperty("browser") + " launched successfully");
        WebDriver driver = getDriver();
        driver.get(properties.getProperty("url"));
        testStep.get().pass(properties.getProperty("url") + " launched successfully");
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Long.parseLong(properties.getProperty("implicitlyWaitTimeOut"))));
    }

    @AfterSuite(alwaysRun = true)
    public void closeOverallReport() {
        try {
            extent.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addScreenshot(){
        ExtentReport.getScreenShot(testStep.get(), getDriver());
    }
}