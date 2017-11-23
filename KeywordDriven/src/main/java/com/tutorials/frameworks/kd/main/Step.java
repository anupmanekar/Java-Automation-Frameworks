package com.tutorials.frameworks.kd.main;

public class Step {
    private String function;
    private String params;
    private String result;

    public Step(String function,String params){
        this.function = function;
        this.params = params;
        this.result = "";
    }

    public String getFunction() {
        return function;
    }

    public void setFunction(String function) {
        this.function = function;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "Step{" +
                "function='" + function + '\'' +
                ", params='" + params + '\'' +
                ", result='" + result + '\'' +
                '}';
    }
}
