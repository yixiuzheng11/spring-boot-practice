package org.yixz.common.config;

import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.core.Ordered;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.yixz.common.util.DateUtils;
import org.springframework.context.annotation.Configuration;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 全局处理，添加httpmessage转换器
 * 解决返回字符串的问题
 *当我们的Spring MVC接口返回数据时，会根据Content-Type来选择一个HttpMessageConverter来处理，而字符串在不声明Content-Type的情况下优先使用StringHttpMessageConverter ，就导致了转换异常，需要设定成MappingJackson2HttpMessageConverter用Jackson来处理
 * @author yixiuzheng11
 * @date 2021年07月22日 17:43
 */
@Configuration
public class JacksonConfig implements Jackson2ObjectMapperBuilderCustomizer, Ordered {
    @Override
    public void customize(Jackson2ObjectMapperBuilder builder) {
        // 设置java.util.Date时间类的序列化以及反序列化的格式
        builder.simpleDateFormat(DateUtils.dateTimeFormat);
        // JSR 310日期时间处理
        JavaTimeModule javaTimeModule = new JavaTimeModule();
        javaTimeModule.addDeserializer(LocalDate.class, new LocalDateDeserializer(DateUtils.dateFormatter));
        javaTimeModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(DateUtils.dateTimeFormatter));
        javaTimeModule.addSerializer(LocalDate.class, new LocalDateSerializer(DateUtils.dateFormatter));
        javaTimeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(DateUtils.dateTimeFormatter));
        builder.modules(javaTimeModule);

        // 全局转化Long类型为String，解决序列化后传入前端Long类型精度丢失问题
        builder.serializerByType(BigInteger.class, ToStringSerializer.instance);
        builder.serializerByType(Long.class,ToStringSerializer.instance);
    }

    @Override
    public int getOrder() {
        return 1;
    }
}