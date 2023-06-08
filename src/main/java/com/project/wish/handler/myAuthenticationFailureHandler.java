package com.project.wish.handler;

import com.google.gson.JsonObject;
import java.io.IOException;
import java.time.LocalDateTime;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

@Component
public class myAuthenticationFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
        AuthenticationException exception) throws IOException, ServletException {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("loginSuccess", false);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");  // 추가되는 부분
        response.getWriter().write(jsonObject.toString());
    }
}
