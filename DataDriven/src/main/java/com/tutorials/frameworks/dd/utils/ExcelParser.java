package com.tutorials.frameworks.dd.utils;

import com.tutorials.frameworks.dd.main.TestCase;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class ExcelParser {
    private static final int TEST_ID_COL = 1;
    private static final int TEST_DESC_COL = 2;
    private static final int EXECUTE_FLAG_COL = 3;
    private static final int PARAMS_COL = 4;

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
            TestCase test = null;
            List<String> headers = new ArrayList<String>();

            while (iterator.hasNext()) {
                int iCol = 0;
                Row currentRow = iterator.next();
                String testId = null,testDesc = null,keyword = null;
                Map<String,String> params = new HashMap<String,String>();
                boolean executeFlag = false;
                Iterator<Cell> cellIterator = currentRow.iterator();
                while (cellIterator.hasNext()) {
                    Cell currentCell = cellIterator.next();
                    if (iRow == 0){
                        headers.add(currentCell.getStringCellValue());
                    }else{
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
                                test = new TestCase(testId,testDesc,executeFlag);

                        }
                        if (iCol >= PARAMS_COL){
                            params.put(headers.get(iCol).toString(),currentCell.getStringCellValue());
                            test.setParams(params);
                        }
                    }
                    iCol = iCol + 1;

                }
                iRow = iRow + 1;
                if (iRow > 1){
                    testcases.add(test);
                }
            }
        } catch(FileNotFoundException e) {
            e.printStackTrace();
        } catch(IOException e) {
            e.printStackTrace();
        }
        return testcases;
    }

}
