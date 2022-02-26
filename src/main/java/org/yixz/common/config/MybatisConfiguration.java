package org.yixz.common.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 描述
 *
 * @author YIXIUZHENG741
 * @date 2021年12月17日 17:51
 */
@Configuration
@MapperScan("org.yixz.mapper")
public class MybatisConfiguration {
    @Bean
    MetaObjectHandler metaObjectHandler() {
        MybatisObjectHandler mybatisObjectHandler = new MybatisObjectHandler();
        return mybatisObjectHandler;
    }
}
