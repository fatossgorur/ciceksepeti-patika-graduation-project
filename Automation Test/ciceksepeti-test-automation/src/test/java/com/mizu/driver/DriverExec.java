package com.mizu.driver;

import com.mizu.utils.Log4j;
import com.mizu.utils.ReadProperties;
import org.openqa.selenium.WebDriver;
import java.time.Duration;
import java.util.ResourceBundle;

public class DriverExec {

    public static String browserName;
    public static WebDriver driver;
    public static String baseUrl;
    public static ResourceBundle ConfigurationProp = ReadProperties.readProp("Configuration.properties");
    public static String platformName;

    public static WebDriver getDriver() {
        if (driver == null) {
            browserName = ConfigurationProp.getString("browserName");
            baseUrl = ConfigurationProp.getString("baseUrl");
            platformName = ConfigurationProp.getString("platformName");
            driver = BrowserExec.LocalExec(browserName);
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
            driver.manage().deleteAllCookies();
            Log4j.startLog("Driver initialized");
        }
        return driver;
    }

    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
            Log4j.endLog("The driver has been quit");
        }
    }

}
