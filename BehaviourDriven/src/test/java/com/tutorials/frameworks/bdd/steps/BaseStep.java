package com.tutorials.frameworks.bdd.steps;

import com.thoughtworks.gauge.AfterScenario;
import com.thoughtworks.gauge.BeforeScenario;
import com.thoughtworks.gauge.Step;
import com.tutorials.frameworks.bdd.webdriver.DriverManager;
import org.junit.After;
import org.openqa.selenium.WebDriver;

import java.sql.Driver;

public class BaseStep {

    @BeforeScenario
    public void startDriver(){
        DriverManager.INSTANCE.startDriver();
    }

    @AfterScenario
    public void stopDriver(){
        DriverManager.INSTANCE.stopDriver();
    }

    @Step("Close Browser")
    public void closeBrowser(){
        DriverManager.INSTANCE.getDriver().quit();
    }
}
