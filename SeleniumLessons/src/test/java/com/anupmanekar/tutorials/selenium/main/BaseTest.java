package com.anupmanekar.tutorials.selenium.main;

import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.CapabilityType;

public class BaseTest {

    public final static String BROWSER_NAME = "FIREFOX";
    protected WebDriver wd;

    @Before
    public void setup(){
        switch (BROWSER_NAME){
            case "CHROME":
                wd = getChromeDriver();
                break;
            case "FIREFOX":
                wd = getFirefoxDriver();
                break;
            default:
                wd = getChromeDriver();
        }
    }

    public WebDriver getChromeDriver(){
        ChromeOptions options = new ChromeOptions();
        options.setCapability(CapabilityType.TAKES_SCREENSHOT,true);
        options.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS,true);
        ChromeDriverService chromeDriverService = new ChromeDriverService.Builder()
                .usingAnyFreePort()
                .build();
        WebDriver driver = new ChromeDriver(chromeDriverService,options);
        return driver;
    }

    public WebDriver getFirefoxDriver(){
        System.setProperty("webdriver.gecko.driver", "D:\\Tools\\geckodriver-v0.19.1-win64\\geckodriver.exe");
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.setBinary("C:\\Program Files (x86)\\Mozilla Firefox\\firefox.exe");
        //firefoxOptions.setCapability("marionette", true);
        WebDriver driver = new FirefoxDriver(firefoxOptions);
        return driver;
    }
}
