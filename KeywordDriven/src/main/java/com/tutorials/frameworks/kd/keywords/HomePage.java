package com.tutorials.frameworks.kd.keywords;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HomePage extends BasePage {

    @FindBy(id = "lst-ib")
    public WebElement searchBox;

    @FindBy(css = "input[class = 'lsb']")
    public WebElement searchBtn;

    @FindBy(css = "h3[class = 'r']")
    public WebElement searchResult;

    private static final Logger LOGGER = LoggerFactory.getLogger(HomePage.class);

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public boolean go_to_google_home(){
        try{
            this.invokeUrl("https://www.google.com");
            assert(this.driver.getTitle().equals("Google"));
        }catch(AssertionError e){
            LOGGER.error(e.getMessage());
            return false;
        }catch(Exception e){
            LOGGER.error(e.getMessage());
            return false;
        }
        return true;

    }

    public boolean search_for_phrase(String text){
        try{
            searchBox.sendKeys(text);
            searchBox.sendKeys(Keys.RETURN);
        }catch(Exception e){
            LOGGER.error(e.getMessage());
            return false;
        }
        return true;
        //searchBtn.click();

    }

    public boolean validate_first_link_text(String text){
        try{
            String result =  searchResult.getText();
            assert(result.equals(text));
        }catch(AssertionError e){
            LOGGER.error(e.getMessage());
            return false;
        }catch(Exception e){
            LOGGER.error(e.getMessage());
            return false;
        }
        return true;

    }
}
