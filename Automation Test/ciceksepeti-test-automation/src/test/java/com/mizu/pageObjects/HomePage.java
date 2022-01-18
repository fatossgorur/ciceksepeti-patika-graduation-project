package com.mizu.pageObjects;

import com.mizu.driver.DriverExec;
import com.mizu.driver.DriverMethods;
import com.mizu.utils.Log4j;
import com.mizu.utils.VerifyBrokenLinks;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HomePage {

    private static WebDriver driver;
    private static DriverMethods driverMethods;
    private VerifyBrokenLinks verifyBrokenLinks;

    public HomePage() {
        driver = DriverExec.getDriver();
        PageFactory.initElements(driver, this);
        driverMethods = new DriverMethods();
        verifyBrokenLinks = new VerifyBrokenLinks();
    }

    public static class HomePageLocators {
        public static By addressFocusCloseIcon = By.className("js-subheader-close");
        public static By mainMenu = By.cssSelector("nav[class='main-menu']");
        public static By mainMenuItemsList = By.cssSelector("a");

    }

    public void getUrl(String url){
        driverMethods.navigateTo(url);
        Log4j.info("Opening Page : " + url);
    }

    public String getCurrentUrl(){
        Log4j.info("Current url : " + driverMethods.getCurrentUrl());
        return driverMethods.getCurrentUrl();
    }


    public void closeTheAddressFocus() {
        Log4j.info("Waited for page to load");
        driverMethods.waitForAjax(driver);
        WebElement addressFocusCloseIcon = driverMethods.findElement(HomePageLocators.addressFocusCloseIcon);
        Log4j.info("Closed address focus icon");
        driverMethods.clickElement(addressFocusCloseIcon);
    }

    public Map<String, Integer> verifyBrokenLinkSubcategories() {
        Map<String, Integer> map = new HashMap<>();
        WebElement mainMenuItems = driverMethods.findElement(HomePageLocators.mainMenu);
        List<WebElement> mainMenuItemsList = driverMethods.findElementsWithElement(mainMenuItems, HomePageLocators.mainMenuItemsList);
        for (WebElement element : mainMenuItemsList) {
            String link = driverMethods.getAttribute(element, "href");
            if (!link.contains("javascript:void(0)")) {
                Log4j.info("Clickable links pulled");
                int statusCode = verifyBrokenLinks.linkStatus(link);
                Log4j.info("Status codes of links received");
                map.put(link, statusCode);
            }
        }
        Log4j.info("Added links and status codes to the map");
        return map;
    }


}
