package com.diary.emotion.config;

import com.diary.emotion.domain.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Base64;
import java.util.Date;

@RequiredArgsConstructor
@Component
public class JwtTokenProvider {

    private final UserDetailsService userDetailsService;
    private final JwtProperties jwtProperties;
    private final long EXPIRATION_TIME = 864_000_000; // 10일


    // JWT 토큰 생성
    public String generateToken(User user) {
        Date now = new Date();
        return makeToken(new Date(now.getTime() + EXPIRATION_TIME), user);
    }

    private String makeToken(Date expiry, User user) {
        Date now = new Date();

        return Jwts.builder()
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
                .setSubject(user.getEmail()) // token의 주체 설정    sub :
                .setIssuedAt(now)       // 토큰이 발급된 시간   iat:
                .setExpiration(expiry)  // 토큰 만료 시간    exp :
                .claim("id", user.getId())  //  기본 데이터 (sub, iat, exp) 외의 데이터를 추가할 때 사용하는 메서드
                .signWith(SignatureAlgorithm.HS256, jwtProperties.getSecretKey())
                .compact();
    }

    // JWT를 받아서 인증객체를 반환
    public Authentication getAuthentication(String token) {
        Claims claims = getClaimsFromToken(token);
        String userEmail = claims.getSubject();

        var userDetails = userDetailsService.loadUserByUsername(userEmail);
        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }

    // JWT 에서 claims 추출
    public Claims getClaimsFromToken(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(jwtProperties.getSecretKey())   // 비밀키 지정 (signature 변조 여부 확인)
                .parseClaimsJws(token)  // JWT를 파싱하여 내부 Claims 추출
                .getBody(); // payload 추출
        return claims;
    }

    // JWT 토큰이 유효한지 확인
    public boolean validateToken(String token) {
        try{
            Jwts.parser()
                    .setSigningKey(jwtProperties.getSecretKey())
                    .parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}