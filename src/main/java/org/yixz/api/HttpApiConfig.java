package org.yixz.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import retrofit2.Retrofit;

@Configuration
public class HttpApiConfig {
    @Autowired
    private Retrofit retrofit;

    @Bean
    public HttpApi getHttpApi(){
        return retrofit.create(HttpApi.class);
    }
}
