package com.anupmanekar.tutorials.selenium.main;

import ch.qos.logback.core.util.FileUtil;
import javafx.scene.input.KeyCode;
import junit.framework.Assert;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class GoogleTests {
    private static final Logger LOGGER = LoggerFactory.getLogger(GoogleTests.class);

    @Test
    public void testSearchResults(){
        WebDriver driver = new ChromeDriver();
        try {
            driver.get("https://www.google.com");
            driver.manage().window().maximize();
            WebElement searchBox = driver.findElement(By.id("lst-ib"));
            searchBox.sendKeys("Iconography");
            //searchBox.sendKeys(Keys.RETURN);
            WebElement searchBtn = driver.findElement(By.name("btnK"));
            searchBtn.click();
            driver.wait(5);
            WebDriverWait wait = new WebDriverWait(driver, 20);
            wait.until(ExpectedConditions.titleIs("Iconography - Google Search"));
            WebElement searchResult = driver.findElement(By.cssSelector("h3.r"));
            Assert.assertEquals("Search Result not matching","Iconography - Wikipedia", searchResult.getText());
        }catch(AssertionError e){
            LOGGER.error("Test Case Failed");
        } catch (InterruptedException e) {
            LOGGER.error("Interrupted Exception found");
        } finally {
            driver.close();
        }
    }

    @Test
    public void testLinks(){
        final WebDriver driver = new ChromeDriver();
        try {
            driver.get("https://www.google.com");
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            driver.manage().window().maximize();
            WebDriverWait wait = new WebDriverWait(driver, 30);
            wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("a.gb_b.gb_cc")));
            WebElement menu = driver.findElement(By.cssSelector("a.gb_b.gb_cc"));
            menu.click();
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='gb5']/span[2]")));
            //WebElement newsMenu = driver.findElement(By.xpath("//*[@id='gb5']/span[2]"));
            WebElement newsMenu = driver.findElement(By.cssSelector("a#gb5 span.gb_3"));
            newsMenu.click();
            wait.until(ExpectedConditions.titleIs("Google News"));
            //WebElement signInButton = driver.findElement(By.linkText("Sign in"));
            WebElement signInButton = driver.findElement(By.partialLinkText("Sign"));
            signInButton.click();
            wait.until(ExpectedConditions.titleIs("Sign in – Google accounts"));
            WebElement headerText = driver.findElement(By.className("sfYUmb"));
            Assert.assertEquals("Not matching",headerText.getText(),"Sign in");
        }catch(AssertionError e){
            LOGGER.error("Test Case Failed");
        } finally {
            driver.close();
        }

    }

    @Test
    public void testSearchResultsViaJS(){
        WebDriver driver = new ChromeDriver();
        JavascriptExecutor js = (JavascriptExecutor)driver;
        try {
            driver.get("https://www.google.com");
            driver.manage().window().maximize();
            WebElement searchBox = driver.findElement(By.id("lst-ib"));
            js.executeScript("arguments[0].value='Iconography';",searchBox);
            //searchBox.sendKeys("Iconography");
            //searchBox.sendKeys(Keys.RETURN);
            WebElement searchBtn = driver.findElement(By.name("btnK"));
            searchBtn.click();
            WebDriverWait wait = new WebDriverWait(driver, 20);
            wait.until(ExpectedConditions.titleIs("Iconography - Google Search"));
            WebElement searchResult = driver.findElement(By.cssSelector("h3.r"));
            Assert.assertEquals("Search Result not matching","Iconography - Wikipedia", searchResult.getText());
        }catch(AssertionError e){
            LOGGER.error("Test Case Failed");
        }finally {
            driver.close();
        }
    }

    /**
     * In old ways, {@link DesiredCapabilities} object was defined for each browser and set while instantiating drivers.
     * Below code demonstrates how {@link ChromeDriver} capabilities were defined and instantiated. This however is now
     * deprecated for all drivers.
     */
    @Test
    public void testOldSetDesiredCapabilities(){
        Map<String, Object> prefs = new HashMap<String, Object>();
        DesiredCapabilities profile = DesiredCapabilities.chrome();
        ChromeOptions options = new ChromeOptions();
        prefs.put("download.default_directory", "");
        prefs.put("profile.default_content_settings.popups", 0);
        options.setExperimentalOption("prefs", prefs);
        profile.setCapability("name", "chrome");
        profile.setCapability(CapabilityType.TAKES_SCREENSHOT, true);
        profile.setCapability(ChromeOptions.CAPABILITY, options);
        WebDriver driver = new ChromeDriver(profile);
        try {
            driver.get("https://www.google.com");
            driver.manage().window().maximize();
            WebElement searchBox = driver.findElement(By.id("lst-ib"));
            searchBox.sendKeys("Iconography");
            //searchBox.sendKeys(Keys.RETURN);
            WebElement searchBtn = driver.findElement(By.name("btnK"));
            searchBtn.click();
            //driver.wait(5);
            WebDriverWait wait = new WebDriverWait(driver, 20);
            wait.until(ExpectedConditions.titleIs("Iconography - Google Search"));
            WebElement searchResult = driver.findElement(By.cssSelector("h3.r"));
            Assert.assertEquals("Search Result not matching","Iconography - ikipedia", searchResult.getText());
        }catch(AssertionError e){
            File ssFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            try {
                FileUtils.copyFile(ssFile, new File("D:\\screenshot.png"));
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            LOGGER.error("Test Case Failed");
            new AssertionError(e);
        } finally {
            driver.close();
        }

    }

    /**
     * Here you see how new capabilities are used. Desired Capabilities is deprecated and instead
     * {@link ChromeOptions} and {@link ChromeDriverService} is used. Capabilities are not defined
     * in {@link ChromeOptions} which is demonstrated in below code.
     */
    @Test
    public void testNewSetDesiredCapabilities(){
        ChromeOptions options = new ChromeOptions();
        options.setCapability(CapabilityType.TAKES_SCREENSHOT,true);
        options.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS,true);
        ChromeDriverService chromeDriverService = new ChromeDriverService.Builder()
                                                    .usingAnyFreePort()
                                                    .build();
        WebDriver driver = new ChromeDriver(chromeDriverService,options);
        try {
            driver.get("https://www.google.com");
            driver.manage().window().maximize();
            WebElement searchBox = driver.findElement(By.id("lst-ib"));
            searchBox.sendKeys("Iconography");
            //searchBox.sendKeys(Keys.RETURN);
            WebElement searchBtn = driver.findElement(By.name("btnK"));
            searchBtn.click();
            //driver.wait(5);
            WebDriverWait wait = new WebDriverWait(driver, 20);
            wait.until(ExpectedConditions.titleIs("Iconography - Google Search"));
            WebElement searchResult = driver.findElement(By.cssSelector("h3.r"));
            Assert.assertEquals("Search Result not matching","Iconography - ikipedia", searchResult.getText());
        }catch(AssertionError e){
            File ssFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            try {
                FileUtils.copyFile(ssFile, new File("D:\\screenshot.png"));
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            LOGGER.error("Test Case Failed");
            new AssertionError(e);
        } finally {
            driver.close();
        }
    }
}
