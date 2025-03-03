package com.diary.emotion.config;


import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties("jwt") // application jwt 에서 가져오기
public class JwtProperties {
    private String secretKey;
}