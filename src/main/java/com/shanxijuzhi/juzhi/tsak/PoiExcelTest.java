/*package com.shanxijuzhi.juzhi.tsak;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.InputStream;

public class PoiExcelTest {


    public static XSSFWorkbook workbook; // 工作簿
    public static XSSFSheet sheet; // 工作表
    public static XSSFRow row; // 行
    public static XSSFCell cell; // 列

    public static void main(String[] args) throws Exception{

        String fielName = "E:/file/001.xlsx";
        String sheetName = "Sheet1";
        readExcelData(fielName,sheetName,2,1);
        readExcelData(fielName,sheetName,2,2);
    }

    public static void readExcelData(String fielName,String sheetName,int rownum,int cellnum) throws Exception{

        InputStream in = new FileInputStream(fielName);
        workbook = new XSSFWorkbook(in);
        sheet = workbook.getSheet(sheetName);
        row = sheet.getRow(rownum);
        cell = row.getCell(cellnum);
        switch (cell.getCellType()){
            case XSSFCell.CELL_TYPE_NUMERIC:
                System.out.println("第"+(rownum+1)+"行"+"第"+(cellnum+1)+"列的值： "+String.valueOf(cell.getNumericCellValue()));
                break;
            case XSSFCell.CELL_TYPE_STRING:
                System.out.println("第"+(rownum+1)+"行"+"第"+(cellnum+1)+"列的值： "+cell.getStringCellValue());
                break;
            default:
                System.out.println("第"+(rownum+1)+"行"+"第"+(cellnum+1)+"列的值： "+cell.getStringCellValue());
                break;
        }
    }
}*/

