package org.yixz.common.config;

import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.concurrent.TimeUnit;

@Configuration
public class OkHttpConfig {
    @Value("${ok.http.connect-timeout:30}")
    private Integer connectTimeout;

    @Value("${ok.http.read-timeout:30}")
    private Integer readTimeout;

    @Value("${ok.http.write-timeout:30}")
    private Integer writeTimeout;

    @Value("${ok.http.max-idle-connections:200}")
    private Integer maxIdleConnections;

    @Value("${ok.http.keep-alive-duration:300}")
    private Long keepAliveDuration;

    @Bean
    public OkHttpClient okHttpClient() {
        return new OkHttpClient.Builder()
                // 是否开启缓存
                .retryOnConnectionFailure(true)
                .connectionPool(pool())
                .connectTimeout(connectTimeout, TimeUnit.SECONDS)
                .readTimeout(readTimeout, TimeUnit.SECONDS)
                .writeTimeout(writeTimeout, TimeUnit.SECONDS)
                .hostnameVerifier((hostname, session) -> true)
                .build();
    }

    public ConnectionPool pool() {
        return new ConnectionPool(maxIdleConnections, keepAliveDuration, TimeUnit.SECONDS);
    }
}
