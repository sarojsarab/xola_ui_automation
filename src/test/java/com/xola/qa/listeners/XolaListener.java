package com.xola.qa.listeners;

import com.relevantcodes.extentreports.LogStatus;
import com.xola.qa.basetests.BaseTest;
import com.xola.qa.utilities.Utility;
import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.IOException;


public class XolaListener implements ITestListener {

    private static final Logger logger = Logger.getLogger(ITestListener.class);

    public void onTestStart(ITestResult tr) {
        BaseTest.test = BaseTest.extent.startTest(tr.getName());
        log(LogStatus.INFO, tr.getName() + " test Started");
    }


    public void onTestSuccess(ITestResult tr) {
        log(LogStatus.PASS, tr.getName() + " test is pass");
    }

    private void log(LogStatus status, String data) {
        logger.info(data);
        BaseTest.test.log(status, data);
    }

    public void onTestFailure(ITestResult tr) {
        log(LogStatus.FAIL, tr.getName() + " test is failed \n reason: " + tr.getThrowable());
        try {
            BaseTest.test.log(LogStatus.FAIL, BaseTest.test.addScreenCapture(Utility.takeScreenShot()));
        } catch (IOException e) {
            logger.warn("Can't take screenshot: " + e);
        }
    }

    public void onTestSkipped(ITestResult tr) {
        log(LogStatus.SKIP, tr.getName() + " test is skipped \n reason" + tr.getThrowable());
    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
        // Add some code here if you want to execute it on test failed but within success percentage
    }

    public void onStart(ITestContext tc) {
        logger.info(tc.getName() + " started");
    }

    public void onFinish(ITestContext tc) {
        logger.info(tc.getName() + " completed");
    }
}
