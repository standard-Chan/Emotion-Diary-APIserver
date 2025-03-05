package com.diary.emotion.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@RequiredArgsConstructor
@Configuration
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .cors(cors -> cors.and())  // CORS 설정 추가
                .csrf(csrf -> csrf.disable())       // CSRF 비활성화
                .authorizeHttpRequests()
                .requestMatchers("/test","/api/user/signup", "/api/user/login", "/h2-console/**", "/h2-console").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin().disable()  // 기본 로그인 폼 비활성화
                .httpBasic().disable()
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)  // JWT 인증 필터 추가
                .headers().frameOptions().sameOrigin() // 프레임 옵션 설정 (h2 접속을 위함)
                .and()
                .build();
    }

    // password 인코더
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

}