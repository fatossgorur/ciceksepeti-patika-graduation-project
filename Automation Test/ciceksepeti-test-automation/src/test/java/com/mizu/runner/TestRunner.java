package com.mizu.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
            features = "src/test/java/com/mizu/features",
            glue = "com/mizu/definitions",
            plugin = {"pretty", "html:target/cucumber-reports/cucumber.html"}
    )
    public class TestRunner extends AbstractTestNGCucumberTests {
    }


