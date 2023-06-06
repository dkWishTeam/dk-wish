package com.project.wish.handler;

import com.project.wish.jwt.JwtTokenProvider;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class myAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
        Authentication authentication) throws IOException {
        var authorities = authentication.getAuthorities();

        var auth = authorities.stream()
            .filter(a -> a.getAuthority().equals("USER") || a.getAuthority().equals("ADMIN"))
            .findFirst();

        if (auth.isPresent()) {
            String token = jwtTokenProvider.createToken(authentication.getName(), authorities);
            httpServletResponse.setContentType("application/json");
            httpServletResponse.getWriter().write("{\"token\": \"" + token + "\"}");
        } else {
            httpServletResponse.sendRedirect("/error");
        }
    }
}
