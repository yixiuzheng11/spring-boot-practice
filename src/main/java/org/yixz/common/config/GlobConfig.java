package org.yixz.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.yixz.common.interceptor.LoginInterceptor;
import javax.annotation.Resource;

@Configuration
public class GlobConfig implements WebMvcConfigurer {
    @Resource
    LoginInterceptor loginInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //注册登录拦截器
        registry.addInterceptor(loginInterceptor).addPathPatterns("/**");
    }
}
