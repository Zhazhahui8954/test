/*
package com.shanxijuzhi.juzhi.controller;

import com.shanxijuzhi.juzhi.tsak.InputExcelUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class TestDataInfoController {

    @RequestMapping(value="/inputs.action")
    @ResponseBody
    public void  inputs()throws IOException, InvalidFormatException {


        //读取excel表格
        try {
            List<List<String>> lists = InputExcelUtil.readExcel(file.getInputStream());

            //判断集合是否为空
            if (!CollectionUtils.isEmpty(lists)) {
                for (int i = 1; i < lists.size(); i++) {
                    List<String> list = lists.get(i);
                    for (int j = 0; j < list.size(); j++) {

                        Map<String, Object> map = new HashMap<String, Object>();
                        map.put("Name", list.get(1));
                        map.put("Factory", list.get(2));
                        map.put("Package", list.get(3));
                        map.put("Price", list.get(4));
                        map.put("Code", UtilId.generateId());
                        int ac = ssi.add(map);
                        System.out.println(ac);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}*/
