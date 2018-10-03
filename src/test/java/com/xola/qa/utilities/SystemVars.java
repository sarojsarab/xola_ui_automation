package com.xola.qa.utilities;

public class SystemVars {

    public static String getDir() {
        return System.getProperty("user.dir");
    }

    public static String getResourcePath() {
        return getDir() + "\\src\\test\\resources\\";
    }

    public static String getChromePath() {
        return getResourcePath() + "drivers\\chromedriver.exe";
    }

    public static String getFirefoxPath() {
        return getResourcePath() + "drivers\\geckodriver.exe";
    }

    public static String getIePath() {
        return getResourcePath() + "drivers\\IEDriverServer.exe";
    }

    public static String getReportPath() {
        return getDir() + "\\test_output\\";
    }
}
