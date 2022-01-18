package com.mizu.driver;

import com.mizu.driver.DriverExec;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.time.Duration;
import java.util.List;

public class DriverMethods {

    private WebDriver driver = DriverExec.getDriver();
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25));

    public void navigateTo(String url) {
        driver.navigate().to(url);
    }

    public String getCurrentUrl(){return driver.getCurrentUrl();}

    public WebElement findElement(By byElement){
        return driver.findElement(byElement);
    }

    public List<WebElement> findElementsWithElement(WebElement element, By byElement) {
        wait.until(ExpectedConditions.visibilityOf(element));
        return element.findElements(byElement);
    }
    public List<WebElement> findElements(By byElement) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(byElement));
        return driver.findElements(byElement);
    }

    public void clickElement(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
    }

    public void scrollElementCenter(WebElement webElement){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        wait.until(ExpectedConditions.visibilityOf(webElement));
        js.executeScript(
                "arguments[0].scrollIntoView({behavior: 'smooth', block: 'center', inline: 'center'});",
                webElement);
    }

    public String getText(WebElement element) {
        return element.getText();
    }

    public void clickElementWithText(WebElement element , String text){
            if (getText(element).equals(text)){
                //wait.until(ExpectedConditions.elementToBeClickable(element));
                element.click();
            }

    }

    public String getText2(WebElement element){
                try {
                    wait.until(ExpectedConditions.visibilityOf(element));
                    return getText(element);
                } catch (org.openqa.selenium.StaleElementReferenceException e) {
                    wait.until(ExpectedConditions.visibilityOf(element));
                    waitBySeconds(1);
                    return getText(element);
                }

    }

    public String getAttribute(WebElement element, String text){
        return element.getAttribute(text);
    }

    public void waitBySeconds(long seconds){
        try {
            Thread.sleep(seconds*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void waitForAjax(WebDriver driver) {
        new WebDriverWait(driver, Duration.ofSeconds(15)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                JavascriptExecutor js = (JavascriptExecutor) driver;
                return (Boolean) js.executeScript("return jQuery.active == 0");
            }
        });
    }


}

