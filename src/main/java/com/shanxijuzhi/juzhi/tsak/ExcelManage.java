/*
package com.shanxijuzhi.juzhi.tsak;*/
/*
package com.shanxijuzhi.juzhi.tsak;


import com.microsoft.schemas.office.visio.x2012.main.CellType;
import com.shanxijuzhi.juzhi.model.TestDataInfo;
import com.sun.rowset.internal.Row;
import javafx.scene.control.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import javax.annotation.Resource;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableScheduling//开启定时任务的支持
public class Test {

    @Resource
    private TestDataInfo testDataInfo;

    @Value("${file.url}")//获取文件的存储路径
    private String url;



    @Scheduled(cron="0/10 * * * * *")
    public List<TestDataInfo> test1() throws Exception {
        String saveUrl = url;
        System.out.println("存储文件的地址为"+ saveUrl);
        File file = new File(saveUrl);
        //遍历saveURl文件下的目录和文件
        File[] files = file.listFiles();

        for (File fs : files){
            //获取文件名
            String fileName = fs.getName();
            System.out.println(fileName);
            //获取文件的后缀名
            String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
            //非目录(即文件是为excel文件.xls)则处理文件,数据插入到库中
            String path = url +"/"+fileName;
            System.out.println(fs);
            System.out.println(path);
            if (!fs.isDirectory() && suffix.equals("xls")) {
                ImportExcel<TestDataInfo> importExcel = new ImportExcel<>();
                List<TestDataInfo> testDataInfos = ReadExcel.readExcelInfo(path,TestDataInfo.class);
                for (TestDataInfo tt:testDataInfos){
                    System.out.println(tt);
                }
            }else if (!fs.isDirectory() && suffix.equals("xlsx")){
                ImportExcel<TestDataInfo> importExcel = new ImportExcel<>();
                List<TestDataInfo> testDataInfos = ReadExcel.readExcelInfo(path,TestDataInfo.class);
                for (TestDataInfo tt:testDataInfos){
                    System.out.println("00000000000000000000000000000000000000000000000000000000000000000000000"+tt);

                }
            }

        }
        return null;

    }



}
*//*


import com.shanxijuzhi.juzhi.model.TestDataInfo;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import javax.annotation.Resource;
import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableScheduling//开启定时任务的支持
public class ExcelManage {
    private HSSFWorkbook workbook;

    @Resource
    private TestDataInfo testDataInfo;

    @Value("${file.url}")//获取文件的存储路径
    private String url;


    @Scheduled(cron="0/10 * * * * *")
    public boolean ExcelManage() {


       String saveUrl = url;
        File file = new File(url);
        //遍历saveURl文件下的目录和文件
        File[] files = file.listFiles();

        for (File fs : files) {
            //获取文件名
            String fileName = fs.getName();
            System.out.println(fileName);
            //获取文件的后缀名
            String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
            //非目录(即文件是为excel文件.xls)则处理文件,数据插入到库中
            String path = url + "/" + fileName;

            if (!fs.isDirectory() && suffix.equals("xls")) {
                ExcelManage excelManage = new ExcelManage();
                List<TestDataInfo> testDataInfos = excelManage.readFromExcel(fileName,TestDataInfo.class);

                // List<TestDataInfo> testDataInfos = ExcelManage.ExcelManage(path,TestDataInfo.class);
                for (TestDataInfo tt:testDataInfos){

                    System.out.println(tt.getId());
                    System.out.println(tt.getNo());

                    System.out.println(tt);
                }
            }else if (!fs.isDirectory() && suffix.equals("xlsx")){
                ExcelManage excelManage = new ExcelManage();
                List<TestDataInfo> testDataInfos = excelManage.readFromExcel(path,TestDataInfo.class);

                for (TestDataInfo tt:testDataInfos){
                    System.out.println("00000000000000000000000000000000000000000000000000000000000000000000000"+tt);

                }
            }

        }
        return true;
    }

    public List readFromExcel(String sheetName, Object object) {

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

*/
/*public static void main(String[] args) {
        String url = "E:/file/001.xlsx";
    TestDataInfo testDataInfo = new TestDataInfo();
    ExcelManage em = new ExcelManage(url);

        List list = em.readFromExcel("sheet1", testDataInfo);
        for (int i = 0; i < list.size(); i++) {
            TestDataInfo newUser = (TestDataInfo) list.get(i);
            System.out.println(newUser.getNo() + " " + newUser.getStoreTime() + " "
                    + newUser.getCheckData());
        }

    }*//*

}




































*/
