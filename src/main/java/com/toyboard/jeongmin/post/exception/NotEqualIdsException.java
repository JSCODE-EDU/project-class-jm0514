package com.toyboard.jeongmin.post.exception;

import com.toyboard.jeongmin.advice.BadRequestException;

public class NotEqualIdsException extends BadRequestException {

    private static final String MESSAGE = "권한이 없습니다.";

    public NotEqualIdsException() {
        super(MESSAGE);
    }
}
