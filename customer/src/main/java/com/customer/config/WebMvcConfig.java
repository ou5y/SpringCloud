package com.customer.config;

import com.customer.interceptor.ApiTokenHandlerInterceptor;
import com.customer.interceptor.CORSInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by fangbaoyan on 2017/5/7.
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

    @Bean
    CORSInterceptor corsInterceptor(){
        return new CORSInterceptor();
    }

    @Bean
    ApiTokenHandlerInterceptor apiTokenHandlerInterceptor()
    {
        return  new ApiTokenHandlerInterceptor();
    }
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(corsInterceptor())
                .addPathPatterns("/**");

        registry.addInterceptor(apiTokenHandlerInterceptor())
                .addPathPatterns("/v1/**")
                .excludePathPatterns("/v1/customer/*")
                .excludePathPatterns("/v1/strollAround/*")
                .excludePathPatterns("/v1/api/common/*/getCode")
                .excludePathPatterns("/v1/api/common/*/{code}/checkCode")
                .excludePathPatterns("/v1/api/common/getPhoneCode")
                .excludePathPatterns("/v1/api/common/fileUploadWeb")
                .excludePathPatterns("/v1/api/common/fileUploadWebCutImg")
                .excludePathPatterns("/v1/api/common/getCityCode")
                .excludePathPatterns("/v1/cApi/homepage/*");
    }


}
