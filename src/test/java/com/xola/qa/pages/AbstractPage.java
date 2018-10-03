package com.xola.qa.pages;

import com.xola.qa.basetests.BaseTest;
import com.xola.qa.utilities.Utility;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Properties;

public abstract class AbstractPage {
    WebDriver driver = BaseTest.driver;
    Properties properties = Utility.getProperties;
    WebDriverWait wait;

    abstract AbstractPage waitPageLoaded();

    public void waitForElement(WebElement element, int timeOutInSeconds) throws NoSuchElementException {
        wait = new WebDriverWait(driver, timeOutInSeconds);
        wait.until(ExpectedConditions.visibilityOf(element));
    }


}
