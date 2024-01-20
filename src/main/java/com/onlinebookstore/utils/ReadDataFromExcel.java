package com.onlinebookstore.utils;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReadDataFromExcel {
    Sheet sheet = null;
    FileInputStream fp = null;
    XSSFWorkbook xssfWorkbook = null;
    HSSFWorkbook hssfWorkbook = null;
    private String excel_path = System.getProperty("user.dir") + "\\src\\test\\resources\\testdata\\TestData.xlsx";
    String file_extension = excel_path.split("\\.")[1];//xlsx//{"\src\test\resources\testdata\TestData","xlsx"}

    public String getExcel_path() {
        return excel_path;
    }

    public void setExcel_path(String excel_path) {
        this.excel_path = excel_path;
    }

    public Sheet getSheet(String sheetName) {
        try {
            if (file_extension.equals("xlsx")) {
                fp = new FileInputStream(excel_path);
                xssfWorkbook = new XSSFWorkbook(fp);//.xlsx -  XSSFWorkbook
                sheet = xssfWorkbook.getSheet(sheetName);
            } else if (file_extension.equals(".xls")) {
                fp = new FileInputStream(excel_path);
                hssfWorkbook = new HSSFWorkbook(fp);//.xls - HSSFWorkbook
                sheet = hssfWorkbook.getSheet(sheetName);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sheet;
    }

    public Sheet getSheet(int sheetIndex) {
        try {
            if (file_extension.equals("xlsx")) {
                fp = new FileInputStream(excel_path);
                xssfWorkbook = new XSSFWorkbook(fp);//.xlsx -  XSSFWorkbook
                sheet = xssfWorkbook.getSheetAt(sheetIndex);
            } else if (file_extension.equals(".xls")) {
                fp = new FileInputStream(excel_path);
                hssfWorkbook = new HSSFWorkbook(fp);//.xls - HSSFWorkbook
                sheet = hssfWorkbook.getSheetAt(sheetIndex);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sheet;
    }

    public List<Map<String, String>> getDataFromSheet(String sheetName) {
        sheet = this.getSheet(sheetName);

        int totalRows = sheet.getLastRowNum();
        int totalCols = sheet.getRow(0).getLastCellNum();

        Map<String, String> innerMap = null;

        List<Map<String, String>> dataList = new ArrayList<>();

        for (int rowNum = 0; rowNum < totalRows; rowNum++) {
            innerMap = new HashMap<>();//creating empty map
            for (int colNum = 0; colNum < totalCols; colNum++) {
                String key = sheet.getRow(0).getCell(colNum).getStringCellValue();//Email
                String value = sheet.getRow(rowNum + 1).getCell(colNum).getStringCellValue();//abc.a@gmail.com
                innerMap.put(key, value);//Email-->abc.a@gmail.com
            }
            dataList.add(innerMap);//list allows duplicates and adding each row map into list after it is read
        }
        return dataList;
    }
}