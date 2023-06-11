package com.project.wish.advice;

import com.project.wish.exception.UnAuthorizedAccessException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerAdvice {

    public static final String MESSAGE = "message";
    public static final String TIMESTAMP = "timestamp";

    @ExceptionHandler(UnAuthorizedAccessException.class)
    public ResponseEntity<?> handleUnAuthorizedAccessException(UnAuthorizedAccessException e) {
        // 예외에 대한 상세 정보를 담을 객체 생성
        Map<String, Object> errorDetails = new HashMap<>();
        errorDetails.put(MESSAGE, e.getMessage());
        errorDetails.put(TIMESTAMP, LocalDateTime.now());

        // 에러 메시지와 함께 HTTP 상태 코드 400(Bad Request) 반환
        return new ResponseEntity<>(errorDetails, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGeneralException(Exception e) {
        Map<String, Object> errorDetails = new HashMap<>();
        errorDetails.put(MESSAGE, e.getMessage());
        errorDetails.put(TIMESTAMP, LocalDateTime.now());

        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
