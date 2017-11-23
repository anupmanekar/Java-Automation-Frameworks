package com.tutorials.frameworks.kd.keywords;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BasePage {

    protected WebDriver driver;
    private static final Logger LOGGER = LoggerFactory.getLogger(BasePage.class);

    public BasePage(WebDriver driver){
        this.driver = driver;
    }

    public void invokeUrl(String url){
        driver.get(url);
        driver.manage().window().maximize();
    }

}