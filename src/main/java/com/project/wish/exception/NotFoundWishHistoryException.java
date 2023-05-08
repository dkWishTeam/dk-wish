package com.project.wish.exception;

public class NotFoundWishHistoryException extends RuntimeException{
    public static final String MESSAGE = "해당 위시히스토리는 삭제되었거나 존재하지 않습니다.";


    public NotFoundWishHistoryException() {
        super(MESSAGE);
    }
}
