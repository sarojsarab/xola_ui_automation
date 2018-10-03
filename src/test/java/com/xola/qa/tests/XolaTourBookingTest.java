package com.xola.qa.tests;

import com.xola.qa.basetests.BaseTest;
import com.xola.qa.steps.XolaTourBookingSteps;
import org.testng.annotations.Test;


public class XolaTourBookingTest extends BaseTest {

    @Test(description = "TC_01")
    public void runTc01() {

        //Step1
        XolaTourBookingSteps steps = new XolaTourBookingSteps();
        steps.openApp(properties.getProperty("url"));
        steps.setCustTestData(bookingDetails.get(0));

        //Step2 to Step8
        steps.fillQuantityDetails();
        steps.fillCustDetails();
        steps.fillCalendarDetails();

        //Step9
        steps.clickContinueBtn();

        //Step10
        steps.setCcTestData(creditCardDetails.get(0));
        steps.verifyCustDetailsOnCheckOutPage();

        //Step11
        steps.fillCcDetails();

        //Step12
        steps.clickPayBtn();
        steps.verifySuccessMsg();
    }

    @Test(description = "TC_02")
    public void runTc02() {

        //Step1
        XolaTourBookingSteps steps = new XolaTourBookingSteps();
        steps.openApp(properties.getProperty("url"));

        //Step2
        steps.verifyExceededQuantityErrMsg();
    }

    @Test(description = "TC_03")
    public void runTc03() {

        //Step1
        XolaTourBookingSteps steps = new XolaTourBookingSteps();
        steps.openApp(properties.getProperty("url"));
        steps.setCustTestData(bookingDetails.get(0));

        //Step2
        steps.verifyValMsgForBookingDetailsPage();

        //Step3
        steps.fillQuantityDetails();

        //Step4 to Step 7
        steps.fillCustDetails();
        steps.fillCalendarDetails();

        //Step8
        steps.clickContinueBtn();

        //Step9
        steps.verifyCustDetailsOnCheckOutPage();

        //Step10
        steps.setCcTestData(creditCardDetails.get(0));
        steps.verifyValErrMsgForCcDetails();

        //Step11
        steps.verifyCcNumFieldErrWithWrngCcNum("4000000000000000");

        //Step12
        steps.fillValidCcNum();

        //Step13
        steps.clickPayBtn();
        steps.verifySuccessMsg();
    }
}
