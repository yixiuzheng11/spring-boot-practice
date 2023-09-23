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

    // swagger2的配置文件，这里可以配置swagger2的一些基本的内容，比如扫描的包等等
    /*@Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .useDefaultResponseMessages(false)
                .apiInfo(apiInfo())
                .select()
                // 为当前包路径
                .apis(RequestHandlerSelectors.basePackage("org.yixz.controller")).paths(PathSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }

    // 构建 api文档的详细信息函数,注意这里的注解引用的是哪个
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                // 页面标题
                .title("Knife4j APIs")
                // 描述
                .description("swagger-bootstrap-ui")
                // 创建人信息
                .contact(new Contact("yixiuzheng",  "https://github.com/yixiuzheng11",  "1546267208@qq.com"))
                .termsOfServiceUrl("http://localhost:9090/")
                // 版本号
                .version("1.0")
                .build();
    }*/
}
