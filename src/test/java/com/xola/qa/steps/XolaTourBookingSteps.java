package com.xola.qa.steps;

import com.xola.qa.pages.BookingDetailsPage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.util.Map;

public class XolaTourBookingSteps extends BaseSteps {
    private Map<String, String> custDetails;
    private Map<String, String> creditCardDetails;


    public void setCustTestData(Map<String, String> custDetails) {
        this.custDetails = custDetails;
        bookingPage.waitPageLoaded();
    }

    public void setCcTestData(Map<String, String> creditCardDetails) {
        this.creditCardDetails = creditCardDetails;
    }

    public void fillQuantityDetails() {
        selectAdultQuantity(custDetails.get("QuantityAdults"));
        selectChildQuantity(custDetails.get("QuantityChildren"));
    }

    public void fillCustDetails() {
        bookingPage.getNameField().sendKeys(custDetails.get("FullName"));
        bookingPage.getEmailField().sendKeys(custDetails.get("Email"));
        bookingPage.getPhoneField().sendKeys(custDetails.get("Phone"));
    }

    public void fillCalendarDetails() {
        while (true) {
            if (calendar.getSelectedMonth().trim().equalsIgnoreCase(custDetails.get("BookingMonth")) &&
                    calendar.getSelectedYear().trim().equalsIgnoreCase(custDetails.get("BookingYear"))) break;
            calendar.getMonthFwdIcon().click();
        }
        calendar.getFirstAvailableDate().click();
        calendar.getFirstAvailableTimeSlot().click();
    }

    public void clickContinueBtn() {
        bookingPage.getContinueBtn().click();
        checkOutPage.waitPageLoaded();
    }

    public void verifyCustDetailsOnCheckOutPage() {
        Assert.assertEquals(checkOutPage.getCustName().trim(), custDetails.get("FullName").toUpperCase());
        int adult = Integer.parseInt(custDetails.get("QuantityAdults"));
        int child = Integer.parseInt(custDetails.get("QuantityChildren"));
        if (child != 0) Assert.assertEquals(checkOutPage.getQuantity().trim(),
                "guests " + (adult + child) + " Reserved");
        else Assert.assertEquals(checkOutPage.getQuantity().trim(), "guests " + adult + " Adults");
        Assert.assertEquals(checkOutPage.getCustEmail().trim(), "mail " + custDetails.get("Email"));
        Assert.assertEquals(checkOutPage.getArrDate().trim(), getFormatedArrDate());
        Assert.assertEquals(checkOutPage.getCustPhone().trim(), "phone " + custDetails.get("Phone"));
        Assert.assertEquals(checkOutPage.getArrTime().trim(), "time " + calendar.getSelectedTimeSlot());
    }

    public void verifyValErrMsgForCcDetails() {
        checkOutPage.getPayBtn().click();
        Assert.assertEquals(checkOutPage.getCcNumFieldErr().trim(), "Required");
        Assert.assertEquals(checkOutPage.getCvvFieldErr().trim(), "Required");
        Assert.assertEquals(checkOutPage.getExpErr().trim(), "Required");
    }

    public void verifyCcNumFieldErrWithWrngCcNum(String CcNum) {
        checkOutPage.getCcNumField().sendKeys(CcNum);
        checkOutPage.getCvvField().sendKeys(creditCardDetails.get("SecCode"));
        selectDrpDwnVal(checkOutPage.getExpMnthDrpDwn(), creditCardDetails.get("ExpMonth"));
        selectDrpDwnVal(checkOutPage.getExpYrDrpDwn(), creditCardDetails.get("ExpYear"));
        checkOutPage.getZipCodeField().sendKeys(creditCardDetails.get("Zip"));
        checkOutPage.getPayBtn().click();
        Assert.assertEquals(checkOutPage.getCcNumFieldErr().trim(), "Card number is invalid");
    }

    public void fillValidCcNum() {
        checkOutPage.getCcNumField().clear();
        checkOutPage.getCcNumField().sendKeys(creditCardDetails.get("CardNum"));
    }

    public void fillCcDetails() {
        checkOutPage.getCcNumField().sendKeys(creditCardDetails.get("CardNum"));
        checkOutPage.getCvvField().sendKeys(creditCardDetails.get("SecCode"));
        selectDrpDwnVal(checkOutPage.getExpMnthDrpDwn(), creditCardDetails.get("ExpMonth"));
        selectDrpDwnVal(checkOutPage.getExpYrDrpDwn(), creditCardDetails.get("ExpYear"));
        checkOutPage.getZipCodeField().sendKeys(creditCardDetails.get("Zip"));
    }

    public void clickPayBtn() {
        checkOutPage.getPayBtn().click();
        successPage.waitPageLoaded();
    }

    public void verifySuccessMsg() {
        Assert.assertEquals(successPage.getSuccessMsg().getText().trim(), "YOUR BOOKING HAS BEEN PLACED");
    }

    public void verifyExceededQuantityErrMsg() {
        bookingPage.waitPageLoaded();
        String actErrMsg = "Please contact Bengaluru City Tours at 1234567890 or email rushi+blrtours@xola.com if you are interested in booking a group larger than 6";
        selectAdultQuantity("7");
        Assert.assertEquals(bookingPage.getQuantityErrMsg(), actErrMsg);
    }

    public void verifyValMsgForBookingDetailsPage() {
        bookingPage.getContinueBtn().click();
        Assert.assertEquals(bookingPage.getNameFieldErr().trim(), "Required");
        Assert.assertEquals(bookingPage.getEmailFieldErr().trim(), "Required");
        Assert.assertEquals(calendar.getCalenderDateErrMsg().trim(), "Select a valid date");
        calendar.getDateAtIndex(2).click();
        bookingPage.getContinueBtn().click();
        Assert.assertEquals(calendar.getCalenderTimeErrMsg().trim(), "Select a valid time");
    }

    private void selectDrpDwnVal(WebElement element, String value) {
        Select select = new Select(element);
        try {
            select.selectByValue(value);
        } catch (Exception e) {

            select.selectByVisibleText(value);
        }
    }

    private void selectAdultQuantity(String adultQuantity) {
        int adult;
        if (adultQuantity == null) adult = 1;
        else adult = Integer.parseInt(adultQuantity);
        for (int i = 1; i <= adult; i++) {
            if (Integer.parseInt(bookingPage.getAdultCount()) == adult) break;
            bookingPage.getAdultSpinUp().click();
        }
    }

    private void selectChildQuantity(String childQuantity) {
        int child;
        if (childQuantity == null) child = 0;
        else child = Integer.parseInt(childQuantity);
        for (int i = 0; i <= child; i++) {
            if (Integer.parseInt(bookingPage.getChildCount()) == child) break;
            bookingPage.getChildSpinUp().click();
        }
    }

    private String getFormatedArrDate() {
        return String.format("date %s %s, %s", custDetails.get("BookingMonth").substring(0, 3),
                calendar.getSelectedDate(), custDetails.get("BookingYear"));
    }

    private String getActualTimeSlot(String time) {
        String[] timeSlots = {"1000", "1200", "8000"};

        switch (time) {

            case "10:00 AM":
                return timeSlots[0];
            case "12:00 PM":
                return timeSlots[1];
            case "8:00 PM":
                return timeSlots[2];
            default:
                return timeSlots[1];
        }
    }
}
