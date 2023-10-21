package org.yixz.common.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 描述
 *
 * @author yixiuzheng11
 * @date 2021年12月15日 16:36
 */
@Configuration
public class Knife4jConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Knife4j示例项目 - 接口文档")
                        .description("项目简介，支持Markdown格式：`这里是代码标签哦`，**这里是强调哦**")
                        .version("V1.0")
                        .contact(new Contact().name("yixiuzheng11"))
                );
    }
}
