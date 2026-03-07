package com.example.demo.config.oauth2;

import com.example.demo.user.model.AuthUserDetails;
import com.example.demo.utils.JwtUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

// 성공 후 응답 처리 로직
@Slf4j
@RequiredArgsConstructor
@Component
public class OAuth2AuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    private final JwtUtil jwtUtil;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
//        System.out.println("OAuth 2.0 로그인 성공"); // 로그인 성공 했다면 이 클래스로 오기 때문에 이 코드를 탈 것
        log.debug("OAuth 2.0 로그인 성공 디버그");

        // 로그인 유저 정보 꺼내기
        AuthUserDetails user = (AuthUserDetails) authentication.getPrincipal();

        // 로그인 성공 시 토큰 발급 (일반 로그인 방식에서도 UserController에서 해준 것 처럼)
        String jwt = jwtUtil.createToken(user.getIdx(), user.getUsername(), user.getRole());
        response.addHeader("Set-Cookie", "ATOKEN=" + jwt +"; Path=/");

        // 로그인 성공 시 프론트엔드로 리다이렉트
        String redirectUrl = "http://localhost:5173/success";
        getRedirectStrategy().sendRedirect(request, response, redirectUrl);

    }
}
