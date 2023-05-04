package com.project.wish.advice;

import com.project.wish.exception.SignUpFailException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;

@org.springframework.web.bind.annotation.ControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(SignUpFailException.class)
    public String handleSignUpFailException(SignUpFailException ex, Model model) {
        String errorMessage = ex.getMessage();
        model.addAttribute("errorMessage", errorMessage);
        // 예외 처리 결과를 보여줄 페이지의 이름을 반환합니다.
        return "error/4xx";
    }

}
