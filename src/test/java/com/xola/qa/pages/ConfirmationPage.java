package com.xola.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

public class ConfirmationPage extends CheckOutPage {

    @Override
    public ConfirmationPage waitPageLoaded() {
        try {
            waitForElement(getConfirmPage(), Integer.parseInt(properties.getProperty("timeout")));
        } catch (NoSuchElementException e) {
            throw new AssertionError(this.getClass().getName() + " page hasn't been loaded", e);
        }
        return this;
    }

    private WebElement getConfirmPage() {
        return driver.findElement(By.className("success"));
    }

    public WebElement getSuccessMsg() {
        return getConfirmPage().findElement(By.className("alert-success"));
    }


}
