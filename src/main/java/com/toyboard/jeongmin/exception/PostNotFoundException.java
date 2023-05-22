package com.toyboard.jeongmin.exception;

public class PostNotFoundException extends BadRequestException {

    private final static String MESSAGE = "존재하지 않는 게시글 입니다.";

    public PostNotFoundException() {
        super(MESSAGE);
    }
}
