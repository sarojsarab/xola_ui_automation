package com.xola.qa.basetests;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.xola.qa.listeners.XolaListener;
import com.xola.qa.utilities.BrowserUtility;
import com.xola.qa.utilities.ExcelReader;
import com.xola.qa.utilities.Utility;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import static com.xola.qa.utilities.SystemVars.*;

@Listeners(XolaListener.class)
public class BaseTest {

    private static final Logger logger = Logger.getLogger(BaseTest.class);

    public static WebDriver driver;
    private ExcelReader excel;
    public static ExtentReports extent;
    public static ExtentTest test;
    protected Properties properties = Utility.getProperties;
    protected List<Map<String, String>> bookingDetails, creditCardDetails;
    private ITestResult result;


    static {
        extent = new ExtentReports(getReportPath() + "XOLA_Test_Result" + ".html", true);
        DOMConfigurator.configure("log4j.xml");
        logger.info("Loading properties");
        try {
            Utility.loadProperties();
            logger.info("Properties loaded successfully");
        } catch (IOException e) {
            logger.error("Loading properties \n reason :" + e.getStackTrace());
        }
    }

    private void loadData() throws IOException {
        FileInputStream fis = new FileInputStream(new File(getDir() + "\\configs\\config.properties"));
        logger.info("Loading property file");
        logger.info("Property file loaded successfully");
        logger.info("Loading test data");
        excel = new ExcelReader(getResourcePath() + "testdata\\" + properties.getProperty("testDataFile"));
        bookingDetails = getData("BookingDetails");
        creditCardDetails = getData("CreditCardDetails");
        logger.info("Test data loaded successfully");
    }


    private void initDriver() throws Exception {
        logger.info("Initializing driver");
        driver = new BrowserUtility().setBrowser(Utility.getProperties.getProperty("browser")).getDriver();
        logger.info("Driver initialized");
    }

    private List<Map<String, String>> getData(String sheetName) {
        List<Map<String, String>> excelData = new ArrayList<>();
        excel.setSheetName(sheetName);
        logger.info("Loading " + sheetName + " data");
        for (int i = 1; i <= excel.sheet.getLastRowNum(); i++) {
            excelData.add(excel.getTestData(i));
        }
        logger.info(sheetName + " data loaded successfully");
        return excelData;
    }


    private void log(LogStatus status, String data) {
        logger.info(data);
        test.log(status, data);
    }

    @BeforeMethod(alwaysRun = true)
    public void beforeMethod() throws Exception {
        initDriver();

    }

    @AfterMethod(alwaysRun = true)
    public void afterMethod() {
        driver.quit();
    }

    @BeforeTest(alwaysRun = true)
    public void beforeTest() {
        try {
            loadData();
        } catch (IOException e) {
            logger.info("Unable to load data \n reason :" + e.getStackTrace());
        }
    }


    @AfterTest(alwaysRun = true)
    public void afterTest() {
        extent.endTest(test);
        extent.flush();
    }

}


