package org.yixz.common.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.yixz.common.handler.MybatisObjectHandler;

/**
 * 描述
 *
 * @author yixiuzheng11
 * @date 2021年12月17日 17:51
 */
@Configuration
@MapperScan("org.yixz.mapper")
public class MybatisConfig {
    @Bean
    MetaObjectHandler metaObjectHandler() {
        MybatisObjectHandler mybatisObjectHandler = new MybatisObjectHandler();
        return mybatisObjectHandler;
    }
}
