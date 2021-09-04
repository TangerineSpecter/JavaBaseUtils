package com.tangerinespecter.javabaseutils.common.util;

import cn.hutool.core.img.BackgroundRemoval;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.read.builder.ExcelReaderBuilder;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * EasyExcel处理工具类
 *
 * @author TangerineSpecter
 */
public class EasyExcelUtils {

    public static void simpleRead(MultipartFile file) throws IOException {
        ExcelReaderBuilder read = EasyExcel.read(file.getInputStream());
        List<List<String>> list = new ArrayList<>();
        read.head(list);
        read.doReadAll();
        for (List<String> strings : list) {
            System.out.println(strings);
        }
    }

    public static void main(String[] args) {
//        String filePath = "/Users/zhouliangjun/Downloads/生产环境机构及员工信息-0729.xlsx";
//        File file = new File(filePath);
//        if (file.isFile()) {
//            System.out.println("这是一个文件");
//        }
//        ExcelReaderBuilder read = EasyExcel.read(file);
//        ExcelReaderSheetBuilder sheet = read.sheet();
//        List<Object> objects = sheet.doReadSync();
//        System.out.println(objects);
        System.out.println(BackgroundRemoval.hexToRgb("ffcc99"));
        System.out.println(String.format("#%02x%02x%02x", 15, 204, 153));
    }
}
