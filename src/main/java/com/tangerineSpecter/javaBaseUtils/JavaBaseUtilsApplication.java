package com.tangerinespecter.javabaseutils;

import com.tangerinespecter.javabaseutils.common.DocumentInfo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 启动入口
 *
 * @author TangerineSpecter
 */
@SpringBootApplication
public class JavaBaseUtilsApplication {

    public static void main(String[] args) throws Exception {
        DocumentInfo.createDocInfo();
        SpringApplication.run(JavaBaseUtilsApplication.class, args);
    }

}
