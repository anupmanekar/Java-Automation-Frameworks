package com.tutorials.frameworks.kd.main;

import com.tutorials.frameworks.kd.keywords.HomePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class KeywordFactory {
    private static final Logger LOGGER = LoggerFactory.getLogger(KeywordFactory.class);
    private WebDriver driver;

    public KeywordFactory(WebDriver driver){
        this.driver = driver;
    }

    public boolean implement(String keyword,String params){
        HomePage page = PageFactory.initElements(this.driver, HomePage.class);
        if (Keywords.go_to_google_home.name().equals(keyword))
            return page.go_to_google_home();
        if (Keywords.search_for_phrase.name().equals(keyword))
            return page.search_for_phrase(params);
        if (Keywords.validate_first_link_text.name().equals(keyword))
            return page.validate_first_link_text(params);
        LOGGER.error("Keyword: " + keyword + " not found");
        return false;
    }

    public Object implement_via_reflection(String keyword,String params){
        Method method;
        HomePage page = PageFactory.initElements(driver, HomePage.class);
        try {
            method = HomePage.class.getMethod(keyword, params.getClass());
            method.invoke(page,params);
        } catch (SecurityException e){

        } catch (NoSuchMethodException e) {

        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        return null;
    }
}
