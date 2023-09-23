package org.yixz.api;

import okhttp3.OkHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Configuration
public class RetrofitConfig {
    @Autowired
    private OkHttpClient okHttpClient;

    @Bean
    public Retrofit getRestAdapter(){
        Retrofit retrofit = new Retrofit .Builder()
                .baseUrl("http://localhost:8080/")
                .client(okHttpClient)
                //设置数据解析器
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }
}
