package com.project.wish.exception;

public class UnAuthorizedAccessException extends RuntimeException {
    public static final String MESSAGE = "권한이 없습니다";

    public UnAuthorizedAccessException() {
        super(MESSAGE);
    }
}
