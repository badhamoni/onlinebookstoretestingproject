package com.onlinebookstore.driverfactory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class DriverFactory {
    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();

    public DriverFactory() {
        //to get rid of instantiation - creating object
    }

    public static WebDriver getDriver() {
        return driver.get();
    }

    public static void setDriver(String browserName) {
        driver.set(createBrowserInstance(browserName));//driver.set(new chromedriver)//chrome//safari
    }

    public static void closeBrowser() {
        driver.get().close();
        driver.remove();
    }

    public static WebDriver createBrowserInstance(String browserName) {
        switch (browserName) {
            case "chrome":
                WebDriverManager.chromedriver().setup();//set system property
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--incognito");
                return new ChromeDriver(options);//used to return the chromedriver instance
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions optionsFirefox = new FirefoxOptions();
                optionsFirefox.addArguments("-private");
                return new FirefoxDriver(optionsFirefox);
            case "edgedriver":
                WebDriverManager.edgedriver().setup();
                EdgeOptions edgeOptions = new EdgeOptions();
                edgeOptions.addArguments("-InPrivate");
                return new EdgeDriver();
            default:
                throw new IllegalArgumentException("The browser you are trying to instantiate is not supported");
        }
    }
}