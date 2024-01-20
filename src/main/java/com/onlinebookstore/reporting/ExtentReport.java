package com.onlinebookstore.reporting;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.model.Media;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtentReport {

    private static String extentReportFileName = "";

    public static Media captureScreenShot(WebDriver driver) {
        TakesScreenshot ts = (TakesScreenshot) driver;
        String screenshot = ts.getScreenshotAs(OutputType.BASE64);//this generates base64 string format
        return MediaEntityBuilder.createScreenCaptureFromBase64String(screenshot).build();
    }

    public static String getExtentReportFileName() {
        return extentReportFileName;
    }

    public static void setExtentReportFileName(String extentReportFileName) {
        ExtentReport.extentReportFileName = extentReportFileName;
    }

    public static void setupExtentReport(ExtentReports extent) {//extent = null//calling in test base and passing new ExtentReports
        String timeStamp = (new SimpleDateFormat("MMddyy_HHmmss").format(new Date()));
        extentReportFileName = System.getProperty("user.dir") + "\\test-output\\ExtentReports\\" + timeStamp + ".html";
        ExtentSparkReporter htmlReporter = new ExtentSparkReporter(extentReportFileName);
        extent.setSystemInfo("Executed on Environment: ", "LocalHost");
        extent.setSystemInfo("Executed on browser: ", "chrome");
        extent.setSystemInfo("Execution on OS: ", System.getProperty("os.name"));
        extent.attachReporter(new ExtentSparkReporter[]{htmlReporter});
    }

    public static void reportStatus(WebDriver driver, ExtentTest report, ITestResult result) {
        try {
            switch (result.getStatus()) {//1,2,3
                case 1:
                    report.log(Status.PASS, "Test passed");
                    break;
                case 2:
                    report.fail("Test failed ==> " + result.getThrowable());
                    report.fail("Screeenshot", captureScreenShot(driver));
                    break;
                case 3:
                    report.log(Status.SKIP, "Test Skipped");
                    break;
                default:
                    report.log(Status.WARNING, "Test failed unexpectedly");
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}