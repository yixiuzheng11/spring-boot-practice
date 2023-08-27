package org.yixz.common.config;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

/**
 * 描述
 *
 * @author YIXIUZHENG741
 * @date 2021年12月15日 16:36
 */
@Configuration
@EnableKnife4j
@EnableSwagger2
public class Knife4jConfiguration {

    // swagger2的配置文件，这里可以配置swagger2的一些基本的内容，比如扫描的包等等
    @Bean
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
    }
}
