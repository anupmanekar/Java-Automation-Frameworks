package com.tutorials.frameworks.dd.main;

import com.tutorials.frameworks.dd.workflows.SearchScenario;
import org.openqa.selenium.WebDriver;

import java.util.Map;

public class WorkflowImplementor {

    private Map<String,String> params = null;
    private WebDriver driver;

    public WorkflowImplementor(WebDriver wd, Map<String,String> params){
        this.driver = wd;
        this.params = params;
    }

    public boolean execute(String workflowName){
        boolean result = false;
        switch(workflowName){
            case SearchScenario.NAME:
                result = new SearchScenario(this.driver).execute(this.params);
                break;
            default:
                result = false;
                break;
        }
        return result;
    }
}
