package com.shanxijuzhi.juzhi.tsak;


import com.shanxijuzhi.juzhi.model.TestDataInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.io.File;
import java.util.List;

@Component
@Configuration
@EnableScheduling//开启定时任务的支持
public class FileTask<T> {

    @Value("${file.url}")//获取文件的存储路径
    private String url;

    @Scheduled(cron="0/15 * * * * *")
    public List<TestDataInfo> scanFile() throws Exception {
        System.out.println("我这个实在分支创建的");
        System.out.println("我把我的代码提交到本地分支以及远程分支。");


        System.out.println("我又是新增加的远程   master_dev分支");

        System.out.println("woshi本地dev提交的数据");
        System.out.println("我是本地master 提交的数据");
        System.out.println("fffff    分支本地");
        System.out.println("我又是新增加的远程   master_dev分支");
        System.out.println("我又是新增加的远程   master_dev分支");
        System.out.println("fffff    分支本地");
        System.out.println("我是dev ------------- dev  dev     ");


        System.out.println("开始进入文件扫描的程序..................");
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
                ImportExcel<TestDataInfo> importExcel = new ImportExcel<TestDataInfo>();
                List<TestDataInfo> testDataInfos = importExcel.readxls(path,TestDataInfo.class);

               // List<TestDataInfo> testDataInfos = ExcelManage.ExcelManage(path,TestDataInfo.class);
                /*for (TestDataInfo tt:testDataInfos){

                    System.out.println(tt.getId());
                    System.out.println(tt.getNo());

                    System.out.println(tt);
                }*/
                System.out.println("1111111111111111111");
                System.out.println("1111111111111111111");
                System.out.println("1111111111111111111");
                System.out.println("1111111111111111111");
                System.out.println("1111111111111111111");

            }else if (!fs.isDirectory() && suffix.equals("xlsx")){
                ImportExcel<TestDataInfo> importExcel = new ImportExcel<TestDataInfo>();
                List<TestDataInfo> testDataInfos = importExcel.readxlsx(path,TestDataInfo.class);
                for (TestDataInfo tt:testDataInfos){
                    System.out.println("00000000000000000000000000000000000000000000000000000000000000000000000"+tt);

                    System.out.println("我是dev  我提交的应该会到origin/dev  xxx");

                    System.out.println("我是master    我是本地的master 我应该提交到远程的origin/master");

                }
            }

        }
        return null;

    }

}
