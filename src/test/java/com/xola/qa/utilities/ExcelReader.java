package com.xola.qa.utilities;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.log4testng.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ExcelReader {

    private static final Logger logger = Logger.getLogger(ExcelReader.class);

    private XSSFWorkbook workbook;
    public XSSFSheet sheet;
    private String sheetName;


    public ExcelReader(String path) throws IOException {

        workbook = new XSSFWorkbook(new FileInputStream(path));

    }

    public void setSheetName(String sheetName) {
        this.sheetName = sheetName;
        sheet = workbook.getSheet(sheetName);
    }

    public Map<String, String> getTestData(int rowNum) {
        XSSFRow firstRow, dataRow;
        Map<String, String> excelData = new HashMap<>();

        firstRow = sheet.getRow(0);
        dataRow = sheet.getRow(rowNum);
        for (int i = 0; i < firstRow.getLastCellNum(); i++) {
            excelData.put(firstRow.getCell(i).getStringCellValue(), dataRow.getCell(i).getStringCellValue());
        }

        return excelData;
    }

}
