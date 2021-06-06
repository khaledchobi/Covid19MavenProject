package com.compilia.utility;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class ExcelUtils {
    public XSSFWorkbook excelBook;
    public XSSFSheet excelSheet;
    public XSSFRow row;
    public XSSFCell cell;




    public void setExcelFile(String path, String sheetName)throws Exception{
        try{
            //open the excel
            FileInputStream excelFile = new FileInputStream(path);  // pass the path
            excelBook = new XSSFWorkbook(excelFile); // pass the file
            excelSheet = excelBook.getSheet(sheetName);  // pass the sheet

        }catch(Exception e){
            throw e;
        }
    }

    public String getCellData(int rowNum, int colNum)throws Exception{

        try{
            cell = excelSheet.getRow(rowNum).getCell(colNum);
            String cellData = cell.getStringCellValue();

            return cellData;

        }catch(Exception e){
            return "";
        }

    }

    public void setCellData(String result, int rowNum, int colNum, String path)throws Exception{
        try{
            row = excelSheet.getRow(rowNum);
            cell = row.getCell(colNum, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);

            if(cell ==null){
                cell = row.createCell(colNum);
                cell.setCellValue(result);
            }else{
                cell.setCellValue(result);
            }


            FileOutputStream fileOut = new FileOutputStream(path);
            excelBook.write(fileOut);

            fileOut.flush();

            fileOut.close();


        }catch(Exception e){
            throw e;
        }
    }

}
