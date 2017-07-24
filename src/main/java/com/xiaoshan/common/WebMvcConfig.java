package com.xiaoshan.common;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by admin on 2017/7/11.
 */
@Component
public class WebMvcConfig extends WebMvcConfigurerAdapter {

    @Bean
    public AuthFunctionInterceptor getAuthFunctionInterceptor() {
        return new AuthFunctionInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(getAuthFunctionInterceptor());
    }
}
