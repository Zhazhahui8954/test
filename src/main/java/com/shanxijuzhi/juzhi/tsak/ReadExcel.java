/*
package com.shanxijuzhi.juzhi.tsak;


import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class ReadExcel {

    public List readFromExcel(String path, Object object) {

        List result = new ArrayList();
        // 获取该对象的class对象
        Class class_ = object.getClass();
        // 获得该类的所有属性
        Field[] fields = class_.getDeclaredFields();

        // 读取excel数据
        // 获得指定的excel表
        HSSFSheet sheet = workbook.getSheet(sheetName);
        // 获取表格的总行数
        int rowCount = sheet.getLastRowNum() + 1; // 需要加一
        if (rowCount < 1) {
            return result;
        }
        // 获取表头的列数
        int columnCount = sheet.getRow(0).getLastCellNum();
        // 读取表头信息,确定需要用的方法名---set方法
        // 用于存储方法名
        String[] methodNames = new String[columnCount]; // 表头列数即为需要的set方法个数
        // 用于存储属性类型
        String[] fieldTypes = new String[columnCount];
        // 获得表头行对象
        HSSFRow titleRow = sheet.getRow(0);
        // 遍历
        for (int columnIndex = 0; columnIndex < columnCount; columnIndex++) { // 遍历表头列
            String data = titleRow.getCell(columnIndex).toString(); // 某一列的内容
            String Udata = Character.toUpperCase(data.charAt(0))
                    + data.substring(1, data.length()); // 使其首字母大写
            methodNames[columnIndex] = "set" + Udata;
            for (int i = 0; i < fields.length; i++) { // 遍历属性数组
                if (data.equals(fields[i].getName())) { // 属性与表头相等
                    fieldTypes[columnIndex] = fields[i].getType().getName(); // 将属性类型放到数组中
                }
            }
        }
        // 逐行读取数据 从1开始 忽略表头
        for (int rowIndex = 1; rowIndex < rowCount; rowIndex++) {
            // 获得行对象
            HSSFRow row = sheet.getRow(rowIndex);
            if (row != null) {
                Object obj = null;
                // 实例化该泛型类的对象一个对象
                try {
                    obj = class_.newInstance();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }

                // 获得本行中各单元格中的数据
                for (int columnIndex = 0; columnIndex < columnCount; columnIndex++) {
                    String data = row.getCell(columnIndex).toString();
                    // 获取要调用方法的方法名
                    String methodName = methodNames[columnIndex];
                    Method method = null;
                    try {
                        // 这部分可自己扩展
                        if (fieldTypes[columnIndex].equals("java.lang.String")) {
                            method = class_.getDeclaredMethod(methodName,
                                    String.class); // 设置要执行的方法--set方法参数为String
                            method.invoke(obj, data); // 执行该方法
                        } else if (fieldTypes[columnIndex].equals("int")) {
                            method = class_.getDeclaredMethod(methodName,
                                    int.class); // 设置要执行的方法--set方法参数为int
                            double data_double = Double.parseDouble(data);
                            int data_int = (int) data_double;
                            method.invoke(obj, data_int); // 执行该方法
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                result.add(obj);
            }
        }
        return result;
    }









































}

*/
/*

    private static final String EXCEL_XLS = ".xls";
    private static final String EXCEL_XLSX = ".xlsx";


    *//*

*/
/**
     * 读取excel数据
     *
     * @throws Exception
     *//*
*/
/*


    public static List<TestDataInfo> readExcelInfo(String url) throws Exception {
//        支持excel2003、2007
        File excelFile = new File(url);//创建excel文件对象
        InputStream is = new FileInputStream(excelFile);//创建输入流对象
       // checkExcelVaild(excelFile);
        ///Workbook workbook = getWorkBook(is, excelFile);
//        Workbook workbook = WorkbookFactory.create(is);//同时支持2003、2007、2010
//        获取Sheet数量
        int sheetNum = workbook.getActiveSheetIndex();
        sheetNum = 1;//限制模板只在一个工作簿上操作
//      创建二维数组保存所有读取到的行列数据，外层存行数据，内层存单元格数据
        List<List<String>> dataList = new ArrayList<List<String>>();
//        遍历工作簿中的sheet,第一层循环所有sheet表
        for (int index = 0; index < sheetNum; index++) {
            Sheet sheet = workbook.getSheetAt(index);
            if (sheet == null) {
                continue;
            }
//            如果当前行没有数据跳出循环，第二层循环单sheet表中所有行
            for (int rowIndex = 0; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
                System.out.println(sheet.getLastRowNum() + "====");
                Row row = sheet.getRow(rowIndex);
                if (row == null) {
                    continue;
                }
//                遍历每一行的每一列，第三层循环行中所有单元格
                List<String> cellList = new ArrayList<String>();
                for (int cellIndex = 0; cellIndex < row.getLastCellNum(); cellIndex++) {
                    Cell cell = row.getCell(cellIndex);
                    System.out.println(cellIndex);
                    cellList.add(getCellValue(cell));
                }
                dataList.add(cellList);
            }

        }
        is.close();
        return dataList;
    }

    *//*

*/
/**
     * 获取单元格的数据,暂时不支持公式
     *//*
*/
/*


    public static String getCellValue(Cell cell) {
        CellType cellType = cell.getCellTypeEnum();
        if (cellType == null) {
            return null;
        }
        String cellValue = "";
        if (cell == null || cell.toString().trim().equals("")) {
            return null;
        }

        if (cellType == CellType.STRING) {
            cellValue = cell.getStringCellValue().trim();
            return cellValue = StringUtil.isEmpty(cellValue) ? "" : cellValue;
        }
        if (cellType == CellType.NUMERIC) {
            if (HSSFDateUtil.isCellDateFormatted(cell)) {  //判断日期类型
                cellValue = DateFormatUtil.formatDurationYMD(cell.getDateCellValue().getTime());
            } else {  //否
                cellValue = new DecimalFormat("#.######").format(cell.getNumericCellValue());
            }
            return cellValue;
        }
        if (cellType == CellType.BOOLEAN) {
            cellValue = String.valueOf(cell.getBooleanCellValue());
            return cellValue;
        }
        return null;

    }
}
*//*



*/
