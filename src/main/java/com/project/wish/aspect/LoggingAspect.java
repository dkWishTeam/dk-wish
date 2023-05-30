package com.project.wish.aspect;

import com.project.wish.dto.UserCreateRequestDto;
import java.util.Arrays;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class LoggingAspect {

    // args(dto) = dto 라는 이름을 가진 매개변수를 가진 메서드 추출
    @Before("execution(* com.project.wish.controller.*.*(..)) && args(..)")
    public void logControllerWithArgs(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        String argsString = Arrays.stream(args)
            .map(Object::toString)
            .collect(Collectors.joining(", "));
        log.info("[Controller][Args] Executing method: {} withArgs: {}", methodName, argsString);
    }

    // args(dto) = dto 라는 이름을 가진 매개변수를 가진 메서드 추출
    @Before("execution(* com.project.wish.service.*.*(..)) && args(..)")
    public void logUserControllerWithDto(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        String argsString = Arrays.stream(args)
            .map(Object::toString)
            .collect(Collectors.joining(", "));
        log.info("[Service][Args] Executing method: {} withArgs: {}", methodName, argsString);
    }

    //반환값을 result 라는 변수로 선언한다.
    @AfterReturning(pointcut = "execution(* com.project.wish.controller.*.*(..))", returning = "result")
    public void logControllerResult(JoinPoint joinPoint, Object[] result) {
        String methodName = joinPoint.getSignature().getName();
        String resultString = Arrays.stream(result)
            .map(Object::toString)
            .collect(Collectors.joining(", "));
        log.info("[Controller][Result] Executing method: {} withResult {}", methodName,resultString);
    }

    //반환값을 result 라는 변수로 선언한다.
    @AfterReturning(pointcut = "execution(* com.project.wish.service.*.*(..))", returning = "result")
    public void logServiceResult(JoinPoint joinPoint, Object[] result) {
        String methodName = joinPoint.getSignature().getName();
        String resultString = Arrays.stream(result)
            .map(Object::toString)
            .collect(Collectors.joining(", "));
        log.info("[Service][Result] Executing method: {} withResult {}", methodName,resultString);
    }
}
