package com.xola.qa.utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

public class BrowserUtility {
    private WebDriver driver;
    private String browser = "firefox";

    public String getBrowser() {
        return browser;
    }

    public BrowserUtility setBrowser(String browser) {
        this.browser = browser;
        return this;
    }


    public WebDriver getDriver() throws Exception {
        if (browser.equalsIgnoreCase("chrome")) {
            System.setProperty("webdriver.chrome.driver", SystemVars.getChromePath());
            DesiredCapabilities capabilities = DesiredCapabilities.chrome();
            capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
            driver = new ChromeDriver(capabilities);
            driver.manage().window().maximize();
        } else if (browser.equalsIgnoreCase("ie")) {
            System.setProperty("webdriver.ie.driver", SystemVars.getIePath());
            DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
            capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
            driver = new InternetExplorerDriver(capabilities);
        } else if (browser.equalsIgnoreCase("firefox")) {
            System.setProperty("webdriver.gecko.driver", SystemVars.getFirefoxPath());
            FirefoxProfile ffProfile = new ProfilesIni().getProfile("myProfile");
            ffProfile.setAcceptUntrustedCertificates(true);
            ffProfile.setAssumeUntrustedCertificateIssuer(false);
            driver = new FirefoxDriver();
            driver.manage().window().maximize();
        } else {
            throw new Exception("Unable to select the browser, please set the browser to get the driver instance");
        }
        return driver;
    }


}
