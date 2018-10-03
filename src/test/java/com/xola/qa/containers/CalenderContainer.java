package com.xola.qa.containers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CalenderContainer {
    private WebDriver driver;
    private String selectedDate;
    private String selectedTimeSlot;

    public CalenderContainer(WebDriver driver) {
        this.driver = driver;
    }

    private WebElement getCalander() {
        return driver.findElement(By.className("datepicker"));
    }

    public String getSelectedMonth() {
        return getCalander().findElement(By.className("ui-datepicker-month")).getText();
    }

    public String getSelectedYear() {
        return getCalander().findElement(By.className("ui-datepicker-year")).getText();
    }

    public WebElement getMonthFwdIcon() {
        return getCalander().findElement(By.className("ui-datepicker-next"));
    }

    public List<WebElement> getAvailableDates() {
        return getCalander().findElements(By.xpath(".//a[contains(@class,'ui-state-availability')]"));
    }

    public List<WebElement> getAvailableTimeSlots() {
        return driver.findElements(By.xpath(".//button[@name='arrivalTime']"));
    }

    public WebElement getFirstAvailableTimeSlot() {
        selectedTimeSlot = getAvailableTimeSlots().get(0).getText().replaceAll("\n", "");
        return getAvailableTimeSlots().get(0);
    }

    public String getSelectedTimeSlot() {
        return selectedTimeSlot;
    }

    public WebElement getTimeSlot(String arrivalTime) {
        for (WebElement e : getAvailableTimeSlots()) {
            selectedTimeSlot = e.getText().replaceAll("\n", "");
            if (e.getAttribute("value").equals(arrivalTime)) return e;
        }
        return getFirstAvailableTimeSlot();
    }

    public WebElement getDateAtIndex(int index) {
        selectedDate = getAvailableDates().get(index).getText().trim().substring(0, 2).replaceAll("\n", "");
        return getAvailableDates().get(index);
    }

    public WebElement getFirstAvailableDate() {
        return getDateAtIndex(0);
    }

    public WebElement getDate(String date) {
        for (WebElement e : getAvailableDates()) {
            selectedDate = e.getText().trim().substring(0, 2).replaceAll("\n", "");
            if (selectedDate.equalsIgnoreCase(date)) return e;
        }
        return getFirstAvailableDate();
    }

    public String getSelectedDate() {
        return selectedDate;
    }


    public String getCalenderDateErrMsg() {
        return driver.findElement(By.className("arrival-error-msg")).getText();
    }

    public String getCalenderTimeErrMsg() {
        return driver.findElement(By.className("arrival-time-error-msg")).getText();
    }

}
