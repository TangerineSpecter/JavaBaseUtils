package com.tangerinespecter.javabaseutils.common.servlet;

import com.tangerinespecter.javabaseutils.common.DocumentInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;

@Slf4j
public class AppRunWrapper {

    public static ApplicationContext run(Class clazz, String[] args) throws Exception {
        System.setProperty("appName", clazz.getSimpleName());
        ConfigurableApplicationContext application = SpringApplication.run(clazz, args);
        Environment env = application.getEnvironment();

        DocumentInfo.createDocInfo();
        log.info("\n☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆\n\t" +
                        "Application '{}' is running! Access URLs:\n\t" +
                        "欢迎使用橘子工具包，工具文档访问地址: \t\thttp://localhost:{}{}/doc.html\n" +
                        "☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆",
                env.getProperty("spring.application.name", ""),
                env.getProperty("server.port", ""),
                env.getProperty("server.servlet.context-path", ""));
        return application;
    }
}
