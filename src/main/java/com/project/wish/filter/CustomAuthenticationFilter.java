package com.project.wish.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.wish.domain.User;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
        throws AuthenticationException {
        try {
            // 요청 본문(JSON)을 User 객체로 변환
            User user = new ObjectMapper().readValue(request.getInputStream(), User.class);

            // UsernamePasswordAuthenticationToken 생성
            UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(
                user.getUserId(), user.getPassword());

            // Allow subclasses to set the "details" property
            setDetails(request, authRequest);

            return this.getAuthenticationManager().authenticate(authRequest);
        } catch (IOException e) {
            throw new AuthenticationServiceException(e.getMessage());
        }
    }
}

