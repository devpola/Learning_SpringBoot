package com.example.interceptor.config;

import com.example.interceptor.interceptor.AuthInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class MvcConfig implements WebMvcConfigurer {

    private final AuthInterceptor authInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //해당 interceptor를 전역으로 적용하는 경우
        //registry.addInterceptor(authInterceptor);

        //interceptor를 특정 path pattern에만 적용하는 경우
        registry.addInterceptor(authInterceptor).addPathPatterns("/api/private/*");
    }
}
