package com.xola.qa.utilities;

import com.xola.qa.basetests.BaseTest;
import org.apache.commons.codec.binary.Base64;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import static com.xola.qa.utilities.SystemVars.getDir;

public class Utility {

    public static Properties getProperties;

    public static void loadProperties() throws IOException {
        getProperties = new Properties();
        getProperties.load(new FileInputStream(new File(getDir() + "\\configs\\config.properties")));
    }


    public static String takeScreenShot() throws IOException {
        File scrFile = ((TakesScreenshot) BaseTest.driver).getScreenshotAs(OutputType.FILE);
        String encodedBase64 = null;
        try {
            FileInputStream fis = new FileInputStream(scrFile);
            byte[] bytes = new byte[(int) scrFile.length()];
            fis.read(bytes);
            encodedBase64 = new String(Base64.encodeBase64(bytes));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "data:image/png;base64," + encodedBase64;
    }
}
