package com.tangerinespecter.javabaseutils;

import com.tangerinespecter.javabaseutils.common.servlet.AppRunWrapper;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 启动入口
 *
 * @author TangerineSpecter
 */
@SpringBootApplication
public class JavaBaseUtilsApplication {

    public static void main(String[] args) throws Exception {
        AppRunWrapper.run(JavaBaseUtilsApplication.class, args);
    }

}
