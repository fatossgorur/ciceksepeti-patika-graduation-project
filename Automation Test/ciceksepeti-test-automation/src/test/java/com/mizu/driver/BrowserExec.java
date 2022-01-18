package com.mizu.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.opera.OperaOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class BrowserExec {

    public static WebDriver driver;

    public static WebDriver LocalExec(String browser) {

        switch (browser.toLowerCase(Locale.ENGLISH)) {

            case "chrome":
                System.setProperty("webdriver.chrome.driver", "./src/test/resources/chromedriver.exe");
                driver = getChromeDriver();
                break;
            case "firefox":
                driver = getFirefoxDriver();
                break;
            case "safari":
                driver = getSafariDriver();
                break;
            case "edge":
                driver = getEdgeDriver();
                break;
            case "ie":
                driver = getieDriver();
                break;
            case "opera":
                driver = getOperaDriver();
                break;
            default:
                System.out.println("Error");
        }

        return driver;
    }

    public static ChromeDriver getChromeDriver() {

        DesiredCapabilities capabilities = new DesiredCapabilities();
        //capabilities
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--disable-notifications");
        chromeOptions.addArguments("--disable-blink-features");
        chromeOptions.addArguments("--disable-blink-features=AutomationControlled");
        chromeOptions.addArguments("--disable-gpu");
        chromeOptions.addArguments("--no-sandbox");
        chromeOptions.addArguments("disable-infobars");
        chromeOptions.addArguments("disable-plugins");
        chromeOptions.addArguments("disable-popup-blocking");
        chromeOptions.addArguments("ignore-certificate-errors");
        chromeOptions.addArguments("disable-translate");
        chromeOptions.addArguments("disable-extensions");
        chromeOptions.setExperimentalOption("excludeSwitches", Arrays.asList("enable-automation"));
        chromeOptions.setExperimentalOption("useAutomationExtension", false);
        Map<String, Object> prefs = new HashMap<String, Object>();
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        chromeOptions.setExperimentalOption("prefs", prefs);
        chromeOptions.setExperimentalOption("w3c", false);
        // setProxy(capabilities);
        chromeOptions.merge(capabilities);
        return new ChromeDriver(chromeOptions);
    }

    public static FirefoxDriver getFirefoxDriver() {

        DesiredCapabilities capabilities = new DesiredCapabilities();
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        FirefoxProfile firefoxProfile = new FirefoxProfile();
        firefoxProfile.setPreference("dom.webnotifications.enabled", false);
        firefoxOptions.setProfile(firefoxProfile);
        firefoxOptions.merge(capabilities);
        return new FirefoxDriver(firefoxOptions);
    }

    public static SafariDriver getSafariDriver() {

        DesiredCapabilities capabilities = new DesiredCapabilities();
        SafariOptions safariOptions = new SafariOptions();
        safariOptions.merge(capabilities);
        return new SafariDriver(safariOptions);
    }

    public static EdgeDriver getEdgeDriver() {

        DesiredCapabilities capabilities = new DesiredCapabilities();
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--disable-notifications");
        chromeOptions.addArguments("test-type");
        chromeOptions.addArguments("disable-popup-blocking");
        chromeOptions.addArguments("ignore-certificate-errors");
        chromeOptions.addArguments("disable-translate");
        chromeOptions.addArguments("disable-infobars");
        chromeOptions.addArguments("--no-sandbox");
        chromeOptions.addArguments("disable-plugins");
        chromeOptions.setExperimentalOption("w3c", false);
        chromeOptions.setBinary(DriverExec.ConfigurationProp.getString("edgeBrowserLocation"));
        EdgeOptions edgeOptions = new EdgeOptions().merge(chromeOptions);
        // options
        edgeOptions.merge(capabilities);
        return new EdgeDriver(edgeOptions);
    }

    public static InternetExplorerDriver getieDriver() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("ignoreZoomSetting", true);
        capabilities.setCapability("ignoreProtectedModeSettings", 1);
        capabilities.setCapability("nativeEvents", false);
        capabilities.setCapability("requireWindowFocus", true);
        capabilities.setCapability("enablePersistentHover", false);
        capabilities.setCapability(InternetExplorerDriver.ELEMENT_SCROLL_BEHAVIOR, true);
        capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
        //capabilities
        InternetExplorerOptions internetExplorerOptions = new InternetExplorerOptions();
        internetExplorerOptions.introduceFlakinessByIgnoringSecurityDomains();
        // options
        internetExplorerOptions.merge(capabilities);
        return new InternetExplorerDriver(internetExplorerOptions);
    }

    public static OperaDriver getOperaDriver() {

        DesiredCapabilities capabilities = new DesiredCapabilities();
        OperaOptions operaOptions = new OperaOptions();
        operaOptions.merge(capabilities);
        return new OperaDriver(operaOptions);
    }
}

