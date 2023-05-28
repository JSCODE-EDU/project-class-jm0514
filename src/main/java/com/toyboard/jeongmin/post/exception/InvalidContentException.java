package com.toyboard.jeongmin.post.exception;

import com.toyboard.jeongmin.advice.BadRequestException;

public class InvalidContentException extends BadRequestException {

    private static final String MESSAGE = "내용은 1글자 이상 1000글자 이하여야 합니다.";

    public InvalidContentException() {
        super(MESSAGE);
    }
}
