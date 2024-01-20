package com.onlinebookstore.customactions;

import com.onlinebookstore.driverfactory.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class CustomSeleniumActions extends DriverFactory {

    public void waitForVisibilityOfElement(By locator) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public boolean isElementExists(By locator) {
        waitForVisibilityOfElement(locator);
        int size = getDriver().findElements(locator).size();//list of webelements - size return the size of the list
        return size > 0;
    }

    public void waitForElementToBeClickable(By locator) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(20));
        wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public void waitForElementToBeSelected(By locator) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(20));
        wait.until(ExpectedConditions.elementToBeSelected(locator));
    }

    public String getElementText(By locator) {
        waitForVisibilityOfElement(locator);
        String innerText = null;
        WebElement element = getDriver().findElement(locator);
        if (innerText == null) {
            innerText = element.getText();
        } else if (innerText.isEmpty()) {
            JavascriptExecutor js = (JavascriptExecutor) getDriver();
            innerText = String.valueOf(js.executeScript("return arguments[0].innerText", element));
        } else {
            Assert.fail("Failed to fetch text from the element");
        }
        return innerText;
    }

    public void click(By locator) {
        waitForElementToBeClickable(locator);
        try {
            getDriver().findElement(locator).click();
        } catch (Exception e) {
            getDriver().findElement(locator).click();
        }
    }

    public void setText(By locator, String value) {
        waitForVisibilityOfElement(locator);
        getDriver().findElement(locator).sendKeys(value);
    }

    public void selectByText(By locator, String value) {
        waitForElementToBeSelected(locator);
        Select select = new Select(getDriver().findElement(locator));
        select.selectByVisibleText(value);
    }

    public void selectByValue(WebDriver driver, By locator, String value) {
        waitForElementToBeSelected(locator);
        Select select = new Select(getDriver().findElement(locator));
        select.selectByVisibleText(value);
    }
}
