package com.diary.emotion.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtTokenProvider jwtTokenProvider;
    private final String TOKEN_PREFIX = "Bearer ";

    // http 요청을 가로채서 JWT 여부를 확인
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String token = getToken(request);

        // token이 유효할 경우
        if (token != null && jwtTokenProvider.validateToken(token)) {
            //해당 token claims의 인증 정보를 저장 => 이후 사용자 정보를 가져올 수 있음.
            Authentication authentication = jwtTokenProvider.getAuthentication(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        // 다음 filter로 request 전송 (중간에 request를 가로챘으므로 넘겨줘야함)
        filterChain.doFilter(request, response);
    }

    // Request에서 Token 추출
    /*  GET /api/user/info HTTP/1.1
        Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR...
        에서 eyJhb... 만 추출한다.
     */
    private String getToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith(TOKEN_PREFIX)) {
            return bearerToken.substring(TOKEN_PREFIX.length());
        }
        return null;
    }
}
