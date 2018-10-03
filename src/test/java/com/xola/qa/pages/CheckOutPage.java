package com.xola.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

public class CheckOutPage extends BookingDetailsPage {

    @Override
    public CheckOutPage waitPageLoaded() {
        try {
            waitForElement(getCheckOutPage(), Integer.parseInt(properties.getProperty("timeout")));
        } catch (NoSuchElementException e) {
            throw new AssertionError(this.getClass().getName() + " page hasn't been loaded", e);
        }
        return this;
    }

    private WebElement getCheckOutPage() {
        return driver.findElement(By.className("review-and-pay"));
    }

    private WebElement getPaymentOrderDetails(String className) {
        return driver.findElement(By.className("payment-order-detail")).findElement(By.className(className));
    }

    public String getCustName() {
        return getPaymentOrderDetails("customer-name").getText().trim();
    }

    public String getCustEmail() {
        return getPaymentOrderDetails("customer-email").getText().trim();
    }

    public String getCustPhone() {
        return getPaymentOrderDetails("customer-phone").getText().trim();
    }

    public String getQuantity() {
        return getPaymentOrderDetails("quantity").getText().trim();
    }

    public String getArrDate() {
        return getPaymentOrderDetails("arrival-date").getText().trim();
    }

    public String getArrTime() {
        return getPaymentOrderDetails("arrival-time").getText().trim();
    }

    public WebElement getModifyBtn() {
        return getPaymentOrderDetails("action-edit");
    }

    private WebElement getCcDetails() {
        return driver.findElement(By.className("credit-card-details"));
    }

    public WebElement getCcNumField() {
        return getCcDetails().findElement(By.xpath(".//input[@id='cc-number']"));
    }

    public String getCcNumFieldErr() {
        return driver.findElement(By.xpath(".//input[@id='cc-number']/../span")).getText();
    }

    public WebElement getCvvField() {
        return getCcDetails().findElement(By.name("cvv"));
    }

    public String getCvvFieldErr() {
        return driver.findElement(By.xpath(".//input[@name='cvv']/../span")).getText();
    }

    public String getExpErr() {
        return driver.findElement(By.xpath(".//select[@name='expiryMonth']/../span")).getText();
    }

    public WebElement getExpMnthDrpDwn() {
        return getCcDetails().findElement(By.name("expiryMonth"));
    }


    public WebElement getExpYrDrpDwn() {
        return getCcDetails().findElement(By.name("expiryYear"));
    }

    public WebElement getZipCodeField() {
        return getCcDetails().findElement(By.name("billingPostcode"));
    }

    public WebElement getPayBtn() {
        return driver.findElement(By.className("action-pay"));
    }

    public WebElement getBackBtn() {
        return driver.findElement(By.className("action-back"));
    }
}
