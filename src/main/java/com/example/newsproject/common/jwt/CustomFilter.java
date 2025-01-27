package com.example.newsproject.common.jwt;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class CustomFilter implements Filter {

    private final TokenService tokenService;

    private static final List<String> WHITELIST = Arrays.asList(
            "/user/signup",  // 회원가입
            "/user/login"    // 로그인
    );

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;

        String requestURI = httpRequest.getRequestURI();

        if (isWhitelist(requestURI)) {
            chain.doFilter(request, response);
            return;
        }

        String token = httpRequest.getHeader("Authorization");

        //토큰검증
        long userId = tokenService.verifyToken(token);

        //유저 정보 추출
        request.setAttribute("userId", userId);

        chain.doFilter(request, response);

        }
        private boolean isWhitelist(String path) {
        return WHITELIST.contains(path);

    }
}
