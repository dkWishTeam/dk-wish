package com.project.wish.handler;

import com.google.gson.JsonObject;
import com.project.wish.domain.User;
import com.project.wish.jwt.JwtTokenProvider;
import com.project.wish.repository.UserRepository;
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

    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
        Authentication authentication) throws IOException {
        var authorities = authentication.getAuthorities();

        var auth = authorities.stream()
            .filter(a -> a.getAuthority().equals("ROLE_USER") || a.getAuthority().equals("ROLE_ADMIN"))
            .findFirst();

        if (auth.isPresent()) {
            User user = userRepository.findByUserId(authentication.getName()).orElseThrow();
            String token = jwtTokenProvider.createToken(authentication.getName(), authorities);
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("token", token);
            jsonObject.addProperty("id", user.getId());
            jsonObject.addProperty("userId", user.getUserId());
            jsonObject.addProperty("nickname", user.getNickname());
            jsonObject.addProperty("role", user.getRole().getRoleType().name());

            httpServletResponse.setContentType("application/json");
            httpServletResponse.setCharacterEncoding("UTF-8");  // 추가되는 부분
            httpServletResponse.getWriter().write(jsonObject.toString());

        } else {
            httpServletResponse.sendRedirect("/error");
        }
    }
}
