package com.shanxijuzhi.juzhi.tsak;


import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Component
public class ImportExcel<T> {

    public List<T> readxlsx(String path, Class c) throws Exception {
        InputStream is = new FileInputStream(path);
        XSSFWorkbook xssfWorkbook = new XSSFWorkbook(is);
        List<T> list = new ArrayList<>();
        // Read the Sheet
        for (int numSheet = 0; numSheet < xssfWorkbook.getNumberOfSheets(); numSheet++) {
            XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(numSheet);
            if (xssfSheet.getPhysicalNumberOfRows() == 0) {
                continue;
            }
            // 第一行读表头（列）
            XSSFRow row0 = xssfSheet.getRow(0);
            String[] propertyNames = new String[row0.getLastCellNum()];
            for (int i = 0; i < row0.getLastCellNum(); i++) {
                XSSFCell cell = row0.getCell(i);
                propertyNames[i] = cell.getStringCellValue();
            }
            // 从第二行开始读数据
            for (int rowNum = 1; rowNum <= xssfSheet.getLastRowNum(); rowNum++) {
                XSSFRow xssfRow = xssfSheet.getRow(rowNum);
                if (xssfRow != null) {
                    /*
                     * student = new Student(); XSSFCell name =
                     * xssfRow.getCell(1);
                     * student.setName(name.getStringCellValue());
                     */
                    T obj = (T) c.newInstance();
                    for (int i = 0; i < xssfRow.getLastCellNum(); i++) {
                        XSSFCell cell = xssfRow.getCell(i);
                        // 单元格对象存在，从单元格对象中取出文本，不管具体的数据类型，都以字符串的形式取出。
                        String value = getCellValueByCell(cell);
                        String methodName = "set" + propertyNames[i].substring(0, 1).toUpperCase()
                                + propertyNames[i].substring(1);
                        Method setMethod = c.getDeclaredMethod(methodName, String.class);
                        setMethod.invoke(obj, value);
                    }
                    list.add(obj);
                }
            }
        }
        return list;
    }


    public List<T> readxls(String path, Class c) throws Exception {
        InputStream is = new FileInputStream(path);
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);
        List<T> list = new ArrayList<>();
        // Read the Sheet
        for (int numSheet = 0; numSheet < hssfWorkbook.getNumberOfSheets(); numSheet++) {
            HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);
            if (hssfSheet.getPhysicalNumberOfRows() == 0) {
                continue;
            }
            // 第一行读表头（列）
            HSSFRow row0 = hssfSheet.getRow(0);
            String[] propertyNames = new String[row0.getLastCellNum()];
            for (int i = 0; i < row0.getLastCellNum(); i++) {
                HSSFCell cell = row0.getCell(i);
                propertyNames[i] = cell.getStringCellValue();
            }
            // 从第二行开始读数据
            for (int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
                HSSFRow hssfRow = hssfSheet.getRow(rowNum);
                if (hssfRow != null) {

                    /*
                     * student = new Student(); XSSFCell name =
                     * xssfRow.getCell(1);
                     * student.setName(name.getStringCellValue());
                     */

                    T obj = (T) c.newInstance();
                    for (int i = 0; i < hssfRow.getLastCellNum(); i++) {
                        HSSFCell cell = hssfRow.getCell(i);
                        // 单元格对象存在，从单元格对象中取出文本，不管具体的数据类型，都以字符串的形式取出。
                        String value = getCellValueByCell(cell);
                        String methodName = "set" + propertyNames[i].substring(0, 1).toUpperCase() + propertyNames[i].substring(1);
                        Method setMethod = c.getDeclaredMethod(methodName, String.class);
                        setMethod.invoke(obj, value);
                    }
                    list.add(obj);
                }
            }
        }
        return list;

    }

    // 获取单元格各类型值，返回字符串类型
    public static String getCellValueByCell(Cell cell) {
        // 判断是否为null或空串
        if (cell == null || cell.toString().trim().equals("")) {
            return "";
        }
        String cellValue = "";
        // 以下是判断数据的类型
        switch (cell.getCellType()) {
            case Cell.CELL_TYPE_NUMERIC: // 数字
                if (org.apache.poi.ss.usermodel.DateUtil.isCellDateFormatted(cell)) {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    cellValue = sdf.format(org.apache.poi.ss.usermodel.DateUtil.getJavaDate(cell.getNumericCellValue())).toString();
                } else {
                    DataFormatter dataFormatter = new DataFormatter();
                    cellValue = dataFormatter.formatCellValue(cell);
                }
                break;
            case Cell.CELL_TYPE_STRING: // 字符串
                cellValue = cell.getStringCellValue();
                break;
            case Cell.CELL_TYPE_BOOLEAN: // Boolean
                cellValue = cell.getBooleanCellValue() + "";
                break;
            case Cell.CELL_TYPE_FORMULA: // 公式
                cellValue = cell.getCellFormula() + "";
                break;
            case Cell.CELL_TYPE_BLANK: // 空值
                cellValue = "";
                break;
            case Cell.CELL_TYPE_ERROR: // 故障
                cellValue = "非法字符";
                break;
            default:
                cellValue = "未知类型";
                break;
        }
        return cellValue;
    }



}