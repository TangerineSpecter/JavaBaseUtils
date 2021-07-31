package com.tangerinespecter.javabaseutils.common.config;

import com.github.xiaoymin.knife4j.spring.annotations.EnableSwaggerBootstrapUi;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swagger配置类
 */
@Slf4j
@EnableSwagger2
@EnableSwaggerBootstrapUi
@Configuration
public class SwaggerConfig {

    @Value("${server.port}")
    private Integer port;

    @Bean
    public Docket customDocket() {
        log.info("☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆");
        log.info(" 欢迎使用橘子工具包，工具文档访问地址：http://localhost:{}/doc.html ", port);
        log.info("☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆");
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.any())
                .build();
    }

    /**
     * 配置Api相关信息
     */
    private ApiInfo apiInfo() {
        Contact contact = new Contact("丢失的橘子", "https://github.com/TangerineSpecter", "993033472@qq.com");
        return new ApiInfoBuilder()
                .title("Java工具包文档")
                .description("Java工具包接口演示文档")
                .contact(contact)
                .version("2.0.4")
                .build();
    }
}
