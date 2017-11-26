package com.tutorials.frameworks.bdd.webdriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public enum DriverManager {
    INSTANCE;
    private WebDriver driver;
    private boolean driverStartedFlag = false;

    DriverManager() {
        driver = null;
        driverStartedFlag = false;
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void startDriver() {
        driver = new ChromeDriver();
        driverStartedFlag = true;
    }

    public void stopDriver() {
        driver.close();
        driver = null;
    }

}
