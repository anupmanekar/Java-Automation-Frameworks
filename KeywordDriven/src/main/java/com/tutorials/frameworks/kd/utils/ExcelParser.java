package com.tutorials.frameworks.kd.utils;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import com.tutorials.frameworks.kd.main.TestCase;
import com.tutorials.frameworks.kd.main.Step;

public class ExcelParser {
    private static final int TEST_ID_COL = 1;
    private static final int TEST_DESC_COL = 2;
    private static final int EXECUTE_FLAG_COL = 3;
    private static final int STEP_COL = 4;
    private static final int PARAMETERS_COL = 5;

    private static final Logger LOGGER = LoggerFactory.getLogger(ExcelParser.class);

    public static List<TestCase> read(String fileName){
        List<TestCase> testcases = new ArrayList<TestCase>();
        try{
            ClassLoader classLoader = ExcelParser.class.getClassLoader();
            FileInputStream excelFile = new FileInputStream(classLoader.getResource(fileName).getFile());
            Workbook workbook = new XSSFWorkbook(excelFile);
            Sheet datatypeSheet = workbook.getSheetAt(0);
            Iterator<Row> iterator = datatypeSheet.iterator();
            int iRow = 0;
            List<Step> stepList = null;
            TestCase test = null;
            while (iterator.hasNext()) {
                int iCol = 0;
                Row currentRow = iterator.next();
                String testId = null,testDesc = null,keyword = null,params = null;
                boolean executeFlag = false;
                Iterator<Cell> cellIterator = currentRow.iterator();
                while (cellIterator.hasNext() && iRow != 0) {
                    Cell currentCell = cellIterator.next();
                    switch (iCol){
                        case TEST_ID_COL:
                            testId = currentCell.getStringCellValue();
                            break;
                        case TEST_DESC_COL:
                            testDesc = currentCell.getStringCellValue();
                            break;
                        case EXECUTE_FLAG_COL:
                            String temp = currentCell.getStringCellValue();
                            if (temp.equals("Y"))
                                executeFlag = true;
                            if (temp.equals("N"))
                                executeFlag = false;
                            if (testId != "" && testDesc != ""){
                                test = new TestCase(testId,testDesc,executeFlag);
                                testcases.add(test);
                                stepList = new ArrayList<Step>();
                            }
                            break;
                        case STEP_COL:
                            keyword = currentCell.getStringCellValue();
                            break;
                        case PARAMETERS_COL:
                            params = currentCell.getStringCellValue();
                            break;

                    }
                    iCol = iCol + 1;
                    if (iCol > 6 ){
                        stepList.add(new Step(keyword,params));
                        test.setSteps(stepList);
                    }

                }
                iRow = iRow + 1;
            }
        } catch(FileNotFoundException e) {
            e.printStackTrace();
        } catch(IOException e) {
            e.printStackTrace();
        }
        return testcases;
    }

}
