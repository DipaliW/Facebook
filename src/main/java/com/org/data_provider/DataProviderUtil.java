package com.org.data_provider;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class DataProviderUtil {

  public static Object[][] getData(String path) {
    Object data[][];
    FileInputStream excelFile = null;
    try {
      excelFile = new FileInputStream(new File(path));
      XSSFWorkbook workbook = new XSSFWorkbook(excelFile);
      XSSFSheet sheet = workbook.getSheetAt(0);
      data = new Object[sheet.getPhysicalNumberOfRows() - 1][];
      for (int rows = 1; rows < sheet.getPhysicalNumberOfRows(); rows++) {
        XSSFRow row = sheet.getRow(rows);
        Object cellValues[] = new Object[row.getPhysicalNumberOfCells()];
        for (int cells = 0; cells < row.getPhysicalNumberOfCells(); cells++) {
          XSSFCell cell = row.getCell(cells);
          cellValues[cells] = cell.getStringCellValue();
        }
        data[rows - 1] = cellValues;
      }
      return data;
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }
}
