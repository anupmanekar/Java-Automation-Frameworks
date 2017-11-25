package com.tutorials.frameworks.dd.main;

import com.tutorials.frameworks.dd.utils.ExcelParser;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class TestExecutor {

    private static final Logger LOGGER = LoggerFactory.getLogger(TestExecutor.class);

    public boolean execute(String scenarioName) {
        boolean done = false;
        LOGGER.debug(scenarioName);
        List<TestCase> testcases = ExcelParser.read(scenarioName + ".xlsx");
        LOGGER.debug(testcases.toString());
        for (TestCase test : testcases){
            WebDriver driver = new ChromeDriver();
            if (test.isExecuteFlag()){
                LOGGER.info("TEST START:" + test.getTestId() + "|" + test.getTestDescription());
                boolean test_result = true;
                test_result = new WorkflowImplementor(driver,test.getParams()).execute(scenarioName);
                LOGGER.info("TEST END. RESULT:" + test_result);
                test.setResult(Boolean.toString(test_result));

            }
            driver.close();
        }
        return done;
    }
}
