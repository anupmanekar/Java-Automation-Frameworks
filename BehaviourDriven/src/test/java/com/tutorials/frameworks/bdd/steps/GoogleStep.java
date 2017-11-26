package com.tutorials.frameworks.bdd.steps;

import com.thoughtworks.gauge.Step;
import com.tutorials.frameworks.bdd.po.HomePage;
import com.tutorials.frameworks.bdd.webdriver.DriverManager;

public class GoogleStep extends BaseStep{

    @Step("Open google home page")
    public void openHome(){
        HomePage homePage = new HomePage(DriverManager.INSTANCE.getDriver());
        homePage.go_to_google_home();
    }

    @Step("Search term <searchTerm> on google page")
    public void searchPhrase(String searchTerm){
        HomePage homePage = new HomePage(DriverManager.INSTANCE.getDriver());
        homePage.search_for_phrase(searchTerm);
        homePage.validate_first_link_text(searchTerm);
    }
}
