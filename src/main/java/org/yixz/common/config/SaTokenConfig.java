package org.yixz.common.config;

import cn.dev33.satoken.interceptor.SaInterceptor;
import cn.dev33.satoken.stp.StpUtil;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.yixz.common.constant.SaTokenConstant;

@Configuration
public class SaTokenConfig  implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 注册登录拦截器
        registry.addInterceptor(new SaInterceptor(handle -> StpUtil.checkLogin()))
                // 需要拦截的路径
                .addPathPatterns("/**")
                // 不需要拦截的路径
                .excludePathPatterns(
                        SaTokenConstant.excludePathPatterns
                );
    }
}
