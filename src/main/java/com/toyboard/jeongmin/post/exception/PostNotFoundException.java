package com.toyboard.jeongmin.post.exception;

import com.toyboard.jeongmin.advice.NotFoundException;

public class PostNotFoundException extends NotFoundException {

    private final static String MESSAGE = "존재하지 않는 게시글 입니다.";

    public PostNotFoundException() {
        super(MESSAGE);
    }
}
