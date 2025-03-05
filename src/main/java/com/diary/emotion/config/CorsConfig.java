package com.diary.emotion.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// CORS 설정
@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:3000") // React 클라이언트 도메인만 허용
                .allowedMethods("*")
                .allowedHeaders("*")
                .allowCredentials(true); // 인증 정보(쿠키, 토큰) 포함 허용
    }
}

