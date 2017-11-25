package com.tutorials.frameworks.dd.workflows;

import com.tutorials.frameworks.dd.pages.HomePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SearchScenario {
    private WebDriver driver;
    private String searchTerm;
    private String expectedTerm;
    public final static String NAME = "SearchScenario";

    public SearchScenario(WebDriver wd){
        this.driver = wd;
        this.searchTerm = "";
        this.expectedTerm = "";
    }

    public boolean execute(Map<String,String> params){
        boolean result = false;
        this.searchTerm = params.get("searchTerm").toString();
        this.expectedTerm = params.get("expectedTerm").toString();
        HomePage page = PageFactory.initElements(this.driver, HomePage.class);
        page.go_to_google_home();
        page.search_for_phrase(this.searchTerm);
        result = page.validate_first_link_text(this.expectedTerm);
        return result;

    }
}
