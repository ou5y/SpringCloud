package com.example.demo.system.config;

import com.example.demo.system.interceptor.ApiTokenHandlerInterceptor;
import com.example.demo.system.interceptor.CORSInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

    @Bean
    CORSInterceptor corsInterceptor(){
        return new CORSInterceptor();
    }

    @Bean
    ApiTokenHandlerInterceptor apiTokenHandlerInterceptor(){
        return  new ApiTokenHandlerInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(corsInterceptor()).
                addPathPatterns("/**");

        registry.addInterceptor(apiTokenHandlerInterceptor())
                .addPathPatterns("/v1/**")
                .excludePathPatterns("/v1/customer/*")
                .excludePathPatterns("/v1/api/common/fileUploadWeb")
                .excludePathPatterns("/v1/api/homepage/*");
    }


}
