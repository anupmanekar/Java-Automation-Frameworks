package com.tutorials.frameworks.dd.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestCase {
    private String testId;
    private String testDescription;
    private String result;
    private Map<String,String> params = new HashMap<String, String>();
    private boolean executeFlag;

    public TestCase(String testId,String testDescription,boolean executeFlag){
        this.testId = testId;
        this.testDescription = testDescription;
        this.executeFlag = executeFlag;
        this.result = "";
    }

    public String getTestId() {
        return testId;
    }

    public void setTestId(String testId) {
        this.testId = testId;
    }

    public String getTestDescription() {
        return testDescription;
    }

    public void setTestDescription(String testDescription) {
        this.testDescription = testDescription;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public boolean isExecuteFlag() {
        return executeFlag;
    }

    public void setExecuteFlag(boolean executeFlag) {
        this.executeFlag = executeFlag;
    }

    public Map<String, String> getParams() {
        return params;
    }

    public void setParams(Map<String, String> params) {
        this.params = params;
    }

    @Override
    public String toString() {
        return "TestCase{" +
                "testId='" + testId + '\'' +
                ", testDescription='" + testDescription + '\'' +
                ", result='" + result + '\'' +
                ", params=" + params +
                ", executeFlag=" + executeFlag +
                '}';
    }
}
