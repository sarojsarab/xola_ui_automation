package com.xola.qa.steps;

import com.xola.qa.basetests.BaseTest;
import com.xola.qa.containers.CalenderContainer;
import com.xola.qa.pages.BookingDetailsPage;
import com.xola.qa.pages.CheckOutPage;
import com.xola.qa.pages.ConfirmationPage;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

public class BaseSteps {
    WebDriver driver = BaseTest.driver;
    BookingDetailsPage bookingPage;
    CheckOutPage checkOutPage;
    ConfirmationPage successPage;
    CalenderContainer calendar;

    public void openApp(String url) {
        driver.get(url);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        bookingPage = new BookingDetailsPage();
        checkOutPage = new CheckOutPage();
        successPage = new ConfirmationPage();
        calendar = bookingPage.getCalender();

    }
}
