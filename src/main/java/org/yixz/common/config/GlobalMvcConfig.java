package org.yixz.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 全局处理，添加httpmessage转换器
 * 解决返回字符串的问题
 *当我们的Spring MVC接口返回数据时，会根据Content-Type来选择一个HttpMessageConverter来处理，而字符串在不声明Content-Type的情况下优先使用StringHttpMessageConverter ，就导致了转换异常，需要设定成MappingJackson2HttpMessageConverter用Jackson来处理
 * @author yixiuzheng11
 * @date 2021年07月22日 17:43
 */
@Configuration
public class GlobalMvcConfig implements WebMvcConfigurer {
    /*@Resource
    LoginInterceptor loginInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //注册登录拦截器
        registry.addInterceptor(loginInterceptor).addPathPatterns("/**");
    }*/
}
