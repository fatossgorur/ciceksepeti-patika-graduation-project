package com.mizu.definitions;

import com.mizu.driver.DriverExec;
import com.mizu.pageObjects.HomePage;
import com.mizu.utils.Log4j;
import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import java.util.Map;

public class HomePageSteps {
    //private WebDriver driver;
    HomePage homePage = new HomePage();

    @Given("I go to home page")
    public void goToHomePage() {
        homePage.getUrl(DriverExec.baseUrl);
        Assert.assertEquals(DriverExec.baseUrl,homePage.getCurrentUrl());
    }

    @When("I close the address focus on the page")
    public void closeTheAddressFocus() {
        homePage.closeTheAddressFocus();
    }

    @Then("I verify broken link of sub-categories under the main menu")
    public void verifyBrokenLinkSubcategories() {
        Map<String, Integer> linkMap = homePage.verifyBrokenLinkSubcategories();
        linkMap.forEach((key, value) -> {
            if (value >= 400) {
                Log4j.info(key + " : Broken Link -->" + " HTTP status code : " + value);

            } else {
                Log4j.info(key + " : Valid Link -->" + " HTTP status code : " + value);
            }
        });
    }

    @After
    public void quitDriver() throws InterruptedException {
        Thread.sleep(2000);
        DriverExec.quitDriver();
    }
}
