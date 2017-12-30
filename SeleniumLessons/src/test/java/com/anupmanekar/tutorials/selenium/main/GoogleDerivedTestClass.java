package com.anupmanekar.tutorials.selenium.main;

import junit.framework.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GoogleDerivedTestClass extends BaseTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(GoogleDerivedTestClass.class);

    @Test
    public void testSearchResults(){
        try {
            this.wd.get("https://www.google.com");
            this.wd.manage().window().maximize();
            WebElement searchBox = this.wd.findElement(By.id("lst-ib"));
            searchBox.sendKeys("Iconography");
            //searchBox.sendKeys(Keys.RETURN);
            WebElement searchBtn = this.wd.findElement(By.name("btnK"));
            searchBtn.click();
            //driver.wait(5);
            WebDriverWait wait = new WebDriverWait(this.wd, 20);
            wait.until(ExpectedConditions.titleIs("Iconography - Google Search"));
            WebElement searchResult = this.wd.findElement(By.cssSelector("h3.r"));
            Assert.assertEquals("Search Result not matching","Iconography - Wikipedia", searchResult.getText());
        }catch(AssertionError e){
            LOGGER.error("Test Case Failed");
        } finally {
            this.wd.close();
        }
    }
}
