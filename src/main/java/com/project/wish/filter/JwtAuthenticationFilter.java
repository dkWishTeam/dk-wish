package com.project.wish.filter;

import com.google.gson.JsonObject;
import com.project.wish.jwt.JwtTokenProvider;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private JwtTokenProvider jwtTokenProvider;

    public JwtAuthenticationFilter(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
        throws ServletException, IOException {
        String jwt = getJwtFromRequest(request);
        if (StringUtils.hasText(jwt)) {
            if (jwtTokenProvider.validateToken(jwt)) {
                Authentication authentication = jwtTokenProvider.getAuthentication(jwt);
                SecurityContextHolder.getContext().setAuthentication(authentication);
                filterChain.doFilter(request, response);
            } else {
                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty("error", "Expired or invalid JWT token");
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");  // 추가되는 부분
                response.getWriter().write(jsonObject.toString());
                return;
            }
        } else {
            filterChain.doFilter(request, response);
        }
    }

    private String getJwtFromRequest(HttpServletRequest request) {
        // 1. header에 Authorization 확인
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7, bearerToken.length());
        }
        return null;
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return request.getServletPath()
            .equals("/login");
    }
}
