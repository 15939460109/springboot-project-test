package com.czg.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyWebMvcConfig implements WebMvcConfigurer {

    @Autowired
    private MyInterceptor interceptor;

    // 注册自定义拦截器，声明拦截器相关规则
    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(interceptor)
                // 筛选，放行
                .addPathPatterns("/**")
                .excludePathPatterns("/login", "/*.css", "/*.js");
    }
}
