package com.tutorials.frameworks.kd.main;

import com.sun.org.apache.xpath.internal.operations.Bool;
import com.tutorials.frameworks.kd.utils.ExcelParser;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class TestExecutor {

    private static final Logger LOGGER = LoggerFactory.getLogger(TestExecutor.class);

    public boolean execute(String fileName){
        boolean done = false;
        LOGGER.debug(fileName);
        List<TestCase> testcases = ExcelParser.read(fileName);
        LOGGER.debug(testcases.toString());
        for (TestCase test : testcases){
            WebDriver driver = new ChromeDriver();
            if (test.isExecuteFlag()){
                LOGGER.info("TEST START:" + test.getTestId() + "|" + test.getTestDescription());
                boolean test_result = true;
                for (Step step : test.getSteps()){
                    LOGGER.info("STEP START:" + step.getFunction() + "|" + step.getParams());
                    KeywordFactory stepImpl = new KeywordFactory(driver);
                    boolean result = stepImpl.implement(step.getFunction(),step.getParams());
                    if(!result)
                        test_result = false;
                    LOGGER.info("STEP END. RESULT:" + result);
                    step.setResult(Boolean.toString(result));
                }
                LOGGER.info("TEST END. RESULT:" + test_result);
                test.setResult(Boolean.toString(test_result));

            }
            driver.close();
        }
        return done;
    }
}
