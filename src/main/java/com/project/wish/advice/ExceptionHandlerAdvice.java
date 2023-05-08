package com.project.wish.advice;

import com.project.wish.exception.SignUpFailException;
import com.project.wish.exception.UnAuthorizedAccessException;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Component
public class ExceptionHandlerAdvice {

    @ExceptionHandler(SignUpFailException.class)
    public String handleSignUpFailException(SignUpFailException ex, Model model) {
        String errorMessage = ex.getMessage();
        model.addAttribute("errorMessage", errorMessage);
        return "error/4xx";
    }

    @ExceptionHandler(UnAuthorizedAccessException.class)
    public String handleUnAuthorizedAccessException(UnAuthorizedAccessException ex, Model model) {
        String errorMessage = ex.getMessage();
        model.addAttribute("errorMessage", errorMessage);
        return "error/4xx";
    }
}
