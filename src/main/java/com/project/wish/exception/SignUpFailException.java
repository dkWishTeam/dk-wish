package com.project.wish.exception;

public class SignUpFailException extends RuntimeException {
    public static final String MESSAGE = "회원가입에 실패했습니다.";

    public SignUpFailException() {
        super(MESSAGE);
    }
}
