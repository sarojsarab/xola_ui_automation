package com.xola.qa.pages;

import com.xola.qa.containers.CalenderContainer;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

public class BookingDetailsPage extends AbstractPage {
    private CalenderContainer calender;

    @Override
    public BookingDetailsPage waitPageLoaded() {
        try {
            waitForElement(getBookDetailsPage(), Integer.parseInt(properties.getProperty("timeout")));
        } catch (NoSuchElementException e) {
            throw new AssertionError(this.getClass().getName() + " page hasn't been loaded", e);
        }
        return this;
    }

    private WebElement getQuantity() {
        return driver.findElement(By.className("region-demographics"));
    }

    public WebElement getBookDetailsPage() {
        return driver.findElement(By.cssSelector(".experience-info"));
    }

    private WebElement getSpinner(String category) {
        return getQuantity().findElement(By.xpath(".//*[contains(text(),'" + category + "')]/../.."));
    }

    public WebElement getAdultSpinUp() {
        return getSpinner("Adults").findElement(By.cssSelector(".spin-up"));
    }

    public String getAdultCount() {
        return getSpinner("Adults").findElement(By.name("demographics")).getAttribute("value");
    }

    public WebElement getChildSpinUp() {
        return getSpinner("Children").findElement(By.cssSelector(".spin-up"));
    }

    public String getChildCount() {
        return getSpinner("Children").findElement(By.name("demographics")).getAttribute("value");
    }

    public WebElement getNameField() {
        return driver.findElement(By.id("customerName"));
    }

    public String getNameFieldErr() {
        return driver.findElement(By.xpath(".//*[@id='customerName']/../span")).getText();
    }

    public WebElement getEmailField() {
        return driver.findElement(By.id("customerEmail"));
    }

    public String getEmailFieldErr() {
        return driver.findElement(By.xpath(".//*[@id='customerEmail']/../span")).getText();
    }

    public WebElement getPhoneField() {
        return driver.findElement(By.id("phone"));
    }

    public CalenderContainer getCalender() {
        if (calender == null) calender = new CalenderContainer(driver);
        return calender;
    }

    public WebElement getContinueBtn() {
        return driver.findElement(By.className("action-continue"));
    }

    public String getQuantityErrMsg() {
        waitForElement(driver.findElement(By.className("quantity-error-msg")),
                Integer.parseInt(properties.getProperty("timeout")));
        return driver.findElement(By.className("quantity-error-msg")).getText();
    }
}
