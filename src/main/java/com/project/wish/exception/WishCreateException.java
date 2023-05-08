package com.project.wish.exception;

public class WishCreateException extends RuntimeException {

    private static final String MESSAGE = "wish 생성에 오류가 생겼습니다.";

    public WishCreateException() {
        super(MESSAGE);
    }
}
